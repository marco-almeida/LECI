import sys
import time
import json
import psutil


def main():
    fout = open("ex05.json", "w")
    data = []

    for i in range(0, 3):
        cpu = psutil.cpu_percent(interval=1)
        tm = time.time()
        net = psutil.net_io_counters()
        t_data = {'stats': [{'time': tm, 'cpu': cpu, 'network': net}]}
        data.append(t_data)
    json.dump(data, fout, ensure_ascii=False, indent=2)
    fout.close()


main()
