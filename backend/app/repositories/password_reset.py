from datetime import datetime, timezone

from sqlalchemy import select, update
from sqlalchemy.orm import Session

from app.models.password_reset import PasswordResetToken


def create_password_reset_token(
    db: Session,
    *,
    user_id: int,
    token_hash: str,
    expires_at: datetime,
) -> PasswordResetToken:
    token = PasswordResetToken(
        user_id=user_id,
        token_hash=token_hash,
        expires_at=expires_at,
    )

    db.add(token)
    db.commit()
    db.refresh(token)

    return token


def get_active_password_reset_token(
    db: Session,
    *,
    user_id: int,
) -> PasswordResetToken | None:
    now = datetime.now(timezone.utc)

    statement = (
        select(PasswordResetToken)
        .where(
            PasswordResetToken.user_id == user_id,
            PasswordResetToken.used_at.is_(None),
            PasswordResetToken.expires_at > now,
        )
        .order_by(PasswordResetToken.created_at.desc())
    )

    return db.scalars(statement).first()


def increment_password_reset_attempts(
    db: Session,
    token: PasswordResetToken,
) -> PasswordResetToken:
    token.attempts += 1

    db.add(token)
    db.commit()
    db.refresh(token)

    return token


def mark_password_reset_token_used(
    db: Session,
    token: PasswordResetToken,
) -> PasswordResetToken:
    token.used_at = datetime.now(timezone.utc)

    db.add(token)
    db.commit()
    db.refresh(token)

    return token


def invalidate_password_reset_tokens(
    db: Session,
    *,
    user_id: int,
) -> None:
    now = datetime.now(timezone.utc)

    statement = (
        update(PasswordResetToken)
        .where(
            PasswordResetToken.user_id == user_id,
            PasswordResetToken.used_at.is_(None),
        )
        .values(used_at=now)
    )

    db.execute(statement)
    db.commit()


def mark_password_reset_token_used_without_commit(
    db: Session,
    token: PasswordResetToken,
) -> PasswordResetToken:
    token.used_at = datetime.now(timezone.utc)

    db.add(token)

    return token
