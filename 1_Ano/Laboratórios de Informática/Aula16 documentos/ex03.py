import time
import csv
import psutil


def main():
    fout = open("ex03.csv", "w", newline='')
    writer = csv.DictWriter(fout, fieldnames=["tempo", "bytes", "cpu"])
    writer.writeheader()

    for i in range(0, 60):
        writer.writerow(
            {"tempo": time.time(), "bytes": psutil.net_io_counters()[0], "cpu": psutil.cpu_percent(interval=1)})
    fout.close()


main()
