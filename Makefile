all: docker run-docker
	echo "fine!"

dev:
	 ./gradlew --console=plain quarkusDev

docker:
	quarkus build --native  --no-tests
	docker build --no-cache -f src/main/docker/Dockerfile.native -t golap2docker/realtime-message-service  .
	#docker push golap2docker/realtime-message-service 


run-docker:

	docker rm -f real-time-message-service
	docker run -d --memory 50m --name real-time-message-service --net host doublechaintech/real-time-message-service
	docker logs real-time-message-service
