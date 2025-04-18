package com.zipzipchat.ChatWebSocketServer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ChatWebSocketServer extends WebSocketServer{

    @Override
    public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onClose'");
    }

    @Override
    public void onError(WebSocket arg0, Exception arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }

    @Override
    public void onMessage(WebSocket arg0, String arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
    }

    @Override
    public void onOpen(WebSocket arg0, ClientHandshake arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onOpen'");
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onStart'");
    }
    
}
