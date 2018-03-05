# Database Migrations

Before connecting to a newly provisioned database, you must run the database migrations to ensure the schema is up to date with your application.
This is done using the `make migration` command.
When invoked a container will run and perform the migrations defined in the `resources/migrations` the against the current postgres container.
After this, the application should be ready to run.
Before running tests, call `make migration ENV=test` to seed the test database.

## Repl usage

The dev/user namespace provides the [migratus](https://github.com/yogthos/migratus) methods configured to use the current database connection.
After launching the repl with `make repl`, run `(mount/start)` to start the application.
Once the services start you can run `(user/migrate)` and `(user/rollback)` to update the database schema.

