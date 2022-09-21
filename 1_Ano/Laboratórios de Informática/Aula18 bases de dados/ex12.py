import sqlite3 as sql


def main():
    db = sql.connect("aa.db") # estabelecer ligação à BD
    nome = input("Nome a pesquisar: ")
    c= 0
    result = db.execute("SELECT * FROM contacts WHERE firstname LIKE ? OR middlename LIKE ? OR lastname LIKE ?",(nome,nome,nome,))
    while True:
        row = result.fetchone()
        if not row:
            break
        print(f"{row[1]} {row[2]} {row[3]}")
        c += 1
    print(f"{c} contactos")


    db.close() # terminar ligação
main()
