# PSQL Server

[![CircleCI](https://circleci.com/gh/adamcumiskey/psql-server/tree/master.svg?style=svg&circle-token=4b5805c3dcea7774fa83b3157df70905b042cbcf)](https://circleci.com/gh/adamcumiskey/psql-server/tree/master)

Template for a Clojure web server using a PostgreSQL database. Uses [mount](https://github.com/tolitius/mount) to manage service state.

## Installation

  1. Ensure that Docker and Leinengen are installed on your machine.
  1. Create an `.env` file based on [template.env](template.env) to configure your local environment.
  1. Run `docker-compose build` to build the image.
  1. Run `docker-compose run web lein migratus migrate` to migrate the database to the latest schema.
  1. Start the container by running `docker-compose up`.

## Development

You can start a repl instance inside the docker-container by running `docker-compose run --service-ports web lein repl`.
A client like [fireplace.vim](https://github.com/tpope/vim-fireplace.git) can then connect using the host and port defined by `LEIN_REPL_HOST` and `LEIN_REPL_PORT` in the `.env` file.
After starting the repl, run `(mount/start)` to start the http server and database connection along with their associated dependencies.
Reloading namespaces containing `mount` states will restart those states and their dependencies, allowing development to continue without restarting the repl.

