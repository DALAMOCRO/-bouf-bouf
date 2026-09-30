from datetime import datetime, timedelta, timezone

from sqlalchemy.orm import Session

from app.core.config import get_settings
from app.core.security import (
    generate_password_reset_code,
    hash_password,
    hash_password_reset_code,
    verify_password_reset_code,
)
from app.services.password_reset_delivery import deliver_password_reset_code
from app.repositories.password_reset import (
    create_password_reset_token,
    get_active_password_reset_token,
    increment_password_reset_attempts,
    invalidate_password_reset_tokens,
    mark_password_reset_token_used_without_commit,
)
from app.repositories.user import (
    get_user_by_email,
    get_user_by_phone,
)


class PasswordResetServiceError(Exception):
    """Base exception for password reset errors."""


class InvalidResetCodeError(PasswordResetServiceError):
    """Raised when a reset code is invalid or expired."""


class ResetCodeMaxAttemptsError(PasswordResetServiceError):
    """Raised when the maximum number of reset attempts is reached."""


def find_user_by_identifier(
    db: Session,
    identifier: str,
):
    normalized_identifier = identifier.strip()

    if "@" in normalized_identifier:
        return get_user_by_email(
            db,
            normalized_identifier.lower(),
        )

    return get_user_by_phone(
        db,
        normalized_identifier,
    )


def create_reset_code(
    db: Session,
    *,
    identifier: str,
) -> str | None:
    settings = get_settings()

    user = find_user_by_identifier(
        db,
        identifier,
    )

    if user is None:
        return None

    invalidate_password_reset_tokens(
        db,
        user_id=user.id,
    )

    code = generate_password_reset_code()

    expires_at = datetime.now(timezone.utc) + timedelta(
        minutes=settings.password_reset_code_expire_minutes
    )

    create_password_reset_token(
        db,
        user_id=user.id,
        token_hash=hash_password_reset_code(code),
        expires_at=expires_at,
    )

    deliver_password_reset_code(
        identifier=identifier,
        code=code,
    )

    return code


def verify_reset_code(
    db: Session,
    *,
    identifier: str,
    code: str,
) -> bool:
    settings = get_settings()

    user = find_user_by_identifier(
        db,
        identifier,
    )

    if user is None:
        raise InvalidResetCodeError(
            "Invalid or expired reset code."
        )

    token = get_active_password_reset_token(
        db,
        user_id=user.id,
    )

    if token is None:
        raise InvalidResetCodeError(
            "Invalid or expired reset code."
        )

    if token.attempts >= settings.password_reset_max_attempts:
        raise ResetCodeMaxAttemptsError(
            "Maximum reset attempts reached."
        )

    if not verify_password_reset_code(
        code,
        token.token_hash,
    ):
        increment_password_reset_attempts(
            db,
            token,
        )

        raise InvalidResetCodeError(
            "Invalid or expired reset code."
        )

    return True


def reset_password(
    db: Session,
    *,
    identifier: str,
    code: str,
    new_password: str,
) -> None:
    settings = get_settings()

    user = find_user_by_identifier(
        db,
        identifier,
    )

    if user is None:
        raise InvalidResetCodeError(
            "Invalid or expired reset code."
        )

    token = get_active_password_reset_token(
        db,
        user_id=user.id,
    )

    if token is None:
        raise InvalidResetCodeError(
            "Invalid or expired reset code."
        )

    if token.attempts >= settings.password_reset_max_attempts:
        raise ResetCodeMaxAttemptsError(
            "Maximum reset attempts reached."
        )

    if not verify_password_reset_code(
        code,
        token.token_hash,
    ):
        increment_password_reset_attempts(
            db,
            token,
        )

        raise InvalidResetCodeError(
            "Invalid or expired reset code."
        )

    user.password_hash = hash_password(new_password)

    mark_password_reset_token_used_without_commit(
        db,
        token,
    )

    try:
        db.commit()
    except Exception:
        db.rollback()
        raise

