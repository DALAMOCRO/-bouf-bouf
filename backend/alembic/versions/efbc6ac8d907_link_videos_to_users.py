"""link videos to users

Revision ID: efbc6ac8d907
Revises: 89675b5fd9cc
Create Date: 2026-09-08
"""

from typing import Sequence, Union

from alembic import op
import sqlalchemy as sa


revision: str = "efbc6ac8d907"
down_revision: Union[str, Sequence[str], None] = "89675b5fd9cc"
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


DEV_PASSWORD_HASH = (
    "$argon2id$v=19$m=65536,t=3,p=4$"
    "dwat/Jbg5Ss09klo4LHiAQ$"
    "sGfpHg3/Pdg5dpvcxhafxGYXzKl/k5gVu38NSSxETu8"
)


SEED_USERS = [
    {
        "username": "rania.cuisine",
        "email": "rania.cuisine@seed.boufbouf.local",
        "display_name": "Rania",
        "country": "Morocco",
        "language": "fr",
        "handle": "@rania.cuisine",
    },
    {
        "username": "yassine.food",
        "email": "yassine.food@seed.boufbouf.local",
        "display_name": "Yassine",
        "country": "Morocco",
        "language": "fr",
        "handle": "@yassine.food",
    },
    {
        "username": "nadia.cuisine",
        "email": "nadia.cuisine@seed.boufbouf.local",
        "display_name": "Nadia",
        "country": "Morocco",
        "language": "fr",
        "handle": "@nadia.cuisine",
    },
    {
        "username": "linamange",
        "email": "linamange@seed.boufbouf.local",
        "display_name": "Lina",
        "country": "Morocco",
        "language": "fr",
        "handle": "@linamange",
    },
    {
        "username": "douceurs.meryem",
        "email": "douceurs.meryem@seed.boufbouf.local",
        "display_name": "Meryem",
        "country": "Morocco",
        "language": "fr",
        "handle": "@douceurs.meryem",
    },
    {
        "username": "oum.salma",
        "email": "oum.salma@seed.boufbouf.local",
        "display_name": "Oum Salma",
        "country": "Morocco",
        "language": "fr",
        "handle": "@oum.salma",
    },
    {
        "username": "ines.au.soleil",
        "email": "ines.au.soleil@seed.boufbouf.local",
        "display_name": "Inès",
        "country": "Morocco",
        "language": "fr",
        "handle": "@ines.au.soleil",
    },
    {
        "username": "kenji.table",
        "email": "kenji.table@seed.boufbouf.local",
        "display_name": "Kenji",
        "country": "Japan",
        "language": "fr",
        "handle": "@kenji.table",
    },
    {
        "username": "soukaina.b",
        "email": "soukaina.b@seed.boufbouf.local",
        "display_name": "Soukaina",
        "country": "Morocco",
        "language": "fr",
        "handle": "@soukaina.b",
    },
    {
        "username": "chef.rachid",
        "email": "chef.rachid@seed.boufbouf.local",
        "display_name": "Chef Rachid",
        "country": "Morocco",
        "language": "fr",
        "handle": "@chef.rachid",
    },
]


def upgrade() -> None:
    """Create development users and link existing videos."""

    connection = op.get_bind()

    connection.execute(
        sa.text(
            """
            INSERT INTO users (
                username,
                email,
                password_hash,
                display_name,
                bio,
                avatar_url,
                country,
                language,
                created_at
            )
            VALUES
                (
                    'rania.cuisine',
                    'rania.cuisine@seed.boufbouf.local',
                    :password_hash,
                    'Rania',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'yassine.food',
                    'yassine.food@seed.boufbouf.local',
                    :password_hash,
                    'Yassine',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'nadia.cuisine',
                    'nadia.cuisine@seed.boufbouf.local',
                    :password_hash,
                    'Nadia',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'linamange',
                    'linamange@seed.boufbouf.local',
                    :password_hash,
                    'Lina',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'douceurs.meryem',
                    'douceurs.meryem@seed.boufbouf.local',
                    :password_hash,
                    'Meryem',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'oum.salma',
                    'oum.salma@seed.boufbouf.local',
                    :password_hash,
                    'Oum Salma',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'ines.au.soleil',
                    'ines.au.soleil@seed.boufbouf.local',
                    :password_hash,
                    'Inès',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'kenji.table',
                    'kenji.table@seed.boufbouf.local',
                    :password_hash,
                    'Kenji',
                    '',
                    NULL,
                    'Japan',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'soukaina.b',
                    'soukaina.b@seed.boufbouf.local',
                    :password_hash,
                    'Soukaina',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                ),
                (
                    'chef.rachid',
                    'chef.rachid@seed.boufbouf.local',
                    :password_hash,
                    'Chef Rachid',
                    '',
                    NULL,
                    'Morocco',
                    'fr',
                    CURRENT_TIMESTAMP
                )
            """
        ),
        {"password_hash": DEV_PASSWORD_HASH},
    )

    op.add_column(
        "videos",
        sa.Column(
            "creator_id",
            sa.Integer(),
            nullable=True,
        ),
    )

    op.create_index(
        "ix_videos_creator_id",
        "videos",
        ["creator_id"],
        unique=False,
    )

    op.create_foreign_key(
        "fk_videos_creator_id_users",
        "videos",
        "users",
        ["creator_id"],
        ["id"],
        ondelete="CASCADE",
    )

    for user in SEED_USERS:
        connection.execute(
            sa.text(
                """
                UPDATE videos
                SET creator_id = users.id
                FROM users
                WHERE users.username = :username
                  AND videos.creator_handle = :handle
                """
            ),
            {
                "username": user["username"],
                "handle": user["handle"],
            },
        )

    missing = connection.execute(
        sa.text(
            """
            SELECT COUNT(*)
            FROM videos
            WHERE creator_id IS NULL
            """
        )
    ).scalar_one()

    if missing != 0:
        raise RuntimeError(
            f"Migration aborted: {missing} video(s) could not be linked "
            "to a user."
        )

    op.alter_column(
        "videos",
        "creator_id",
        existing_type=sa.Integer(),
        nullable=False,
    )

    op.drop_column("videos", "creator_name")
    op.drop_column("videos", "creator_handle")