#!/usr/bin/bash
x=abc
xx=0123456789
echo ${x}
echo $x
echo ${xx}
echo $xx
echo ${x}x
echo x${x}
touch a1 a2 a3 a4 a22 # create some files
z=a*
ls $z