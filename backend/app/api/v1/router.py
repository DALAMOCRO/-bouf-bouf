from fastapi import APIRouter

router = APIRouter()


@router.get("/health", tags=["operations"])
async def healthcheck() -> dict[str, str]:
    """Liveness endpoint; database readiness will be added with persistence."""
    return {"status": "ok"}


@router.get("/feed", tags=["feed"])
async def get_feed() -> dict:
    return {
        "videos": [
            {
                "id": "video-1",
                "creatorName": "Rania",
                "creatorHandle": "@rania.cuisine",
                "description": "Rissa express, cuisine chez vous.",
                "hashtags": ["#cuisine", "#maroc"],
            },
            {
                "id": "video-2",
                "creatorName": "Yassine",
                "creatorHandle": "@yassine.food",
                "description": "Une recette simple et rapide.",
                "hashtags": ["#food", "#recette"],
            },
        ]
    }
