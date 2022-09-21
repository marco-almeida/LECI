from PIL import Image
from PIL import ExifTags
import sys


def main(fname):
    im = Image.open(fname)

    width, height = im.size

    print("Largura: %dpx" % width)
    print("Altura: %dpx" % height)
    print("Formato: %s" % im.format)

    tags = im._getexif()

    for k, v in tags.items():
        print(str(ExifTags.TAGS[k])+" : "+str(v))


main("flor.jpg")
