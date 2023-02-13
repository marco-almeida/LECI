case "$OSTYPE" in
  solaris*) os="SOLARIS" ;;
  darwin*)  os="OSX" ;; 
  linux*)   os="LINUX" ;;
  bsd*)     os="BSD" ;;
  msys*)    os="WINDOWS" ;;
  *)        os="unknown: $OSTYPE" ;;
esac

path=$(pwd)

if [ $os = "WINDOWS" ]
then
cmd /k cd ${path} & .\\venv\\Scripts\\activate & pip install -r requirements.txt & python3 server.py
sleep 2
cmd /k cd ${path} & .\\venv\\Scripts\\activate & python3 viewer.py
sleep 2
cmd /k cd ${path} & .\\venv\\Scripts\\activate & python3 student.py
fi

if [ $os = "LINUX" ]
then
gnome-terminal -x bash -c "cd ${path} && source venv/bin/activate && pip install -r requirements.txt && python3 server.py"
sleep 2
gnome-terminal -x bash -c "cd ${path} && source venv/bin/activate && python3 viewer.py"
sleep 2
gnome-terminal -x bash -c "cd ${path} && source venv/bin/activate && python3 student.py"
fi