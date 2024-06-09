from sqlalchemy import Column, Integer, ForeignKey, String, DateTime, Date, Boolean
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
    description = Column(String)
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
    oopt_id = Column(Integer, ForeignKey('geo_polygons.id'))
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


class Countries(Base):
    __tablename__ = "countries"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String)


class VisitPermissionIndividual(Base):
    __tablename__ = "visit_permission_individual"

    id = Column(Integer, primary_key=True, index=True)

    arrival_date = Column(Date)

    surname = Column(String)
    name = Column(String)
    lastname = Column(String)

    birthday = Column(Date)
    citizenship = Column(Integer, ForeignKey('countries.id'))

    isMale = Column(Boolean)

    passport = Column(String)

    email = Column(String)
    phone_number = Column(String)

    path_id = Column(Integer, ForeignKey('geo_paths.id'))

    is_one_day_only = Column(Boolean)

    purpose_skis = Column(Boolean)
    purpose_sport = Column(Boolean)
    purpose_science = Column(Boolean)
    purpose_photo_video = Column(Boolean)
    purpose_mountaineering = Column(Boolean)
    purpose_another = Column(Boolean)

    photo_video_professional = Column(Boolean)
    photo_video_drones = Column(Boolean)

    reviewed = Column(Boolean, default=False)
    approved = Column(Boolean, default=False)

    reviewer = Column(Integer, ForeignKey('users.id'), default=None)

    date_of_creation = Column(DateTime(timezone=True), server_default=func.now())


class EcologyProblems(Base):
    __tablename__ = "ecology_problems"

    id = Column(Integer, primary_key=True)
    name = Column(String)
    description = Column(String)
    geom = Column(Geometry(geometry_type='POINT', srid=4326))

    reporter_id = Column(Integer, ForeignKey('users.id'))
    category_id = Column(Integer, ForeignKey('ecology_problem_categories.id'))
    file_id = Column(Integer, ForeignKey('files_info.id'))
    state_id = Column(Integer, ForeignKey('ecology_problem_states.id'))


class EcologyProblemCategories(Base):
    __tablename__ = "ecology_problem_categories"

    id = Column(Integer, primary_key=True)
    name = Column(String)
    description = Column(String)

    image_file_id = Column(Integer, ForeignKey('files_info.id'))


class EcologyProblemStates(Base):
    __tablename__ = "ecology_problem_states"

    id = Column(Integer, primary_key=True)
    name = Column(String)
    description = Column(String)


class EcologyProblemReports(Base):
    __tablename__ = "ecology_problem_reports"

    id = Column(Integer, primary_key=True)
    name = Column(String)
    description = Column(String)

    old_state = Column(Integer, ForeignKey('ecology_problem_states.id'))
    new_state = Column(Integer, ForeignKey('ecology_problem_states.id'))

    ecology_problem_id = Column(Integer, ForeignKey('ecology_problems.id'))
    file_id = Column(Integer, ForeignKey('files_info.id'))

    reporter_id = Column(Integer, ForeignKey('users.id'))
    date_of_creation = Column(DateTime(timezone=True), server_default=func.now())
