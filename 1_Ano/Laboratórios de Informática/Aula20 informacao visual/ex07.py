from PIL import Image
from PIL import ExifTags
import sys
import os

directory = "imagens"
a = os.listdir(directory)
for i in a:
    im = Image.open(directory + "/" + i)
    print(i + ": " + im.mode)
