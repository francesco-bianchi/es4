package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 3000);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String numero = "";
        String rigioca;
        String stringaRic ="";
        int tentativi;
        String vett[] = new String[2];
        Scanner sc = new Scanner(System.in);

        do {
            do {
                System.out.println("Indovina il numero");
                numero = sc.nextLine();
                out.writeBytes(numero+ "\n");

                stringaRic = in.readLine();
                vett = stringaRic.split(";");
                if(vett[0].equals("<")){
                    System.out.println("Il numero inserito è più piccolo di quello da indovinare");
                }
                else if(vett[0].equals(">")){
                    System.out.println("Il numero inserito è più grande di quello da indovinare");
                }
                else if(vett[0].equals("!")){
                    System.out.println("Inserire un numero compreso tra 0 e 100");
                }
                else{
                    System.out.println("Il numero è stato indovinato in " + vett[1] + " tentativi");
                }
            } while (!vett[0].equals("="));
            
            System.out.println("Se vuoi rigiocare inserisci s, sennò n: ");
            rigioca = sc.nextLine();
            out.writeBytes(rigioca + "\n");

        } while (rigioca.equals("s"));
        socket.close();
    }
}