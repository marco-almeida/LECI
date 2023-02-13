# somm22
SO Memory Management Simulation System

******
******

## Prerequisites

On Ubuntu you need the following packages installed: 
_build-essential_, _cmake_, _doxygen_, and _git_.

```
sudo apt install build-essential cmake doxygen git
```
In other Linux distributions you need equivalent packages installed.

******

## Cloning the repo

In a directory of your choice, clone the project to your computer

```
cd «directory-of-your-choice»
git clone https://git@github.com:detiuaveiro/«your-project»
```

******

## Preparing the compilation environment

In a terminal, enter the base directory of your project, create the **build** directory,
and use _cmake_ to prepare _make_

```
cd «directory-of-your-choice»
cd «your-project»
mkdir build
cd build
cmake ../src
```

If you prefer _ninja_, instead of _make_,

```
cd «directory-of-your-choice»
cd «your-project»
mkdir build
cd build
cmake -G Ninja ../src
```

******

## Compiling the code

In a terminal, enter the **build** directory of your project and run _make_ or _ninja_

```
cd «directory-of-your-choice»
cd «your-project»«your-project»«your-project»/build
make
```
or

```
cd «directory-of-your-choice»
cd «your-project»/build
ninja
```
******

## Generating documentation

The code is documented in **doxygen**. So, you can easily generate **html** documentation pages.

```
cd «directory-of-your-choice»
cd «your-project»/doc
doxygen
```
Then, you can display the pages running (inside the **doc** directory)

```
firefox html/index.html &
```

Of course, you can replace _firefox_ with your favourite browser.

******

## Setting your user name and email in Git

Commands
```
cd «directory-of-your-choice»
cd «your-project»
git config user.name "«your name»"
git config user.email "«your email»"
```
allows you to set your user name and email for this repository.

If you want to apply the settings to all repositories in the computer, run the followings commands instead.
```
cd «directory-of-your-choice»
cd «your-project»
git config --global user.name "«your name»"
git config --global user.email "«your email»"
```

******

## Testing the code

After building the code, a program will be put in the <tt>«your-project»/bin</tt> directory.

Of course you can write your own testing programs. Do not forget to edit appropriately
the main <tt>CMakeList.txt</tt> file.


