#!/usr/bin/bash
touch zzz # to guarantee file zzz exists
if test -f zzz; then
    echo "File zzz exists"
else
    echo "File zzz does not exist"
fi
check() {
    if test -f $1; then
        echo -e "\e[33mFile zzz exists\e[0m"
    else
        echo -e "\e[31mFile zzz does not exist\e[0m"
    fi
}
touch zzz # to guarantee file zzz exists
check zzz
rm -f zzz # to guarantee file zzz does not exist
check zzz
