# Project 1 - eHealth Corp - Vulnerabilities in Software Projects

## Introduction

Project carried out in the scope of the subject Security of Information and Organizations with the purpose of exploiting vulnerabilities in software projects and their correction. Flask, Bootstrap 4.0 and SQLAlchemy/SQLite3 were used for this project completion.

## Description

The objective is to develop a simple web page for a health clinic.

It is necessary to develop two versions of the same application, an insecure version with vulnerabilities that are not obvious to the average user but can be exploited to compromise the correct operation of the application and a secure version with the vulnerabilities corrected.

We decided to develop an intuitive web page with a sign up/login page, a forum for clinic members to post blogs and a page where you can request appointments with a doctor you can search for.

## Vulnerabilities

Mandatory:

- [CWE-79: Improper Neutralization of Input During Web Page Generation ('Cross-site Scripting')](https://cwe.mitre.org/data/definitions/79.html)

- [CWE-89: Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')](https://cwe.mitre.org/data/definitions/89.html)

Chosen:

- [CWE-20: Improper Input Validation](https://cwe.mitre.org/data/definitions/20.html)

- [CWE-200: Exposure of Sensitive Information to an Unauthorized Actor](https://cwe.mitre.org/data/definitions/200.html)

- [CWE-256: Plaintext Storage of a Password](https://cwe.mitre.org/data/definitions/256.html)

- [CWE-284: Improper Access Control](https://cwe.mitre.org/data/definitions/284.html)

- [CWE-425: Direct Request ('Forced Browsing')](https://cwe.mitre.org/data/definitions/425.html)

- [CWE-521: Weak Password Requirements](https://cwe.mitre.org/data/definitions/521.html)

## How to run

> :warning: ***NOT TESTED FOR MacOS***

1. Create a virtual environment:

    ```bash
    python3 -m venv venv
    ```

2. Activate the virtual environment (this step needs to be repeated every time a new terminal is opened):

    ```bash
    source venv/bin/activate
    ```

3. Install the requeriments:

    ```bash
    pip install -r requirements.txt
    ```

4. Run the server (**use these exact paths**):

    ```bash
    python3 app/app.py
    ```

    or

    ```bash
    python3 app_sec/main.py
    ```

5. Access the website:

    <http://127.0.0.1:8000/> for the insecure version

    <http://127.0.0.1:5000/> for the secure version

## Authors

- [Bruno Gomes](https://github.com/BrunoGomes22)

- [João Balseiro](https://github.com/Joao-Balseiro)

- [João Gonçalves](https://github.com/Joaomg001)

- [Marco Almeida](https://github.com/marco-almeida)
