from typing import Any, Dict, List, Tuple, Optional
from pydantic import BaseModel, EmailStr, Field
from datetime import date, datetime


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
    oopt_id: int
    category_id: int


class OOPT(BaseModel):
    id: int
    name: str
    description: str
    geom: dict
    image_url: str


class GetOOPTs(BaseModel):
    OOPT_name: str


class VisitPermissionIndividualCreate(BaseModel):
    arrival_date: date
    surname: str
    name: str
    lastname: Optional[str] = ''
    birthday: date
    citizenship: int
    isMale: bool
    passport: str
    email: EmailStr
    phone_number: str
    path_id: int
    is_one_day_only: bool
    purpose_skis: bool
    purpose_sport: bool
    purpose_science: bool
    purpose_photo_video: bool
    purpose_mountaineering: bool
    purpose_another: bool
    photo_video_professional: bool
    photo_video_drones: bool


class GeoPathResponse(BaseModel):
    id: int
    name: str
    geom: dict
    oopt_id: int
    category_id: int

    class Config:
        orm_mode = True


class GeoPathAddResponse(BaseModel):
    id: int

    class Config:
        orm_mode = True


class Country(BaseModel):
    id: int
    name: str

    class Config:
        orm_mode = True


class VisitPermissionIndividualResponse(BaseModel):
    id: int
    arrival_date: date
    surname: str
    name: str
    lastname: str
    birthday: date
    citizenship: int
    isMale: bool
    passport: str
    email: EmailStr
    phone_number: str
    path_id: int
    is_one_day_only: bool
    purpose_skis: bool
    purpose_sport: bool
    purpose_science: bool
    purpose_photo_video: bool
    purpose_mountaineering: bool
    purpose_another: bool
    photo_video_professional: bool
    photo_video_drones: bool
    reviewed: bool
    approved: bool
    reviewer: Optional[int]
    date_of_creation: datetime

    class Config:
        orm_mode = True


class EcologyProblemCreate(BaseModel):
    name: str
    description: str
    geom: Dict[str, Any]
    category_id: int
    file_id: int


class EcologyProblemResponse(BaseModel):
    id: int
    name: str
    description: str
    geom: Dict[str, Any]
    reporter_id: int
    category_id: int
    image_url: str
    state_id: int

    class Config:
        orm_mode = True


class EcologyProblemCategory(BaseModel):
    id: int
    name: str
    description: str

    image_url: str

    class Config:
        orm_mode = True


class EcologyProblemState(BaseModel):
    id: int
    name: str
    description: str

    class Config:
        orm_mode = True

class ReportCreate(BaseModel):
    name: str
    description: str
    new_state: int
    file_id: int


class EcologyProblemReportResponse(BaseModel):
    id: int
    name: str
    description: str
    old_state: int
    new_state: int
    ecology_problem_id: int
    file_id: int
    reporter_id: int

    class Config:
        orm_mode = True


class EcologyProblemReportCreate(BaseModel):
    name: str
    description: str
    new_state: int
    file_id: int
    ecology_problem_id: int


class OOPTObjectCategories(BaseModel):
    id: int

    name: str
    description: str
    image_url: str

class OOPTObjectsCreate(BaseModel):
    name: str
    description: str
    geom: dict

    file_id: int
    oopt_id: int
    category_id: int

    area: float
    person_area: float
    Rf_coefficient: float
    t_coefficient: float
