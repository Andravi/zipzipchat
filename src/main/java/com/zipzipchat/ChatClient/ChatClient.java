package com.zipzipchat.ChatClient;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class ChatClient {

  WebSocketClient webClient;
  Boolean isConnected;
  String uri;

  public ChatClient() throws URISyntaxException {
    this.uri = "ws://localhost:8080";
    this.webClient = new WebSocketClient(new URI(uri)) {

      @Override
      public void onMessage(String message) {
        System.out.println("got: " + message + "\n");

      }

      @Override
      public void onOpen(ServerHandshake handshake) {
        System.out.println("You are connected to ChatServer: " + getURI() + "\n");
        isConnected = true; // Sinaliza que a conexão está pronta
      }

      @Override
      public void onClose(int code, String reason, boolean remote) {
        System.out.println("You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason
            + "\n");
      }

      @Override
      public void onError(Exception ex) {
        System.out.println("Exception occurred ...\n" + ex + "\n");
        ex.printStackTrace();
      }
    };
  }

  public void connect() throws InterruptedException {
    webClient.connect(); // Inicia a conexão
    Thread.sleep(2000);
    webClient.send("Its working!!!!");
  }

}
