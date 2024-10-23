package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(3000);
        
        Random random = new Random();
        int numRand = random.nextInt(100);

        System.out.println("Server connesso");
        
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connesso");
            GestoreStringhe gs = new GestoreStringhe(socket, numRand);
            gs.start();
        }
    }
}