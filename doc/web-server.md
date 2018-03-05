# Web server

This template uses ring-jetty-adapter as its HTTP server.
By default the server will start listening to requests on port 3000. This can be changed by setting the `PORT` env variable.

The mount service for the server can be found in [src/psql_server/server.clj](server.clj).
This file also contains the middleware used in the application (I'm still debating if this should belong with the routes).

The routes can be found in [src/psql_server/routes.clj](routes.clj).
