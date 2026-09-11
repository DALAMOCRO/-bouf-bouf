from sqlalchemy import select
from sqlalchemy.orm import Session, selectinload

from app.models.video import Video


def get_feed_videos(
    db: Session,
    limit: int = 20,
    offset: int = 0,
) -> list[Video]:
    statement = (
        select(Video)
        .options(selectinload(Video.creator))
        .order_by(Video.created_at.desc())
        .offset(offset)
        .limit(limit)
    )

    return list(db.scalars(statement).all())