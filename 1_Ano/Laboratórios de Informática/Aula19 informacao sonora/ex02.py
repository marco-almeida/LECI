import wave

def main():
    wf = wave.open("440hz.wav", "rb")
    print(f"Channels: {wf.getnchannels()}")
    print(f"Frequency: {wf.getframerate()}")
    print(f"Sample size: {wf.getsampwidth()}")
    print(f"Number of audio frames: {wf.getnframes()}")
    wf.close()
main()
