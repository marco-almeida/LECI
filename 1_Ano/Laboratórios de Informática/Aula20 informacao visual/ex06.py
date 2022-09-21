from PIL import Image
from PIL import ExifTags
import sys


def main(fname):
    im = Image.open(fname)
    print(im.mode)


main("imagens/ribeira_porto.jpg")
