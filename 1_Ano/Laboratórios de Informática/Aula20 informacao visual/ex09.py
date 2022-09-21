from PIL import Image
from PIL import ExifTags

fname = "imagens/flor.jpg"
im = Image.open(fname)
width, height = im.size
new_im = Image.new(im.mode, im.size)
for x in range(width):
    for y in range(height):
        p = im.getpixel( (x,y) )
        r = p[1]
        g = p[0]
        b = p[2]
        new_im.putpixel((x,y), (r, g, b) )
new_im.save(fname+"dif-color.jpg")