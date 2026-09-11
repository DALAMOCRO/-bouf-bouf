from app.api.v1.auth import router as auth_router
from fastapi import APIRouter, Depends, Query
from sqlalchemy.orm import Session

from app.db.database import get_db
from app.services.videos import get_feed as get_feed_service
from app.schemas.video import VideoResponse


router = APIRouter()


@router.get("/health", tags=["operations"])
async def healthcheck() -> dict[str, str]:
    """Liveness endpoint."""
    return {"status": "ok"}


@router.get(
    "/feed",
    response_model=list[VideoResponse],
    tags=["feed"],
)
def get_feed(
    limit: int = Query(default=20, ge=1, le=50),
    offset: int = Query(default=0, ge=0),
    db: Session = Depends(get_db),
) -> list[VideoResponse]:
    return get_feed_service(
        db=db,
        limit=limit,
        offset=offset,
    )

router.include_router(auth_router)