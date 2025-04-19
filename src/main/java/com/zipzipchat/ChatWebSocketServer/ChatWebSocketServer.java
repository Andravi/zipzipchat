package com.zipzipchat.ChatWebSocketServer;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ChatWebSocketServer extends WebSocketServer {

    private ConcurrentHashMap<WebSocket, String> clients = new ConcurrentHashMap<>();

    private String getHostNameBy(WebSocket webSocket) {
        return webSocket.getLocalSocketAddress().getAddress().getHostName();
    }

    
    private String getHostAdressBy(WebSocket webSocket) {
        return webSocket.getLocalSocketAddress().getAddress().getHostAddress();
    }

    public ChatWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket arg0, ClientHandshake arg1) {
        // TODO Auto-generated method stub
        clients.put(arg0, "");
        System.out.println("Nova conexão de " + getHostNameBy(arg0));
    }

    @Override
    public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
        clients.remove(arg0);
        System.out.println("Conexão fechada para " + getHostNameBy(arg0));
    }

    @Override
    public void onMessage(WebSocket arg0, String arg1) {
        System.out.println("Mensagem recebida de" + getHostAdressBy(arg0) + ":" + arg1);
    }

    @Override
    public void onError(WebSocket arg0, Exception arg1) {
        System.out.println("Erro na conexão do " + arg1.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Servidor iniciado na porta: " + getPort());
    }

}
