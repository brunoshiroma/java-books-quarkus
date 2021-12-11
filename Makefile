test:
	./gradlew test

clean:
	./gradlew clean

native:
	./gradlew build -Dquarkus.package.type=native