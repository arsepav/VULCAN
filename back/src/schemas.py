from pydantic import BaseModel
from typing import Any, Dict, List, Tuple

class UserBase(BaseModel):
    username: str
    email: str

class UserCreate(UserBase):
    password: str

class UserResponse(UserBase):
    id: int

    class Config:
        orm_mode: True

class UserLogin(BaseModel):
    username: str
    password: str

class GeoPointAdd(BaseModel):
    name: str
    geom: Tuple[float, float]

class GeoPointResponse(BaseModel):
    id: int
    class Config:
        orm_mode = True

class GeoPolygonAdd(BaseModel):
    name: str
    geom: Dict[str, Any]
    description: str
    category_id: int

class GeoPolygonResponse(BaseModel):
    id: int
    class Config:
        orm_mode = True

class GeoPathAdd(BaseModel):
    name: str
    geom: List[Tuple[float, float]]

class GeoPathResponse(BaseModel):
    id: int
    class Config:
        orm_mode = True


class OOPT(BaseModel):
    id: int
    name: str
    description: str
    geom: dict
    image_url: str

class GetOOPTs(BaseModel):
    OOPT_name: str