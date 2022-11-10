# Contents of this folder

Each `E??` directory contains the files for a given exercise.

The programs in some exercises require the `pt.ua.p2utils` package.
To compile and run those programs you must download `p2utils.jar`
and put it in some directory, for example, in `$HOME/lib/`.
Then, run this command:

    export CLASSPATH=:$HOME/lib/p2utils.jar

After that, you can compile and run with the usual commands:

    javac Prog.java
    java -ea Prog

NOTES:

1. You may change `$HOME/lib/p2utils.jar` to whatever path suits you.
2. `$HOME` is an environment variable with your home dir.
3. These instructions should work in Linux.
4. For Windows, google "set classpath in Windows".

