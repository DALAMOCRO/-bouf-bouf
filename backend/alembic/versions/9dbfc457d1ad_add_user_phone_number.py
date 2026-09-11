"""add user phone number

Revision ID: 9dbfc457d1ad
Revises: 69c37fe0ccbc
"""

from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


revision: str = "9dbfc457d1ad"
down_revision: Union[str, Sequence[str], None] = "69c37fe0ccbc"
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    """Add an optional unique phone number to users."""

    op.add_column(
        "users",
        sa.Column(
            "phone_number",
            sa.String(length=20),
            nullable=True,
        ),
    )

    op.create_index(
        "ix_users_phone_number",
        "users",
        ["phone_number"],
        unique=True,
    )


def downgrade() -> None:
    """Remove the user phone number."""

    op.drop_index(
        "ix_users_phone_number",
        table_name="users",
    )

    op.drop_column(
        "users",
        "phone_number",
    )
