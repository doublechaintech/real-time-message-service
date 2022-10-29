all:
	quarkus build --native  --no-tests
	cp build/start-app-1.0.0-SNAPSHOT-runner  mix-gas-calculator
	./mix-gas-calculator -Xmx64m

dev:
	 ./gradlew --console=plain quarkusDev


docker:
	quarkus build --native  --no-tests
	docker build --no-cache -f src/main/docker/Dockerfile.native -t doublechaintech/real-time-message-service .

run-docker:
	docker run -d --memory 50m --name real-time-message-service --rm -p 9988:9988 doublechaintech/real-time-message-service

