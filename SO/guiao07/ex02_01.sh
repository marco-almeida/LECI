#!/usr/bin/bash
echo ola    # message "ola" is sent to the terminal display window
echo ola >z # message "ola" is sent to file "z",
# any previous content of "z" being deleted
echo ola >>z # message "ola" is appended to the end of file "z";
# file "z" is created if it does not exist
