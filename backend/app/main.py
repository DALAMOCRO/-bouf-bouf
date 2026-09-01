from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.api.v1.router import router as v1_router
from app.core.config import get_settings

settings = get_settings()
app = FastAPI(title="Bouf-Bouf API", version="0.1.0", docs_url=None if settings.app_env == "production" else "/docs")

app.add_middleware(
    CORSMiddleware,
    allow_origins=settings.cors_origins,
    allow_credentials=True,
    allow_methods=["GET", "POST", "PATCH", "PUT", "DELETE"],
    allow_headers=["Authorization", "Content-Type", "X-Request-ID"],
)
app.include_router(v1_router, prefix="/api/v1")
