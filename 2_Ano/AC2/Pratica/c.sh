#!/bin/bash
pcompile aula$1/$2.c && ldpic32 aula$1/$2.hex -p /dev/ttyS$3 && pterm $4 -p /dev/ttyS$3
rm aula$1/*.hex
rm aula$1/*.o
#rm aula$1/*.sym
rm aula$1/*.map
rm aula$1/*.elf
