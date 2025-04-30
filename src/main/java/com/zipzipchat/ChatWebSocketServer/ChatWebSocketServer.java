package com.zipzipchat.ChatWebSocketServer;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class ChatWebSocketServer extends WebSocketServer {
    // Colocar um log
    private ConcurrentHashMap<WebSocket, String> clients = new ConcurrentHashMap<>(); // Pesquisar o que é esse hashMap
    private int numberOfClientsOn = 0;

    private String getHostNameBy(WebSocket webSocket) {
        return webSocket.getLocalSocketAddress().getAddress().getHostName();
    }

    private String getHostAdressBy(WebSocket webSocket) {
        return webSocket.getLocalSocketAddress().getAddress().getHostAddress();
    }

    public int getNumberOfClientsOn() {
        return numberOfClientsOn;
    }

    @Override
    public void onOpen(WebSocket arg0, ClientHandshake arg1) {
        clients.put(arg0, "");
        System.out.println("Nova conexão de " + getHostNameBy(arg0));
        numberOfClientsOn++;
    }

    @Override
    public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
        clients.remove(arg0);
        System.out.println("Conexão fechada para " + getHostNameBy(arg0));
        numberOfClientsOn--;
    }

    @Override
    public void onMessage(WebSocket arg0, String arg1) {
        System.out.println("Mensagem recebida de " + getHostAdressBy(arg0) + ":" + arg1);
    }

    @Override
    public void onError(WebSocket arg0, Exception arg1) {
        System.out.println("Erro na conexão do " + arg1.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println("Servidor iniciado na porta: " + getPort());
    }

    public ChatWebSocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    public void startServer() {
        this.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nRecebido sinal de desligamento...");
            try {
                this.stopServer();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }));

        System.out.println("Servidor iniciado. Comandos: [stop] [status] [exit]");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();

                switch (input) {
                    case "stop":
                        System.out.println("Servidor parado");
                        this.stopServer();
                        System.exit(0);
                        break;

                    case "status":
                        System.out.println("Clientes conectados: " + this.getNumberOfClientsOn());
                        break;

                    case "exit":
                        this.stopServer();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Comando inválido");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stopServer() throws InterruptedException {
        String message = "Fechando server...";
        System.out.println(message);
        this.stop(1000, message);
    }
}
