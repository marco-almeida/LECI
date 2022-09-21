import wave
import pyaudio

def main():
    wf = wave.open("440hz.wav", "rb")
    print(f"Channels: {wf.getnchannels()}")
    print(f"Frequency: {wf.getframerate()}")
    print(f"Sample size: {wf.getsampwidth()}")
    print(f"Number of audio frames: {wf.getnframes()}")
    player = pyaudio.PyAudio()
    stream = player.open(format = player.get_format_from_width(wf.getsampwidth()),
    channels = wf.getnchannels(), rate = wf.getframerate(), output = True)

    while True:
        data = wf.readframes(1024)
        if not data:
            break
        stream.write(data)

    stream.close()
    player.terminate()
    wf.close()

main()
