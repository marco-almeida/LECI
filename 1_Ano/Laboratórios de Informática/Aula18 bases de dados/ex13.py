#find da empresa

import sqlite3 as sql


def main():
    db = sql.connect("aa.db") # estabelecer ligação à BD
    nome = input("Nome a pesquisar: ")
    c= 0#SELECT name FROM companies, WHERE contacts.company_id = companies.id AND companies.address = "Aveiro";
    result = db.execute("SELECT company_id FROM contacts WHERE firstname LIKE ? OR middlename LIKE ? OR lastname LIKE ? ",(nome,nome,nome,))
    row = result.fetchall();
    company = db.execute("SELECT name FROM companies WHERE id = ?",(row[0]))
    
    while True:

        row = company.fetchone()
        if not row:
            break
        print(f"{row[0]}")


    db.close() # terminar ligação
main()
