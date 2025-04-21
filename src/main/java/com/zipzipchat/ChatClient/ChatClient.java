package com.zipzipchat.ChatClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    this.webClient.connectBlocking();

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Servidor iniciado. Comandos: [stop] [status] [exit]");
      while (true) {
        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
          case "stop":
            this.webClient.close();
            System.out.println("Servidor parado");
            System.exit(0);
            break;
 
          case "mensagem":
            System.out.println("Mandando mensagem: 'Coe'");
            String message = "Coe";
            // TODO PArse to Json
            this.sendMessage(message);
            break;

          case "exit":
            this.webClient.close();
            System.exit(0);
            break;

          default:
            System.out.println("Comando inválido");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // webClient.send("Its working!!!!");
  }

  private void sendMessage(String message) {
    this.webClient.send(message);
  }

}
