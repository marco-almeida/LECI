from . import db
from flask_login import UserMixin
from sqlalchemy.sql import func

class User(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(150), unique=True)
    username = db.Column(db.String(150), unique=True)
    fullname = db.Column(db.String(150))
    password = db.Column(db.String(150))

class Doctor(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(150))
    phone = db.Column(db.String(150))
    email = db.Column(db.String(150))

class ContactForm(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    fname = db.Column(db.String(150))
    lname = db.Column(db.String(150))
    email = db.Column(db.String(150))
    phone = db.Column(db.String(150))
    message = db.Column(db.String(10000))

class Appointment(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    patient = db.Column(db.String(150))
    doctor = db.Column(db.String(150))
    date = db.Column(db.String(150))
    time = db.Column(db.String(150))

class Post(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(150))
    content = db.Column(db.String(10000))
    date = db.Column(db.DateTime(timezone=True), default=func.now())
    user_username = db.Column(db.Integer, db.ForeignKey('user.username'))