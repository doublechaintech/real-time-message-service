
#ssh -p 20000 quarkus@demo2.doublechaintech.com
#sync -avz    --exclude node_modules  *   quarkus@192.168.1.9:~/githome/real-time-message-service/
#rsync -avz    --exclude node_modules  *   quarkus@192.168.1.9:~/githome/real-time-message-service/


#ssh -p 20000 quarkus@demo2.doublechaintech.com "cd ~/githome/real-time-message-service/ && rm -rf *"

rsync -avz   -e "ssh -p 20000"    *  quarkus@app.doublechaintech.com:~/githome/real-time-message-service/

