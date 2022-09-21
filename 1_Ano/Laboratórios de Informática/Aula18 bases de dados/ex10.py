import sqlite3 as sql


def main():
    db = sql.connect("aa.db") # estabelecer ligação à BD
    result = db.execute("SELECT * FROM contacts")
    rows = result.fetchall()
    
    for row in rows:
        print(row)

    db.close() # terminar ligação
main()
