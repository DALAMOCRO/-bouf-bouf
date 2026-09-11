from sqlalchemy.orm import Session

from app.repositories.video import get_feed_videos
from app.schemas.video import VideoResponse


def get_feed(
    db: Session,
    limit: int = 20,
    offset: int = 0,
) -> list[VideoResponse]:
    videos = get_feed_videos(
        db=db,
        limit=limit,
        offset=offset,
    )

    return [
        VideoResponse(
            id=str(video.id),
            creatorName=video.creator.display_name,
            creatorHandle=f"@{video.creator.username}",
            description=video.description,
            hashtags=[
                hashtag.strip()
                for hashtag in video.hashtags.split(",")
                if hashtag.strip()
            ],
            videoUrl=video.video_url,
            likes=video.likes,
            comments=video.comments,
        )
        for video in videos
    ]