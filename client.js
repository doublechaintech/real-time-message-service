const WebSocketClient = require('websocket').client;

var client = new WebSocketClient();

client.on('connectFailed', function(error) {
    console.log('Connect Error: ' + error.toString());
});

client.on('connect', function(connection) {
    console.log('Connection established!');
    
    connection.on('error', function(error) {
        console.log("Connection error: " + error.toString());
    });
    
    connection.on('close', function() {
        console.log('Connection closed!');
    });
    
    connection.on('message', function(message) {
        console.log("Current message on server is: '" +JSON.stringify( message) + "'");
    });
});

//client.connect('ws://192.168.1.9:9988/chat/x001', 'server time');
//client.connect('ws://192.168.1.9:9988/message-center/x001');
//client.connect('wss://demo2.doublechaintech.com/message-center/x001');
//wss://demo2.doublechaintech.com/message-center/x001

client.connect('ws://localhost:10000/message-center/x0000001');

