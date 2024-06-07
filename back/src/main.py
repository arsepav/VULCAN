from fastapi import FastAPI, Depends, HTTPException, status, Request
from geoalchemy2.shape import from_shape
from fastapi.responses import HTMLResponse, RedirectResponse
from shapely import Polygon, LineString
from shapely.geometry import shape, Point
from sqlalchemy.orm import Session
from . import models, schemas, auth, database
from datetime import datetime, timedelta
from fastapi.middleware.cors import CORSMiddleware
from fastapi.templating import Jinja2Templates
import json
models.Base.metadata.create_all(bind=database.engine)

from pathlib import Path

BASE_DIR = Path(__file__).resolve().parent

with open('config.json') as f:
    d = json.load(f)

templates = Jinja2Templates(directory='../front')

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.post("/register", response_model=schemas.UserResponse)
def register_user(user: schemas.UserCreate, db: Session = Depends(auth.get_db)):
    hashed_password = auth.get_password_hash(user.password)
    db_user = models.User(username=user.username, email=user.email, hashed_password=hashed_password)
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user


@app.post("/token")
def login_for_access_token(form_data: schemas.UserLogin, db: Session = Depends(auth.get_db)):
    user = auth.authenticate_user(db, form_data.username, form_data.password)
    if not user:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Incorrect username or password",
            headers={"WWW-Authenticate": "Bearer"},
        )
    access_token_expires = timedelta(minutes=auth.ACCESS_TOKEN_EXPIRE_MINUTES)
    access_token = auth.create_access_token(
        data={"sub": user.username}, expires_delta=access_token_expires
    )
    return {"access_token": access_token, "token_type": "bearer"}


@app.get("/users/me", response_model=schemas.UserResponse)
async def read_users_me(current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    return current_user


@app.post("/add_point", response_model=schemas.GeoPointResponse)
async def add_geo_polygon(point_data: schemas.GeoPointAdd, db: Session = Depends(database.get_db),
                          current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    try:
        point = Point(point_data.geom)
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Invalid GeoJSON data: {str(e)}")

    db_point = models.GeoPoints(name=point_data.name, geom=from_shape(point, srid=4326))
    db.add(db_point)
    db.commit()

    return schemas.GeoPolygonResponse(id=db_point.id)


@app.post("/add_polygon", response_model=schemas.GeoPolygonResponse)
async def add_geo_polygon(polygon_data: schemas.GeoPolygonAdd, db: Session = Depends(database.get_db),
                          current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    try:
        polygon = shape(polygon_data.geom)
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Invalid GeoJSON data: {str(e)}")

    db_polygon = models.GeoPolygons(name=polygon_data.name, geom=from_shape(polygon, srid=4326))
    db.add(db_polygon)
    db.commit()

    return schemas.GeoPolygonResponse(id=db_polygon.id)


@app.post("/add_path", response_model=schemas.GeoPathResponse)
async def add_path(path_data: schemas.GeoPathAdd, db: Session = Depends(database.get_db),
                   current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    try:
        path = LineString(path_data.geom)
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Invalid path data: {str(e)}")

    db_path = models.GeoPaths(name=path_data.name, geom=from_shape(path, srid=4326))
    db.add(db_path)
    db.commit()
    db.refresh(db_path)

    return schemas.GeoPathResponse(id=db_path.id)


@app.get("/login", response_class=HTMLResponse)
async def get_login_page(request: Request):
    ip = d['ip']

    print(ip)

    return templates.TemplateResponse("login.html", {"request": request, 'ip': ip})


@app.get("/map", response_class=HTMLResponse)
async def get_map_page(request: Request, current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie)):
    if not current_user:
        return RedirectResponse(url="/login")
    return templates.TemplateResponse("map.html", {"request": request, "ip": d['ip']})