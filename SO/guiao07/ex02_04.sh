#!/usr/bin/bash
rm -f zzz # to guarantee zzz does not exist
# in the following command, the standard error was not redirect
cat zzz >err
# in the following command, the standard error was redirect
# to the same file the standard output was
cat zzz >err 2>&1
cat err # to check the error message is there
# in the following command, the standard output was not redirect
cat /etc/passwd 2>z
# in the following command, the standard output was redirect
# to the same file the standard error was
cat /etc/passwd 2>z >&2
cat z # to check the it
