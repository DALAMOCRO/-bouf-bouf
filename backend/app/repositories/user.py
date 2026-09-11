from sqlalchemy import select
from sqlalchemy.orm import Session

from app.models.user import User


def get_user_by_id(
    db: Session,
    user_id: int,
) -> User | None:
    statement = select(User).where(User.id == user_id)

    return db.scalars(statement).first()


def get_user_by_username(
    db: Session,
    username: str,
) -> User | None:
    statement = select(User).where(User.username == username)

    return db.scalars(statement).first()


def get_user_by_email(
    db: Session,
    email: str,
) -> User | None:
    statement = select(User).where(User.email == email)

    return db.scalars(statement).first()


def get_user_by_phone(
    db: Session,
    phone_number: str,
) -> User | None:
    statement = select(User).where(
        User.phone_number == phone_number
    )

    return db.scalars(statement).first()


def create_user(
    db: Session,
    *,
    username: str,
    email: str,
    password_hash: str,
    display_name: str,
    country: str,
    language: str,
    phone_number: str | None = None,
) -> User:
    user = User(
        username=username,
        email=email,
        phone_number=phone_number,
        password_hash=password_hash,
        display_name=display_name,
        country=country,
        language=language,
    )

    db.add(user)
    db.commit()
    db.refresh(user)

    return user
