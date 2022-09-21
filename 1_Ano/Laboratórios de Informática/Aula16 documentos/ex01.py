import csv
import sys

def main(argv):
    info = []
    fich_csv = open(argv[1], "r")
    csv_Reader = csv.DictReader(fich_csv, delimiter=",")

    for row in csv_Reader:
        info.append(row["value"])
    fich_csv.close()
    newList = list(map(float, info))
    
    print(f"O mínimo das temperaturas é {min(info)}!")
    print(f"O máximo das temperaturas é {max(info)}!")
    print(f"A média das temperaturas é {sum(newList) / len(newList)}!")


main(sys.argv)
