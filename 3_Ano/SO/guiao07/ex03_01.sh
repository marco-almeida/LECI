#!/usr/bin/bash
mkdir dir1
cd dir1 || exit
touch a a1 a2 a3 a11 b b1 b11 b12 b21 # to create some files
ls
ls a*
ls *1
ls a?
ls b??
ls b?*
ls *