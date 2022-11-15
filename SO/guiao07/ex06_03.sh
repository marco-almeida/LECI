#!/usr/bin/bash
zzz=abc
echo $zzz
{
    echo "This takes place in the same bash instance"
    zzz=xpto
    echo $zzz
}
echo $zzz
echo ============================
zzz=abc
echo $zzz
(
    echo "This takes place in a new bash instance"
    zzz=xpto
    echo $zzz
)
echo $zzz # the assignment within !()! ’got lost’
