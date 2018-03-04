all:
	docker-compose build

repl:
	docker-compose run --service-ports web lein repl

test:
	docker-compose -f docker-compose.test.yml run web lein eftest

test_runner:
	docker-compose run web lein auto eftest

migrate:
	docker-compose run web lein migratus migrate

rollback:
	docker-compose run web lein migratus rollback
