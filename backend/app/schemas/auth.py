from pydantic import BaseModel, EmailStr, Field


class RegisterRequest(BaseModel):
    username: str = Field(min_length=3, max_length=50)
    email: EmailStr
    phone_number: str | None = Field(default=None, max_length=20)
    password: str = Field(min_length=8, max_length=128)
    display_name: str = Field(min_length=1, max_length=100)
    country: str = Field(default="", max_length=100)
    language: str = Field(default="fr", max_length=10)


class UserResponse(BaseModel):
    id: int
    username: str
    email: EmailStr
    phone_number: str | None
    display_name: str
    bio: str
    avatar_url: str | None
    country: str
    language: str

    model_config = {"from_attributes": True}


class LoginRequest(BaseModel):
    email_or_phone: str = Field(min_length=3, max_length=255)
    password: str = Field(min_length=8, max_length=128)


class TokenResponse(BaseModel):
    access_token: str
    token_type: str = "bearer"
