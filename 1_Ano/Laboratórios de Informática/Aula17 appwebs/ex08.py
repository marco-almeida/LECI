import cherrypy


class Actions(object):
    @cherrypy.expose
    def doLogin(self, username=None, password=None):
        if (username == "ana" and password=="20"):
            return "Login efetuado com sucesso"
        else:
            return "Login Failed"


class Root(object):

    def __init__(self):
        self.actions = Actions()

    @cherrypy.expose
    def index(self):
        return "Eu sou o index Root"

    @cherrypy.expose
    def form(self):
        cherrypy.response.headers["Content-Type"] = "text/html"
        return open("formularioex05.html", "r").read()


if __name__ == "__main__":
    cherrypy.quickstart(Root())
