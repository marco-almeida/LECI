from datetime import datetime
from flask import Flask, render_template, url_for, request, redirect, request, flash
import sqlite3
from flask import send_file
from fpdf import FPDF
import platform
############################# AUXILIARY FUNCTIONS #############################


def init_db():
    #################### doctors table ####################
    connection = sqlite3.connect('data.db', check_same_thread=False)
    cursor = connection.cursor()

    cursor.execute("""DROP TABLE IF EXISTS doctors""")
    cursor.execute("""CREATE TABLE IF NOT EXISTS doctors (
        id INTEGER PRIMARY KEY,
        name TEXT NOT NULL,
        email TEXT NOT NULL,
        tel TEXT NOT NULL
    );""")

    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Lillian Curry','lc@ehealth.com','915794014')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Esther Holloway','eh@ehealth.com','918226120')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Caleb Bennett','cb@ehealth.com','914389307')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Mildred Dixon','md@ehealth.com','913641581')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Jacqueline Ellis','je@ehealth.com','910691821')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Ken Mccoy','km@ehealth.com','914618330')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Shannon Becker','sb@ehealth.com','917753073')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Amber Huff','ah@ehealth.com','915641953')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Kristi Neal','kn@ehealth.com','914980173')""")
    cursor.execute(
        """INSERT OR IGNORE INTO doctors VALUES (NULL, 'Penny Gonzales','pg@ehealth.com','914596191')""")

    #################### users table ####################

    cursor.execute("""DROP TABLE IF EXISTS users""")
    cursor.execute("""CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    fullname TEXT NOT NULL,
    email TEXT NOT NULL,
    UNIQUE(name)
    );""")
    cursor.execute("""INSERT OR IGNORE INTO users VALUES (NULL,'admin', 'admin', 'Admin', 'adminmail@asd.com')""")
    cursor.execute(
        """INSERT OR IGNORE INTO users VALUES (NULL,'zemanel', 'zemanel123', 'José Manuel Figueiras', 'zemanel123@hotmail.com')""")
    cursor.execute(
        """INSERT OR IGNORE INTO users VALUES (NULL,'antonio', 'qwertyops', 'António Costa Silva', 'toniocosta@gmail.com')""")
    
    connection.commit()

    #################### forum database ####################

    cursor.execute("""DROP TABLE IF EXISTS forum""")
    cursor.execute("""CREATE TABLE IF NOT EXISTS forum (
        id INTEGER PRIMARY KEY,
        title TEXT NOT NULL,
        content TEXT NOT NULL,
        author TEXT NOT NULL,
        date TEXT NOT NULL,
        time TEXT NOT NULL
    );""")

    cursor.execute(
        """INSERT OR IGNORE INTO forum VALUES (NULL, 'We need doctors','We are hiring doctors specializing in podiatry. Please contact us and submit your curriculum vitae!','admin','2020-12-12', '03:44')""")

    connection.commit()

    #################### appointments database ####################

    cursor.execute("""DROP TABLE IF EXISTS appointments""")
    cursor.execute("""CREATE TABLE IF NOT EXISTS appointments (
        id INTEGER PRIMARY KEY,
        DoctorName TEXT NOT NULL,
        patient TEXT NOT NULL,
        date TEXT NOT NULL,
        time TEXT NOT NULL
    );""")

    cursor.execute(
        """INSERT OR IGNORE INTO appointments VALUES (NULL, 'Shannon Becker','admin','2020-12-12','17:30')""")
    cursor.execute(
        """INSERT OR IGNORE INTO appointments VALUES (NULL, 'Amber Huff','admin','2021-01-06','09:00')""")
    cursor.execute(
        """INSERT OR IGNORE INTO appointments VALUES (NULL, 'Ken Mccoy','zemanel','2022-03-07','14:00')""")
    connection.commit()

    #################### contact form database ####################

    cursor.execute("""DROP TABLE IF EXISTS contacts""")
    cursor.execute("""CREATE TABLE IF NOT EXISTS contacts (
        id INTEGER PRIMARY KEY,
        firstname TEXT NOT NULL,
        lastname TEXT NOT NULL,
        email TEXT NOT NULL,
        phone TEXT NOT NULL,
        message TEXT NOT NULL
    );""")

    # no fim dar close
    connection.close()


def insert_user(username, password, password2, fullname, email):
    if password != password2:
        return False, "Passwords don't match"
    if '/' in username:
        return False, "Username can't contain character '/'"
    
    connection = sqlite3.connect('data.db', check_same_thread=False)
    cursor = connection.cursor()
    # check if there already exists a user with that name
    query = "SELECT EXISTS(SELECT 1 FROM users WHERE name ='" + \
        username + "');"
    try:
        cursor.execute(query)
        results = cursor.fetchall()
        if results[0][0] == 1:
            return False, "Username already exists"
    except:
        return False, "There was an error validating your input, please check your data and try again."
    # if we've reached this point, we can add the user to the database
    try:
        cursor.execute("INSERT INTO users VALUES (NULL, ?, ?, ?, ?)",
                       (username, password, fullname, email))
        connection.commit()
        connection.close()
        return True, 'Account created successfully. Click <a href="/login">here</a> to login'
    except:
        return False, "There was an error validating your input, please check your data and try again."


def authenticate_user(username, password):
    if '/' in username:
        return False, "Username can't contain character '/'"
    query = "SELECT name,password FROM users WHERE name= '" + \
        username + "' AND password='" + password + "'"
    connection = sqlite3.connect('data.db', check_same_thread=False)
    cursor = connection.cursor()

    try:
        cursor.execute(query)
        results = cursor.fetchall()
        if len(results) == 0:
            return False, "Incorrect username or password"
        connection.close()
        return True, "Login successful"
    except:
        return False, "There was an error validating your input, please check your data and try again."


def search_for_doctor(search):  # procurar doctors disponiveis na DB
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute(f"SELECT * FROM doctors WHERE name LIKE '%{search}%'")
        results = cursor.fetchall()
        if len(results) == 0:
            return results                 
        conn.close()
        return results
    except :
        return []  
        

def get_posts():
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute("SELECT * FROM forum")
        results = cursor.fetchall()
        if len(results) == 0:
            return results       
        conn.close()
        return results
    except:
        conn.close()
        return []


def get_user_info(username):
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute(f"SELECT * FROM users WHERE name = '{username}'")
        results = cursor.fetchall()
        if len(results) == 0:
            return []
        conn.close()
        return results
    except:
        return []


def get_patient_appointments(username):
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute(f"SELECT * FROM appointments WHERE patient = '{username}'")
        results = cursor.fetchall()
        if len(results) == 0:
            return results
        conn.close()
        return results
    except:
        return None


def appointment_exists(identifier):
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute(f"SELECT * FROM appointments WHERE id = '{identifier}'")
        results = cursor.fetchall()
        if len(results) == 0:
            return False
        conn.close()
        return True
    except:
        return False


def create_certification(identifier):
    if not appointment_exists(identifier):
        return False
    open("certification.txt", "w").close()
    appt_info = get_appointment_info_by_id(identifier)
    with open('certification.txt', 'a') as f:
        f.write(f"eHealth Corp\n")
        f.write(f"Appointment results\n\n")
        f.write(f"Appointment ID: {identifier}\n")
        f.write(f"Doctor: {appt_info[0][1]}\n")
        f.write(f"Patient: {appt_info[0][2]}\n")
        f.write(f"Date: {appt_info[0][3]}\n")
        f.write(f"Time: {appt_info[0][4]}\n")
        f.write(f"Result: Treated\n")
    pdf = FPDF()
    pdf.add_page()
    pdf.set_font("times", size=15)
    f = open("certification.txt", "r")

    for x in f:
        pdf.cell(200, 10, txt=x, ln=1, align='C')

    pdf.output("certification.pdf")
    return True


def get_appointment_info_by_id(identifier):
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute(f"SELECT * FROM appointments WHERE id = '{identifier}'")
        results = cursor.fetchall()
        if len(results) == 0:
            return results
        conn.close()
        return results
    except:
        return []


def get_contact_requests():
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute("SELECT * FROM contacts")
        results = cursor.fetchall()
        if len(results) == 0:
            return results
        conn.close()
        return results
    except:
        return []

############################# END OF AUXILIARY FUNCTIONS #############################
def page_not_found(e):
    return render_template('404.html'), 404

app = Flask(__name__)
app.secret_key = b'_5#y2L"F4Q8z\n\xec]/'
app.register_error_handler(404, page_not_found)
init_db()


@app.route('/', methods=['POST', 'GET'])
def index():
    return render_template('index.html')


@app.route('/search', methods=['POST', 'GET'])
def search():
    if request.method == 'GET':
        query = request.args['query']
        if query:
            return render_template('index.html', users=search_for_doctor(query), query=query)
    return render_template('index.html')


@app.route('/login', methods=['POST', 'GET'])
def login():
    if request.method == 'POST':
        uname = request.form['uname']
        psw = request.form['psw']
        result, msg = authenticate_user(uname, psw)

        if not result:
            flash(msg, category='error')
            return render_template('login.html')
        else:
            flash(msg, category='success')
            return redirect(url_for('user_area', uname=uname))
    else:
        return render_template('login.html')


@app.route('/signup', methods=['POST', 'GET'])
def signup():
    if request.method == 'POST':
        uname = request.form['uname']
        psw = request.form['psw']
        confirmpsw = request.form['confirmpsw']
        email = request.form['email']
        fullname = request.form['fullname']
        result, msg = insert_user(uname, psw, confirmpsw, fullname, email)
        if not result:
            flash(msg, category='error')
        else:
            flash(msg, category='success')
        return render_template('signup.html')
    else:
        return render_template('signup.html')


@app.route('/forum/<string:uname>', methods=['POST', 'GET'])
def forum(uname):
    posts = get_posts()
    if request.method == "POST":
        return render_template("forum.html", uname=uname, posts=posts)
    else:
        return render_template('forum.html', uname=uname, posts=posts)


@app.route('/forum/<string:uname>/newpost', methods=['POST', 'GET'])
def newpost(uname):
    if request.method == "POST":
        title = request.form['title']
        content = request.form['content']
        if title == "" or content == "":
            return redirect(url_for('newpost', uname=uname))
        author = uname
        date = datetime.now().strftime('%Y-%m-%d')
        time = datetime.now().strftime('%H:%M:%S')
        conn = sqlite3.connect("data.db")
        cursor = conn.cursor()
        try:
            cursor.execute("INSERT INTO forum VALUES (NULL, ?, ?, ?, ?, ?)",
                        (title, content, author, date, time))
            conn.commit()
        except:
            return ('',204)
        conn.close()
        return redirect(url_for('forum', uname=uname))
    else:
        return render_template('newpost.html', uname=uname)


@app.route('/user_area/<string:uname>', methods=['POST', 'GET'])
def user_area(uname):
    contact_requests = get_contact_requests()
    info = get_user_info(uname)
    if info != []:
        info = info[0]

    appointments = get_patient_appointments(uname)
    doctors = search_for_doctor('')
    print(uname, info, appointments, doctors)
    return render_template('user_area.html', uname=uname, info=info, appointments=appointments, doctors=doctors, contact_requests=contact_requests)


@app.route('/user_area/<string:uname>/new_appointment', methods=['POST', 'GET'])
def schedule_appointment(uname):
    doctor_name = request.form['doctor']
    date = request.form['date']
    time = request.form['time']
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute("INSERT or IGNORE INTO appointments VALUES (NULL, ?, ?, ?, ?)",
                    (doctor_name, uname, date, time))
        conn.commit()
    except:
        return ('',204)
    conn.close()
    return redirect(url_for('user_area', uname=uname))


@app.route('/user_area/<string:uname>/download_results', methods=['POST', 'GET'])
def download_results(uname):
    appointment_id = request.args['id']
    if platform.system() == 'Windows':
        path = '..\\certification.pdf'
    else:
        path = '../certification.pdf'

    if not appointment_exists(appointment_id):
        flash("Appointment not found", category="error")
    elif not create_certification(appointment_id):
        flash("Appointment not found", category="error")
    else:
        return send_file(path, as_attachment=True)
    return redirect(url_for('user_area', uname=uname))


@app.route('/index/get_contact', methods=['POST', 'GET'])
def get_contact():
    fname = request.form['first_name']
    lname = request.form['last_name']
    email = request.form['email_addr']
    phone = request.form['phone_input']
    message = request.form['message']
    conn = sqlite3.connect("data.db")
    cursor = conn.cursor()
    try:
        cursor.execute("INSERT or IGNORE INTO contacts VALUES (NULL, ?, ?, ?, ?,?)",
                    (fname, lname, email, phone, message))
        conn.commit()
    except:
        return ('',204)
    conn.close()
    return redirect(url_for('index'))


if __name__ == "__main__":
    app.run(port=8000, debug=False)
