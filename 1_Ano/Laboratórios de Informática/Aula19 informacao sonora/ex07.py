from struct import pack
from math import sin, pi
import wave
import sys


def main():
    rate = 44100
    wv = wave.open("teste", "w")
    wv.setparams((1, 2, rate, 0, "NONE", "not compressed"))
    amplitude = 10000
    data = []
    tones = {'1': (697, 1029),
             '2': (697, 1336),
             '3': (697, 1477),
             '4': (770, 1029),
             '5': (770, 1336),
             '6': (770, 1477),
             '7': (852, 1029),
             '8': (852, 1336),
             '9': (852, 1477),
             '0': (941, 1336),
             '*': (941, 1029),
             '#': (941, 1477),
             'A': (697, 1633),
             'B': (770, 1633),
             'C': (852, 1633),
             'D': (941, 1633), }

    number = {1, 2, 3}  # número a codificar

    for n in number:
        # Códigos DTMF
        for i in range(0, int(rate*0.040)):
            data.append(tones[n][0]+tones[n][1])
        # Pausa (silêncio)
        for i in range(0, int(rate*0.040)):
            data.append(0)

    wvData = []
    for v in data:
        wvData += pack("h", int(v))

    wv.writeframes(bytearray(wvData))
    wv.close()


if __name__ == "__main__":
    main()
