from sqlalchemy.orm import Session

from app.core.security import (
    create_access_token,
    hash_password,
    verify_password,
)
from app.repositories.user import (
    create_user,
    get_user_by_email,
    get_user_by_phone,
    get_user_by_username,
)
from app.schemas.auth import (
    LoginRequest,
    RegisterRequest,
    TokenResponse,
    UserResponse,
)


class AuthServiceError(Exception):
    """Base exception for authentication service errors."""


class UsernameAlreadyExistsError(AuthServiceError):
    """Raised when the username is already registered."""


class EmailAlreadyExistsError(AuthServiceError):
    """Raised when the email is already registered."""


class InvalidCredentialsError(AuthServiceError):
    """Raised when login credentials are invalid."""


def register_user(
    db: Session,
    request: RegisterRequest,
) -> UserResponse:
    username = request.username.strip()
    email = str(request.email).strip().lower()
    phone_number = request.phone_number.strip() if request.phone_number else None

    if get_user_by_username(db, username) is not None:
        raise UsernameAlreadyExistsError(
            "Username already exists."
        )

    if get_user_by_email(db, email) is not None:
        raise EmailAlreadyExistsError(
            "Email already exists."
        )

    if phone_number is not None and get_user_by_phone(db, phone_number) is not None:
        raise AuthServiceError(
            "Phone number already exists."
        )

    user = create_user(
        db=db,
        username=username,
        email=email,
        phone_number=phone_number,
        password_hash=hash_password(request.password),
        display_name=request.display_name.strip(),
        country=request.country.strip(),
        language=request.language.strip(),
    )

    return UserResponse.model_validate(user)


def login_user(
    db: Session,
    request: LoginRequest,
) -> TokenResponse:
    identifier = request.email_or_phone.strip()

    if "@" in identifier:
        user = get_user_by_email(db, identifier.lower())
    else:
        user = get_user_by_phone(db, identifier)

    if user is None:
        raise InvalidCredentialsError(
            "Invalid email, phone number, or password."
        )

    if not verify_password(
        request.password,
        user.password_hash,
    ):
        raise InvalidCredentialsError(
            "Invalid email, phone number, or password."
        )

    return TokenResponse(
        access_token=create_access_token(user.id),
        token_type="bearer",
    )
