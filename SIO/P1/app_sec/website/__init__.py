# having a folder with a __init__.py file makes it a package

from flask import Flask, render_template
from flask_sqlalchemy import SQLAlchemy
from werkzeug.security import generate_password_hash
from flask_login import LoginManager

db = SQLAlchemy()
DB_NAME = "database.db"

def page_not_found(e):
    return render_template('404.html'), 404

def create_app():
    app = Flask(__name__)
    app.config['SECRET_KEY'] = 'queimjo'
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{DB_NAME}'
    db.init_app(app)

    from .views import views
    from .auth import auth

    app.register_blueprint(views, url_prefix='/')
    app.register_blueprint(auth, url_prefix='/')

    from .models import User, ContactForm, Doctor, Appointment, Post

    with app.app_context():
        db.drop_all()
        db.create_all()
        ############## INITIALIZE CONTACT FORMS ################
        new_note = ContactForm(fname="Antonio", lname="Bicicleta", email="toniobicla132@gmail.com", phone="912332132", message="Não tenho nada pa dizer. Que lindo site que você fez")
        new_note_2 = ContactForm(fname="Maria", lname="Amélia", email="mariaamelia@hotmail.com", phone="917345273", message="Boa tarde, gostaria de saber o vosso horário de funcionamento. Obrigada!")
        db.session.add(new_note)
        db.session.add(new_note_2)
        ############## INITIALIZE USERS ################
        new_user = User(email="adminmail@asd.com", username="admin", fullname="admin", password=generate_password_hash('admin', method='sha256'))
        new_user_2 = User(email="zemanel123@hotmail.com", username="zemanel", fullname="José Manuel Figueiras", password=generate_password_hash('zemanel123', method='sha256'))
        new_user_3 = User(email="toniocosta@gmail.com", username="antonio", fullname="António Costa Silva", password=generate_password_hash('qwertyops', method='sha256'))
        db.session.add(new_user)
        db.session.add(new_user_2)
        db.session.add(new_user_3)
        ############# INITIALIZE DOCTORS ################
        new_doctor = Doctor(name="Lillian Curry", phone="915794014", email="c@ehealth.com")
        new_doctor_2 = Doctor(name="Esther Holloway", phone="918226120", email="eh@ehealth.com")
        new_doctor_3 = Doctor(name="Caleb Bennett", phone="914389307", email="cb@ehealth.com")
        new_doctor_4 = Doctor(name="Mildred Dixon", phone="913641581", email="md@ehealth.com")
        new_doctor_5 = Doctor(name="Jacqueline Ellis", phone="910691821", email="je@ehealth.com")
        new_doctor_6 = Doctor(name="Ken Mccoy", phone="914618330", email="km@ehealth.com")
        new_doctor_7 = Doctor(name="Shannon Becker", phone="917753073", email="sb@ehealth.com")
        new_doctor_8 = Doctor(name="Amber Huff", phone="915641953", email="ah@ehealth.com")
        new_doctor_9 = Doctor(name="Kristi Neal", phone="914980173", email="kn@ehealth.com")
        new_doctor_10 = Doctor(name="Penny Gonzales", phone="914596191", email="pg@ehealth.com")
        db.session.add(new_doctor)
        db.session.add(new_doctor_2)
        db.session.add(new_doctor_3)
        db.session.add(new_doctor_4)
        db.session.add(new_doctor_5)
        db.session.add(new_doctor_6)
        db.session.add(new_doctor_7)
        db.session.add(new_doctor_8)
        db.session.add(new_doctor_9)
        db.session.add(new_doctor_10)
        ############# INITIALIZE APPOINTMENTS ################
        new_appointment = Appointment(patient="admin", doctor="Shannon Becker", date="2020-12-12", time="17:30")
        new_appointment_2 = Appointment(patient="admin", doctor="Amber Huff", date="2021-01-06", time="09:00")
        new_appointment_3 = Appointment(patient="zemanel", doctor="Ken Mccoy", date="2022-03-07", time="14:00")
        db.session.add(new_appointment)
        db.session.add(new_appointment_2)
        db.session.add(new_appointment_3)
        ######################################################
        new_post = Post(title="We need doctors", content="We are hiring doctors specializing in podiatry. Please contact us and submit your curriculum vitae!", user_username='admin')
        db.session.add(new_post)
        db.session.commit()
        print('Created Database!')

    login_manager = LoginManager()
    login_manager.login_view = "auth.login"
    login_manager.init_app(app)

    @login_manager.user_loader
    def load_user(id):
        return User.query.get(int(id))

    #app.register_error_handler(404, page_not_found)
    return app
