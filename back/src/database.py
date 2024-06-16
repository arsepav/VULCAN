from sqlalchemy import create_engine, MetaData
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import json

with open('config.json') as f:
    d = json.load(f)

DATABASE_URL = d['connection_string']

engine = create_engine(DATABASE_URL, pool_size=40)

SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()
metadata = MetaData()

def get_db():
    yield SessionLocal()
