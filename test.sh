curl -X PUT -d '{"channelName":"ch01","endpoint":"x001"}'  -H "Content-Type:application/json" http://192.168.1.9:9988/message-center/channel
curl -X PUT -d '{"channelName":"ch01","message":"yes是的，我看行"}'  -H "Content-Type:application/json" http://192.168.1.9:9988/message-center/post
