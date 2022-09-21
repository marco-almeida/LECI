import csv
import random


def main():
    fout = open("rand.csv", "w")
    writer = csv.DictWriter(fout,  delimiter='|',
                            fieldnames=["time", "value"])
    writer.writeheader()

    for i in range(1, 10):
        writer.writerow({"time": i, "value": random.randint(1, 100)})
    fout.close()


main()
