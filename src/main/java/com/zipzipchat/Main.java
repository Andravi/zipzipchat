package com.zipzipchat;

import java.net.URISyntaxException;
import java.util.Scanner;

import com.zipzipchat.ChatClient.ChatClient;
import com.zipzipchat.ChatWebSocketServer.ChatWebSocketServer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner reader = new Scanner(System.in);
        // var connections = 0;

        System.out.println("""
                ###Iniciando Chat###
                0: Server
                1: Cliente
                """);
        char option = reader.next().toCharArray()[0];
        if (option == '0') {
            System.out.println("Server");
            ChatWebSocketServer server = new ChatWebSocketServer(8080);
            server.startServer();
        } else if (option == '1') {
            System.out.println("Cliente");
            ChatClient client;
            try {
                client = new ChatClient();
                client.connect();
            } catch (URISyntaxException e) {
                System.out.println("erro");
                e.printStackTrace();
            }

        } else {
            System.out.println("Nenhuma opção válida escolhida");

        }
    }
}