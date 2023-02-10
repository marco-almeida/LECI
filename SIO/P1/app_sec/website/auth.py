import re
from flask import Blueprint, render_template, request, flash, redirect, send_file, url_for
from .models import ContactForm, Doctor, Post, User, db, Appointment
from werkzeug.security import generate_password_hash, check_password_hash
from flask_login import login_user, login_required, logout_user, current_user
from fpdf import FPDF
import platform
auth = Blueprint('auth', __name__)
FORBIDDEN_CHARS_DICT = {'/': '\'', '\\': '\'', ':': '\:', '*': '\*',
                        '?': '\?', '"': '\"', '<': '\<', '>': '\>', '|': '\|', '-': '\-'}


def replace_all(text):
    for i, j in FORBIDDEN_CHARS_DICT.items():
        text = text.replace(i, j)
    return text


def create_certification(appt):
    if not appt:
        return False
    open("certification.txt", "w").close()
    with open('certification.txt', 'a') as f:
        f.write(f"eHealth Corp\n")
        f.write(f"Appointment results\n\n")
        f.write(f"Appointment ID: {appt.id}\n")
        f.write(f"Doctor: {appt.doctor}\n")
        f.write(f"Patient: {appt.patient}\n")
        f.write(f"Date: {appt.date}\n")
        f.write(f"Time: {appt.time}\n")
        f.write(f"Result: Treated\n")
    pdf = FPDF()
    pdf.add_page()
    pdf.set_font("times", size=15)
    f = open("certification.txt", "r")

    for x in f:
        pdf.cell(200, 10, txt=x, ln=1, align='C')

    pdf.output("certification.pdf")
    return True


@auth.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form.get('uname')
        password = request.form.get('psw')

        username = replace_all(username)

        user = User.query.filter_by(username=username).first()

        if user:
            if check_password_hash(user.password, password):
                flash('Logged in successfully!', category='success')
                login_user(user, remember=True)
                return redirect(url_for('auth.user_area', uname=user.username))
            else:
                flash('Incorrect username or password.', category='error')
        else:
            flash('Incorrect username or password.', category='error')
    return render_template("login.html", user=current_user)


@auth.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('views.index'))


@auth.route('/signup', methods=['GET', 'POST'])
def signup():
    if request.method == 'POST':
        username = request.form.get('uname')
        email = request.form.get('email')
        fullname = request.form.get('fullname')
        password1 = request.form.get('psw')
        password2 = request.form.get('confirmpsw')
        username = replace_all(username)

        user = User.query.filter_by(username=username).first()
        if user:
            flash('Username already exists.', category='error')
        elif len(email) < 5:
            flash("Email must be greater than 4 characters.", category='error')
        elif password1 != password2:
            flash("Passwords don't match.", category='error')
        elif len(password1) < 7:
            flash("Password must be at least 7 characters.", category='error')
        elif re.search('[A-Z]', password1) is None:
            flash('Password must contain at least one uppercase letter.', category='error')
        elif re.search('\d', password1) is None:
            flash('Password must contain at least a number.', category='error')
        else:
            new_user = User(email=email, fullname=fullname, password=generate_password_hash(
                password1, method='sha256'), username=username)
            db.session.add(new_user)
            db.session.commit()
            login_user(new_user, remember=True)
            flash("Account created!", category='success')
            return redirect(url_for('views.index'))
    return render_template("signup.html", user=current_user)


@auth.route('/user_area/<string:uname>')
@login_required
def user_area(uname):
    if uname != current_user.username:
        flash("Restricted access.", category="error")
        return redirect(url_for('auth.login'))
    appointments = Appointment.query.filter_by(patient=current_user.username).all()
    contact_requests = ContactForm.query.all() if current_user.username == 'admin' else []
    return render_template("user_area.html", user=current_user, doctors=Doctor.query.all(), contact_requests=contact_requests, appointments=appointments)


@auth.route('/user_area/<string:uname>/download_results/', methods=['GET', 'POST'])
@login_required
def download_results(uname):
    if request.method == "GET":
        if uname != current_user.username:
            flash("Restricted access.", category="error")
            return redirect(url_for('auth.login'))
        contact_requests = ContactForm.query.all() if current_user.username == 'admin' else []
        appointments = Appointment.query.filter_by(patient=current_user.username).all()
        appointment_id = request.args['id']
        if appointment_id:
            appointments = Appointment.query.filter_by(patient=current_user.username).all()
            if platform.system() == 'Windows':
                path = '..\\..\\certification.pdf'
            else:
                path = '../../certification.pdf'
            appt = Appointment.query.filter_by(id=appointment_id).first()

            if not appt:
                flash("Appointment not found", category="error")
            elif not create_certification(appt):
                flash("Appointment not found", category="error")
            else:
                if appt.patient == current_user.username:
                    return send_file(path, as_attachment=True)
                else:
                    flash("Appointment not found", category="error")

            return render_template("user_area.html", user=current_user, doctors=Doctor.query.all(), contact_requests=contact_requests, appointments=appointments)
        else:
            flash("Appointment not found", category="error")
            return render_template("user_area.html", user=current_user, doctors=Doctor.query.all(), contact_requests=contact_requests, appointments=appointments)


@auth.route('/user_area/<string:uname>/new_appointment/', methods=['POST', 'GET'])
@login_required
def schedule_appointment(uname):
    if uname != current_user.username:
        flash("Restricted access.", category="error")
        return redirect(url_for('auth.login'))
    doctor_name = request.form['doctor']
    date = request.form['date']
    time = request.form['time']
    contact_requests = ContactForm.query.all() if current_user.username == 'admin' else []
    new_appointment = Appointment(doctor=doctor_name, patient=current_user.username, date=date, time=time)
    db.session.add(new_appointment)
    db.session.commit()
    appointments = Appointment.query.filter_by(patient=current_user.username).all()
    flash("Appointment scheduled!", category="success")
    return render_template("user_area.html", user=current_user, doctors=Doctor.query.all(), appointments=appointments, contact_requests=contact_requests)


@auth.route('/forum/<string:uname>', methods=['POST', 'GET'])
@login_required
def forum(uname):
    if uname != current_user.username:
        flash("Restricted access.", category="error")
        return redirect(url_for('auth.login'))
    return render_template("forum.html", user=current_user, posts=Post.query.all())


@auth.route('/forum/<string:uname>/newpost/', methods=['POST', 'GET'])
@login_required
def newpost(uname):
    if uname != current_user.username:
        flash("Restricted access.", category="error")
        return redirect(url_for('auth.login'))
    if request.method == 'POST':
        title = request.form['title']
        content = request.form['content']
        new_post = Post(title=title, content=content, user_username=current_user.username)
        db.session.add(new_post)
        db.session.commit()
        return render_template("forum.html", user=current_user, posts=Post.query.all())
    return render_template("newpost.html", user=current_user)
