DOCKERFILE=docker-compose.yml
ifeq ($(ENV), test)
	DOCKERFILE=docker-compose.test.yml
else ifeq($(ENV), ci_test)
	DOCKERFILE=docker-compose.ci.test.yml
endif

all:
	docker-compose -f ${DOCKERFILE} build

start:
	docker-compose -f ${DOCKERFILE} up

stop:
	docker-compose -f ${DOCKERFILE} down

repl:
	docker-compose -f ${DOCKERFILE} run --service-ports web lein repl

migration:
	docker-compose -f ${DOCKERFILE} run web lein migratus migrate

rollback:
	docker-compose -f ${DOCKERFILE} run web lein migratus rollback

.PHONY: test
test:
	docker-compose -f docker-compose.test.yml run web lein test

test_runner:
	docker-compose -f docker-compose.test.yml run web lein auto eftest

