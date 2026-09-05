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
                "description": "Rfissa express, cuisine chez vous.",
                "hashtags": ["#cuisine", "#maroc"],
            },
            {
                "id": "video-2",
                "creatorName": "Yassine",
                "creatorHandle": "@yassine.food",
                "description": "Une recette simple et rapide.",
                "hashtags": ["#food", "#recette"],
            },
            {
                "id": "video-3",
                "creatorName": "Nadia",
                "creatorHandle": "@nadia.cuisine",
                "description": "Les saveurs marocaines dans votre cuisine.",
                "hashtags": ["#maroc", "#cuisine"],
            },
            {
                "id": "video-4",
                "creatorName": "Lina",
                "creatorHandle": "@linamange",
                "description": "Des pâtes crémeuses en quelques minutes.",
                "hashtags": ["#rapide", "#diner"],
            },
            {
                "id": "video-5",
                "creatorName": "Meryem",
                "creatorHandle": "@douceurs.meryem",
                "description": "Une douceur traditionnelle pour toute la famille.",
                "hashtags": ["#dessert", "#tradition"],
            },
            {
                "id": "video-6",
                "creatorName": "Oum Salma",
                "creatorHandle": "@oum.salma",
                "description": "Une harira généreuse et parfumée.",
                "hashtags": ["#soupe", "#maison"],
            },
            {
                "id": "video-7",
                "creatorName": "Inès",
                "creatorHandle": "@ines.au.soleil",
                "description": "Une recette colorée, simple et économique.",
                "hashtags": ["#vegetarien", "#healthy"],
            },
            {
                "id": "video-8",
                "creatorName": "Kenji",
                "creatorHandle": "@kenji.table",
                "description": "Des makis faciles à préparer à la maison.",
                "hashtags": ["#japon", "#food"],
            },
            {
                "id": "video-9",
                "creatorName": "Soukaina",
                "creatorHandle": "@soukaina.b",
                "description": "Briouates croustillantes pour le ftour.",
                "hashtags": ["#ramadan", "#ftour"],
            },
            {
                "id": "video-10",
                "creatorName": "Chef Rachid",
                "creatorHandle": "@chef.rachid",
                "description": "Le couscous traditionnel du vendredi.",
                "hashtags": ["#couscous", "#maroc"],
            },
        ]
    }
