"""align timestamp columns

Revision ID: 4580c449b432
Revises: efbc6ac8d907
Create Date: 2026-09-11 16:52:54.937180

"""

from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


revision: str = "4580c449b432"
down_revision: Union[str, Sequence[str], None] = "efbc6ac8d907"
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    """Align created_at columns with timezone-aware SQLAlchemy models."""

    op.alter_column(
        "users",
        "created_at",
        existing_type=sa.DateTime(),
        type_=sa.DateTime(timezone=True),
        existing_nullable=False,
    )

    op.alter_column(
        "videos",
        "created_at",
        existing_type=sa.DateTime(),
        type_=sa.DateTime(timezone=True),
        existing_nullable=False,
        existing_server_default=sa.text("CURRENT_TIMESTAMP"),
    )


def downgrade() -> None:
    """Revert created_at columns to timezone-naive timestamps."""

    op.alter_column(
        "videos",
        "created_at",
        existing_type=sa.DateTime(timezone=True),
        type_=sa.DateTime(),
        existing_nullable=False,
        existing_server_default=sa.text("CURRENT_TIMESTAMP"),
    )

    op.alter_column(
        "users",
        "created_at",
        existing_type=sa.DateTime(timezone=True),
        type_=sa.DateTime(),
        existing_nullable=False,
    )
