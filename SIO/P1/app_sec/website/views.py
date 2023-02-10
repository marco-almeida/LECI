from flask import Blueprint, render_template, request, flash
from flask_login import current_user
from .models import ContactForm, Doctor, db
views = Blueprint('views', __name__)


@views.route('/', methods=['GET', 'POST'])
def index():
    return render_template("index.html", user=current_user)


@views.route('/search', methods=['GET', 'POST'])
def search():
    if request.method == "GET":
        query = request.args['query']
        from .auth import replace_all
        query = replace_all(query)
        if query:
            doctors = Doctor.query.filter(Doctor.name.contains(query)).all()
            return render_template("index.html", user=current_user, doctors=doctors)
        else:
            return render_template("index.html", user=current_user)


@views.route('/contact-company', methods=['GET', 'POST'])
def contact_company():
    if request.method == "POST":
        fname = request.form['first_name']
        lname = request.form['last_name']
        email = request.form['email_addr']
        phone = request.form['phone_input']
        message = request.form['message']
        new_note = ContactForm(fname=fname, lname=lname, email=email, phone=phone, message=message)
        db.session.add(new_note)
        db.session.commit()
        flash('Message sent!', category='success')
    return render_template('index.html', user=current_user)
    
