from fastapi import APIRouter

router = APIRouter()


@router.get("/health", tags=["operations"])
async def healthcheck() -> dict[str, str]:
    """Liveness endpoint; database readiness will be added with persistence."""
    return {"status": "ok"}
