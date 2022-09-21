#!python
# Example of a cherrypy application that serves static content,
# as well as dynamic content.
#
# JMR@ua.pt 2016
#
# To run:
#	python exampleApp.py

import os.path
import cherrypy

# The absolute path to this file's base directory:
baseDir = os.path.dirname(os.path.abspath(__file__))

# Dict with the this app's configuration:
config = {
  "/":     { "tools.staticdir.root": baseDir },
  "/js":   { "tools.staticdir.on": True,
             "tools.staticdir.dir": "js" },
  "/css":  { "tools.staticdir.on": True,
             "tools.staticdir.dir": "css" },
  "/html": { "tools.staticdir.on": True,
             "tools.staticdir.dir": "html" },
}

class Root:
    # This class atribute contains the HTML text of the main page:
    indexHTML = """<html>
       <head>
       <title>CherryPy static example</title>
       <link rel="stylesheet" type="text/css"
       href="css/style.css" type="text/css"></link>
       <script
       type="application/javascript"
       src="js/some.js"></script>
       </head>
       <body>
       <h1>This is the main (index) page, served dynamically.</h1>
       You should have seen an Alert before this page.
       <p>This is a paragraph, that should be green.</p>
       This is a <a href="html/page.html">link to a static page</a>
       </body>
       </html>
       """

    @cherrypy.expose
    def index(self):
       return Root.indexHTML

    @cherrypy.expose
    def dynamic2(self):
       return "This is dynamic2"

cherrypy.quickstart(Root(), "/", config)

