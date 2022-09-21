from PIL import Image
from PIL import ExifTags
import sys


def main(fname):
    im = Image.open(fname)
    for i in [1, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100]:
        im.save(fname+"-test-%i.jpg" % i, quality=i)



main("imagens/vasos.jpg")
