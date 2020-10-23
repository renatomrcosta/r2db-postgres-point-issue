.PHONY: test
test: run-db
	./gradlew check

.PHONY: run-db
run-db:
	docker-compose up -d
