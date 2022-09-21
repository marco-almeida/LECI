from struct import pack
from math import sin, pi
import wave
import sys


def main():
    rate=44100
    wv = wave.open("teste.wav", "w")
    wv.setparams((1, 2, rate, 0, "NONE", "not compressed"))
    amplitude = 750
    data = []
    freq = 2000
    duration = 1 # Em segundos
    freq_a = 440
    freq_b = 880
    for i in range(0, rate):
        data.append(
        amplitude*sin(2*pi*freq_a*i/rate) +
        amplitude*sin(2*pi*freq_b*i/rate)
    )
    wvData = []
    for v in data:
        wvData += pack("h", int(v))
    wv.writeframes(bytearray(wvData))
    wv.close()
main()
