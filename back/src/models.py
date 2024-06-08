from sqlalchemy import Column, Integer, ForeignKey, String, DateTime
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
    category_id = Column(Integer, ForeignKey('geo_points_category.id'))

class GeoPointsCategory(Base):
    __tablename__ = 'geo_points_category'

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True, index=True)
    description = Column(String)
    image_url = Column(String)


class GeoPolygons(Base):
    __tablename__ = 'geo_polygons'

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    geom = Column(Geometry(geometry_type='POLYGON', srid=4326))
    category_id = Column(Integer, ForeignKey('geo_polygons_category.id'))


class GeoPolygonsCategory(Base):
    __tablename__ = 'geo_polygons_category'

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True, index=True)
    description = Column(String)
    image_url = Column(String)

class GeoPaths(Base):
    __tablename__ = 'geo_paths'

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, index=True)
    geom = Column(Geometry(geometry_type='LINESTRING', srid=4326))
    category_id = Column(Integer, ForeignKey('geo_paths_category.id'))

class GeoPathsCategory(Base):
    __tablename__ = 'geo_paths_category'

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True, index=True)
    description = Column(String)
    image_url = Column(String)

class FilesInfo(Base):
    __tablename__ = "files_info"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String)
    extension = Column(String)
