"""remove video created_at server default

Revision ID: 69c37fe0ccbc
Revises: 4580c449b432
"""

from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


revision: str = "69c37fe0ccbc"
down_revision: Union[str, Sequence[str], None] = "4580c449b432"
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    """Remove the database server default from videos.created_at."""

    op.alter_column(
        "videos",
        "created_at",
        existing_type=sa.DateTime(timezone=True),
        existing_nullable=False,
        server_default=None,
    )


def downgrade() -> None:
    """Restore CURRENT_TIMESTAMP as the database server default."""

    op.alter_column(
        "videos",
        "created_at",
        existing_type=sa.DateTime(timezone=True),
        existing_nullable=False,
        server_default=sa.text("CURRENT_TIMESTAMP"),
    )
