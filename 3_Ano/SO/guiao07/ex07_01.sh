#!/usr/bin/bash
ls
echo $?
rm -f zzz # to guarantee file zzz does not exist
echo $?
test -f zzz
echo $?
touch zzz # to guarantee file zzz exists
test -f zzz
echo $?
