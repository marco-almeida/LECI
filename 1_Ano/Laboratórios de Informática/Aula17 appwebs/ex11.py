import os
import cherrypy

PATH = os.path.abspath(os.path.dirname(__file__))

class Root(object):
    @cherrypy.expose
    
    def index(self):
        return "Eu sou o índice do Root (Root.index)"

def main():
    conf = {
            "/static": {
                        "tools.staticdir.on": True,
                        "tools.staticdir.dir": os.path.join(PATH, "static")
            },
    }

    cherrypy.quickstart(Root(), config=conf)

if __name__ == "__main__":
    main()