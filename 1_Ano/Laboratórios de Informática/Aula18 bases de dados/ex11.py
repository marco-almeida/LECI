import sqlite3 as sql


def main():
    db = sql.connect("aa.db") # estabelecer ligação à BD
    result = db.execute("SELECT firstname FROM contacts")
    rows = result.fetchall()
    i = 0
    for row in rows:
        print(row[0])
        i += 1
    print(f"{i} contactos")

    db.close() # terminar ligação
main()
