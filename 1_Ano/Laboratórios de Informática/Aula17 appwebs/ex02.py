import cherrypy


class HelloWorld(object):
    @cherrypy.expose
    def index(self):
        host = cherrypy.request.headers["Host"]
        return "Hello World!" + " You have successfully reached " + host


cherrypy.quickstart(HelloWorld())


