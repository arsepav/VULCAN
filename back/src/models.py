from sqlalchemy import Column, Integer, String, DateTime
from sqlalchemy.sql import func
from .database import Base
from geoalchemy2 import Geometry


class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    username = Column(String, unique=True, index=True)
    email = Column(String, unique=True, index=True)
    hashed_password = Column(String)
    created_at = Column(DateTime(timezone=True), server_default=func.now())

class GeoPoints(Base):
    __tablename__ = 'geo_points'

    id = Column(Integer, primary_key=True)
    name = Column(String(100))
    geom = Column(Geometry(geometry_type='POINT', srid=4326))

class GeoPolygons(Base):
    __tablename__ = 'geo_polygons'

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    geom = Column(Geometry(geometry_type='POLYGON', srid=4326))

class GeoPaths(Base):
    __tablename__ = 'geo_paths'

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    geom = Column(Geometry(geometry_type='LINESTRING', srid=4326))