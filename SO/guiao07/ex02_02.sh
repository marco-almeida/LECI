#!/usr/bin/bash
rm -f zzz   # to guarantee file "zzz" does not exist
cat zzz     # an error message is sent to the terminal display window
cat zzz 2>z # the error message is sent to file "z",
# any previous content of "z" being deleted
cat z        # to check the error message is there
cat zzz 2>>z # the error message is appended to the end of file "z"
cat z        # Why are there 2 error messages in file "z"?
cat zzz >z   # the error message is sent to the terminal display window. Why?

