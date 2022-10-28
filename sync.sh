
#ssh -p 20000 quarkus@demo2.doublechaintech.com
#sync -avz    --exclude node_modules  *   quarkus@192.168.1.9:~/githome/real-time-message-service/
#rsync -avz    --exclude node_modules  *   quarkus@192.168.1.9:~/githome/real-time-message-service/
rsync -avz   -e "ssh -p 20000"   --exclude node_modules  *  quarkus@demo2.doublechaintech.com:~/githome/real-time-message-service/

