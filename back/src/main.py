from fastapi import FastAPI, Depends, HTTPException, status, File, UploadFile, Request, Query
from geoalchemy2.shape import from_shape
from fastapi.responses import HTMLResponse, RedirectResponse, JSONResponse, FileResponse
from shapely import Polygon, LineString
from shapely.geometry import shape, Point
from sqlalchemy.orm import Session
from . import models, schemas, auth, database
from datetime import datetime, timedelta
from fastapi.middleware.cors import CORSMiddleware
from fastapi.templating import Jinja2Templates
import json
from os.path import join, exists
import shutil
from typing import List
from .processing import null_to_empty, wkb_element_to_wkt
from typing import List, Optional
from fastapi.staticfiles import StaticFiles

models.Base.metadata.create_all(bind=database.engine)

with open('config.json') as f:
    d = json.load(f)

templates = Jinja2Templates(directory='../front')

UPLOAD_DIRECTORY = d['upload_dir']

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.mount("/static", StaticFiles(directory="../static"), name="static")


@app.post("/register", response_model=schemas.UserResponse)
def register_user(user: schemas.UserCreate, db: Session = Depends(auth.get_db)):
    users = db.query(models.User).where(models.User.username == user.username).all()

    if not users:
        hashed_password = auth.get_password_hash(user.password)
        db_user = models.User(username=user.username,
                              email=user.email,
                              hashed_password=hashed_password,
                              name=user.name,
                              surname=user.surname,
                              lastname=user.lastname,
                              phone_number=user.phone_number
                              )
        db.add(db_user)
        db.commit()
        db.refresh(db_user)
        return {'id': db_user.id,
                'success': True,
                'message': 'ok',
                'username': user.username,
                'email': user.email,
                'name': user.name,
                'surname': user.surname,
                'lastname': user.lastname,
                'phone_number': user.phone_number,
                'avatar_url': 'null'
                }

    return {'id': -1,
            'success': False,
            'message': 'user with such name or email already exists',
            'username': user.username,
            'email': user.email,
            'name': user.name,
            'surname': user.surname,
            'lastname': user.lastname,
            'phone_number': user.phone_number,
            'avatar_url': 'null'
            }


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
    response = JSONResponse(content={"access_token": access_token,
                                     "token_type": "bearer",
                                     'email': user.email,
                                     'name': user.name,
                                     'surname': user.surname,
                                     'lastname': user.lastname,
                                     'phone_number': user.phone_number,
                                     'avatar_url': f"http://{d['ip']}:8000/downloadfile/{user.avatar_file_id}" if user.avatar_file_id else 'null'
                                     })
    response.set_cookie(key="access_token", value=access_token, httponly=True, samesite="lax")
    return response


@app.post("/update_avatar")
def login_for_access_token(file_data: schemas.UpdateAvatar, db: Session = Depends(auth.get_db),
                           current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    user = db.query(models.User).filter(models.User.id == current_user.id).first()
    user.avatar_file_id = file_data.file_id

    db.commit()
    return {'status': 'ok'}


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

    db_polygon = models.GeoPolygons(name=polygon_data.name,
                                    geom=from_shape(polygon, srid=4326),
                                    description=polygon_data.description,
                                    category_id=polygon_data.category_id)
    db.add(db_polygon)
    db.commit()

    return schemas.GeoPolygonResponse(id=db_polygon.id)


@app.post("/add_path", response_model=schemas.GeoPathAddResponse)
async def add_path(path_data: schemas.GeoPathAdd, db: Session = Depends(database.get_db),
                   current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    try:
        path = LineString(path_data.geom)
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Invalid path data: {str(e)}")

    db_path = models.GeoPaths(name=path_data.name,
                              geom=from_shape(path, srid=4326),
                              category_id=path_data.category_id,
                              oopt_id=path_data.oopt_id)

    db.add(db_path)
    db.commit()
    db.refresh(db_path)

    for i in path_data.points:
        db.add(models.GeoPathPoints(path_id=db_path.id, point_id=i))

    db.commit()

    return schemas.GeoPathAddResponse(id=db_path.id)


@app.get("/login", response_class=HTMLResponse)
async def get_login_page(request: Request):
    ip = d['ip']

    print(ip)

    return templates.TemplateResponse("login.html", {"request": request, 'ip': ip})


@app.get("/", response_class=HTMLResponse)
async def get_map_page(request: Request, current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie)):
    if not current_user:
        return RedirectResponse(url="/login")
    return templates.TemplateResponse("home.html", {"request": request, "ip": d['ip']})


@app.get("/map", response_class=HTMLResponse)
async def get_map_page(request: Request, current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie)):
    if not current_user:
        return RedirectResponse(url="/login")
    return templates.TemplateResponse("map.html", {"request": request, "ip": d['ip']})


@app.get("/map_oopt", response_class=HTMLResponse)
async def get_map_page(request: Request, current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie)):
    if not current_user:
        return RedirectResponse(url="/login")
    return templates.TemplateResponse("map_oopt.html", {"request": request, "ip": d['ip']})


@app.get("/map_objects_creation", response_class=HTMLResponse)
async def get_map_page(request: Request, current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie)):
    if not current_user:
        return RedirectResponse(url="/login")
    return templates.TemplateResponse("map_objects_creation.html", {"request": request, "ip": d['ip']})


@app.get("/ecology_problems_list_viewer", response_class=HTMLResponse)
async def get_map_page(
        request: Request,
        ecology_problem_id: Optional[int] = None,
        current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie)
):
    if not current_user:
        return RedirectResponse(url="/login")

    return templates.TemplateResponse(
        "ecology_problems_list_viewer.html",
        {
            "request": request,
            "ip": d['ip'],
            "ecology_problem_id": ecology_problem_id if ecology_problem_id else 'null'
        }
    )


@app.get("/ecology_problems_viewer", response_class=HTMLResponse)
async def get_map_page(
        request: Request,
        ecology_problem_id: Optional[int] = Query(None),
        current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie),
        db: Session = Depends(database.get_db)
):
    if not current_user:
        return RedirectResponse(url="/login")

    if ecology_problem_id:
        problem = db.query(models.EcologyProblems).filter(models.EcologyProblems.id == ecology_problem_id).first()
        if problem:
            problem.geom = wkb_element_to_wkt(problem.geom)
            print(problem.geom)
            center_lat = problem.geom['coordinates'][1]
            center_lng = problem.geom['coordinates'][0]
            zoom_level = 9
        else:
            center_lat = 55.914298
            center_lng = 158.918540
            zoom_level = 6
    else:
        center_lat = 55.914298
        center_lng = 158.918540
        zoom_level = 6

    return templates.TemplateResponse("ecology_problem_viewer.html", {
        "request": request,
        "center_lat": center_lat,
        "center_lng": center_lng,
        "zoom_level": zoom_level,
        "ip": d['ip'],
        "highlighted_problem_id": ecology_problem_id
    })


@app.get("/permission_approve_page", response_class=HTMLResponse)
async def get_permissions_page(request: Request,
                               current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie)):
    if not current_user:
        return RedirectResponse(url="/login")
    return templates.TemplateResponse("permission_approve.html", {"request": request, "ip": d['ip']})


@app.get("/upload_file_page/", response_class=HTMLResponse)
async def read_upload_file(request: Request):
    return templates.TemplateResponse("upload.html", {"request": request})


@app.post("/uploadfile/")
async def create_upload_file(file: UploadFile = File(...), db: Session = Depends(database.get_db)):
    db_file = models.FilesInfo(name=file.filename, extension=file.filename.split('.')[-1])
    db.add(db_file)
    db.commit()

    file_location = join(UPLOAD_DIRECTORY, f'file_{db_file.id}')
    with open(file_location, "wb") as buffer:
        shutil.copyfileobj(file.file, buffer)

    return JSONResponse(content={"file_id": db_file.id})


@app.get("/downloadfile/{file_id}")
async def download_file(file_id: int, db: Session = Depends(database.get_db)):
    db_file = db.query(models.FilesInfo).filter(models.FilesInfo.id == file_id).first()
    if not db_file:
        raise HTTPException(status_code=404, detail="File not found")

    file_location = join(UPLOAD_DIRECTORY, f'file_{db_file.id}')
    if not exists(file_location):
        raise HTTPException(status_code=404, detail="File not found on disk")

    return FileResponse(path=file_location, filename=db_file.name, media_type='application/octet-stream')


@app.post("/oopts", response_model=List[schemas.OOPT])
def read_oopts(oopt_info: schemas.GetOOPTs, db: Session = Depends(database.get_db)):
    oopts = (db.query(models.GeoPolygons, models.GeoPolygonsCategory.image_url)
             .filter(models.GeoPolygons.category_id == 1)
             .filter(models.GeoPolygons.name.ilike(f"%{oopt_info.OOPT_name}%"))
             .join(models.GeoPolygonsCategory)
             .all())

    response = []
    for oopt in oopts:
        oopt_dict = oopt[0].__dict__
        oopt_dict['image_url'] = oopt[1]
        oopt_dict['geom'] = wkb_element_to_wkt(oopt_dict['geom'])
        oopt_dict = null_to_empty(oopt_dict)
        response.append(schemas.OOPT(**oopt_dict))

    return response


@app.post("/create_visit_permission/", response_model=schemas.VisitPermissionIndividualCreate)
async def create_visit_permission_individual(visit_permission_data: schemas.VisitPermissionIndividualCreate,
                                             db: Session = Depends(database.get_db)):
    # Create a new VisitPermissionIndividual instance
    db_visit_permission = models.VisitPermissionIndividual(
        arrival_date=visit_permission_data.arrival_date,
        surname=visit_permission_data.surname,
        name=visit_permission_data.name,
        lastname=visit_permission_data.lastname,
        birthday=visit_permission_data.birthday,
        citizenship=visit_permission_data.citizenship,
        isMale=visit_permission_data.isMale,
        passport=visit_permission_data.passport,
        email=visit_permission_data.email,
        phone_number=visit_permission_data.phone_number,
        path_id=visit_permission_data.path_id,
        is_one_day_only=visit_permission_data.is_one_day_only,
        purpose_skis=visit_permission_data.purpose_skis,
        purpose_sport=visit_permission_data.purpose_sport,
        purpose_science=visit_permission_data.purpose_science,
        purpose_photo_video=visit_permission_data.purpose_photo_video,
        purpose_mountaineering=visit_permission_data.purpose_mountaineering,
        purpose_another=visit_permission_data.purpose_another,
        photo_video_professional=visit_permission_data.photo_video_professional,
        photo_video_drones=visit_permission_data.photo_video_drones
    )

    # Add the new instance to the session and commit
    db.add(db_visit_permission)
    db.commit()
    db.refresh(db_visit_permission)

    return db_visit_permission


@app.get("/geopaths/", response_model=List[schemas.GeoPathResponse])
async def get_geopaths(oopt_id: Optional[int] = Query(None), db: Session = Depends(database.get_db)):
    if oopt_id is not None:
        geopaths = db.query(models.GeoPaths).filter(models.GeoPaths.oopt_id == oopt_id).all()
    else:
        geopaths = db.query(models.GeoPaths).all()

    for i in range(len(geopaths)):
        geopaths[i].geom = wkb_element_to_wkt(geopaths[i].geom)

    return geopaths


@app.get("/countries/", response_model=List[schemas.Country])
def read_countries(name_part: Optional[str] = None, db: Session = Depends(database.get_db)):
    if name_part:
        countries = db.query(models.Countries).filter(models.Countries.name.ilike(f"%{name_part}%")).all()
    else:
        countries = db.query(models.Countries).all()
    return countries


@app.get("/visit_permissions/", response_model=List[schemas.VisitPermissionIndividualResponse])
def get_visit_permissions(
        not_reviewed_only: Optional[bool] = Query(None), db: Session = Depends(database.get_db)
):
    permissions_query = db.query(models.VisitPermissionIndividual, models.GeoPaths.name, models.Countries.name,
                                 models.GeoPaths.oopt_id)
    permissions_query = permissions_query.join(models.GeoPaths, models.GeoPaths.id == models.VisitPermissionIndividual.path_id)
    permissions_query = permissions_query.join(models.Countries, models.Countries.id == models.VisitPermissionIndividual.citizenship)

    if not_reviewed_only:
        permissions_query = permissions_query.filter(
            models.VisitPermissionIndividual.reviewed == False).all()

    permissions = permissions_query.order_by(
        models.VisitPermissionIndividual.date_of_creation).limit(50).all()

    for i in range(len(permissions)):
        s = permissions[i]
        permissions[i] = permissions[i][0].__dict__

        permissions[i]['citizenship_country'] = s[2]
        permissions[i]['path'] = s[1]

        permissions[i]['oopt_load'] = get_oopt_load_func(s[3], permissions[i]['arrival_date'].strftime("%Y-%m-%d"), db)['load_coef']
        permissions[i]['path_load'] = get_path_load_func(permissions[i]['path_id'], permissions[i]['arrival_date'].strftime("%Y-%m-%d"), db)['load_coef']

    return permissions


@app.get("/visit-permissions/{permission_id}", response_model=schemas.VisitPermissionIndividualResponse)
def get_permission(permission_id: int, db: Session = Depends(database.get_db)):
    permission = (db.query(models.VisitPermissionIndividual, models.GeoPaths.name, models.Countries.name,
                                 models.GeoPaths.oopt_id)
                  .join(models.GeoPaths,
                        models.GeoPaths.id == models.VisitPermissionIndividual.path_id)
                  .join(models.Countries,
                        models.Countries.id == models.VisitPermissionIndividual.citizenship)
                  .filter(
        models.VisitPermissionIndividual.id == permission_id).first())


    s = permission
    permission = permission[0].__dict__

    permission['citizenship_country'] = s[2]
    permission['path'] = s[1]

    permission['oopt_load'] = get_oopt_load_func(s[3], permission['arrival_date'].strftime("%Y-%m-%d"), db)[
        'load_coef']
    permission['path_load'] = \
    get_path_load_func(permission['path_id'], permission['arrival_date'].strftime("%Y-%m-%d"), db)['load_coef']

    if not permission:
        raise HTTPException(status_code=404, detail="Permission not found")
    return permission


@app.put("/visit-permissions/{permission_id}/review", response_model=schemas.VisitPermissionIndividualResponse)
def review_permission(permission_id: int, approved: bool, db: Session = Depends(database.get_db),
                      current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    permission = db.query(models.VisitPermissionIndividual).filter(
        models.VisitPermissionIndividual.id == permission_id).first()
    if not permission:
        raise HTTPException(status_code=404, detail="Permission not found")

    permission.reviewed = True
    permission.reviewer = current_user.id
    permission.approved = approved
    db.commit()
    db.refresh(permission)

    permission = permission.__dict__

    permission['oopt_load'] = 0
    permission['path_load'] = 0

    permission['citizenship_country'] = 'Россия'
    permission['path'] = 'Путь'

    return permission


@app.get("/ecology_problem_categories", response_model=List[schemas.EcologyProblemCategory])
def get_categories(db: Session = Depends(database.get_db)):
    categories = db.query(models.EcologyProblemCategories).all()
    for i in range(len(categories)):
        categories[i] = categories[i].__dict__
        categories[i]['image_url'] = f"http://{d['ip']}:8000/downloadfile/{categories[i]['image_file_id']}"
    return categories


@app.get("/ecology_problem_states", response_model=List[schemas.EcologyProblemState])
def get_categories(db: Session = Depends(database.get_db)):
    states = db.query(models.EcologyProblemStates).all()
    return states


@app.post("/ecology_problems", response_model=schemas.EcologyProblemResponse)
def create_ecology_problem(problem: schemas.EcologyProblemCreate, db: Session = Depends(database.get_db),
                           current_user: schemas.UserResponse = Depends(auth.get_current_user)):
    try:
        point = shape(problem.geom)
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Invalid GeoJSON data: {str(e)}")

    new_problem = models.EcologyProblems(
        name=problem.name,
        description=problem.description,
        geom=from_shape(point, srid=4326),
        reporter_id=current_user.id,
        category_id=problem.category_id,
        file_id=problem.file_id,
        state_id=1
    )
    db.add(new_problem)
    db.commit()
    db.refresh(new_problem)

    new_problem = new_problem.__dict__
    new_problem['geom'] = wkb_element_to_wkt(new_problem['geom'])
    new_problem['image_url'] = f"http://{d['ip']}:8000/downloadfile/{new_problem['file_id']}"
    return new_problem


@app.get("/ecology_problems", response_model=List[schemas.EcologyProblemResponseExt])
def get_ecology_problems(
        category_id: Optional[int] = Query(None),
        state_id: Optional[int] = Query(None),
        db: Session = Depends(database.get_db)
):
    query = db.query(models.EcologyProblems, models.EcologyProblemStates.name, models.EcologyProblemCategories.name)

    if category_id is not None:
        query = query.filter(models.EcologyProblems.category_id == category_id)
    if state_id is not None:
        query = query.filter(models.EcologyProblems.state_id == state_id)

    problems = query.join(models.EcologyProblemStates).join(models.EcologyProblemCategories).all()

    for i in range(len(problems)):
        s = problems[i][1]
        c = problems[i][2]
        problems[i] = problems[i][0].__dict__
        problems[i]['geom'] = wkb_element_to_wkt(problems[i]['geom'])
        problems[i]['image_url'] = f"http://{d['ip']}:8000/downloadfile/{problems[i]['file_id']}"
        problems[i]['state'] = s
        problems[i]['category'] = c

    return problems


@app.post("/update_problem_status_and_report")
def update_problem_status_and_report(
        report_data: schemas.EcologyProblemReportCreate,
        db: Session = Depends(database.get_db),
        current_user: schemas.UserResponse = Depends(auth.get_current_user_cookie),
):
    problem = db.query(models.EcologyProblems).filter(
        models.EcologyProblems.id == report_data.ecology_problem_id).first()
    if not problem:
        raise HTTPException(status_code=404, detail="Ecology problem not found")

    new_state = db.query(models.EcologyProblemStates).filter(
        models.EcologyProblemStates.id == report_data.new_state).first()
    if not new_state:
        raise HTTPException(status_code=404, detail="New state not found")

    report = models.EcologyProblemReports(
        name=report_data.name,
        description=report_data.description,
        old_state=problem.state_id,
        new_state=report_data.new_state,
        ecology_problem_id=report_data.ecology_problem_id,
        file_id=report_data.file_id,
        reporter_id=current_user.id,
    )
    db.add(report)

    problem.state_id = report_data.new_state
    problem.responsible_id = report_data.responsible_id

    db.commit()
    return {"message": "Report added and problem status updated successfully"}


@app.get("/oopt_objects_categories", response_model=List[schemas.OOPTObjectCategories])
def get_categories(db: Session = Depends(database.get_db)):
    states = db.query(models.OOPTObjectCategories).all()
    return states


@app.post("/add_oopt_object")
def add_oopt_object(
        oopt_obj_data: schemas.OOPTObjectsCreate,
        db: Session = Depends(database.get_db)
):
    try:
        point = Point(oopt_obj_data.geom['coordinates'])
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Invalid path data: {str(e)}")

    object = models.OOPTObjects(
        name=oopt_obj_data.name,
        description=oopt_obj_data.description,
        geom=from_shape(point, srid=4326),
        file_id=oopt_obj_data.file_id,
        category_id=oopt_obj_data.category_id,
        oopt_id=oopt_obj_data.oopt_id,

        area=oopt_obj_data.area,
        person_area=oopt_obj_data.person_area,
        Rf_coefficient=oopt_obj_data.Rf_coefficient,
        t_coefficient=oopt_obj_data.t_coefficient
    )

    db.add(object)
    db.commit()

    return {'result': 'success', 'id': object.id}


@app.get("/oopt_objects/", response_model=List[schemas.OOPTObject])
def get_oopt_objects(oopt_id: Optional[int] = None, db: Session = Depends(database.get_db)):
    if oopt_id:
        objects = db.query(models.OOPTObjects).filter(models.OOPTObjects.oopt_id == oopt_id).all()
    else:
        objects = db.query(models.OOPTObjects).all()

    for i in range(len(objects)):
        objects[i] = objects[i].__dict__
        objects[i]['geom'] = wkb_element_to_wkt(objects[i]['geom'])
        objects[i]['image_url'] = f"http://{d['ip']}:8000/downloadfile/{objects[i]['file_id']}"
    return objects


def get_oopt_load_func(oopt_id, date, db):
    objects = db.query(models.OOPTObjects).where(models.OOPTObjects.oopt_id == oopt_id).all()

    target_date = datetime.strptime(date, '%Y-%m-%d')

    s = 0

    for obj in objects:
        s += obj.area / obj.person_area * obj.Rf_coefficient * obj.t_coefficient

    if s == 0:
        s = 20

    max_date = target_date + timedelta(days=3)
    min_date = target_date - timedelta(days=4)

    visits = (db.query(models.VisitPermissionIndividual)
              .where(models.VisitPermissionIndividual.reviewed)
              .where(models.VisitPermissionIndividual.approved)
              .where(models.VisitPermissionIndividual.arrival_date <= max_date)
              .where(models.VisitPermissionIndividual.arrival_date >= min_date)
              .join(models.GeoPaths)
              .join(models.GeoPolygons)
              .where(models.GeoPolygons.id == oopt_id)
              .all()
              )

    k = 10

    return {
        'id': oopt_id,
        'load_coef': len(visits) * k / s
    }

@app.get("/oopt_load/", response_model=schemas.OOPTLoad)
def get_oopt_load(oopt_id: int, date: str, db: Session = Depends(database.get_db)):
    return get_oopt_load_func(oopt_id, date, db)


def get_path_load_func(path_id, date, db):
    objects = (db.query(models.OOPTObjects)
               .join(models.GeoPathPoints)
               .where(models.GeoPathPoints.path_id == path_id)
               .all())

    print(objects)

    target_date = datetime.strptime(date, '%Y-%m-%d')

    s = 0

    for obj in objects:
        s += obj.area / obj.person_area * obj.Rf_coefficient * obj.t_coefficient

    if s == 0:
        s = 20

    max_date = target_date + timedelta(days=3)
    min_date = target_date - timedelta(days=4)

    visits = (db.query(models.VisitPermissionIndividual)
              .where(models.VisitPermissionIndividual.reviewed)
              .where(models.VisitPermissionIndividual.approved)
              .where(models.VisitPermissionIndividual.arrival_date <= max_date)
              .where(models.VisitPermissionIndividual.arrival_date >= min_date)
              .join(models.GeoPaths)
              .where(models.GeoPaths.id == path_id)
              .all()
              )

    k = 10

    return {
        'id': path_id,
        'load_coef': len(visits) * k / s
    }


@app.get("/path_load/", response_model=schemas.PathLoad)
def get_path_load(path_id: int, date: str, db: Session = Depends(database.get_db)):
    return get_path_load_func(path_id, date, db)


@app.get("/admins/", response_model=List[schemas.UserResponse])
def get_admins(db: Session = Depends(database.get_db)):
    users = db.query(models.User).where(models.User.is_admin).all()

    result = []
    for user in users:
        result.append({'id': user.id,
                       'username': user.username,
                       'success': True,
                       'message': 'ok',
                       'email': user.email,
                       'name': user.name,
                       'surname': user.surname,
                       'lastname': user.lastname,
                       'phone_number': user.phone_number
                       })
    return result
