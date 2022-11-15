#!/usr/bin/bash
# the following code declares function x
x() {
    ls -l
}
# the following code uses the function x previously defined
x
x | wc -
