all:
	docker-compose build

repl:
	docker-compose run --service-ports web lein repl

test:
	docker-compose run web lein test

test_runner:
	docker-compose run web lein auto test
