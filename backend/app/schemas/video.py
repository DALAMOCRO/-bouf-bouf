from pydantic import BaseModel


class VideoResponse(BaseModel):
    id: str
    creatorName: str
    creatorHandle: str
    description: str
    hashtags: list[str]
    videoUrl: str
    likes: int
    comments: int