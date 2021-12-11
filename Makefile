test:
	./gradlew test

clean:
	./gradlew clean

native:
	./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true