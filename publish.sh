quarkus build --native  --no-tests
rsync -avz build/real-time-message-service-1.0.0-SNAPSHOT-runner philip@iotlog.doublechaintech.com:~/githome/iot-message-console/iot-log-server-qk
