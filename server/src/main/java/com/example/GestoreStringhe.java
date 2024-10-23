
package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GestoreStringhe extends Thread {
    Socket socket;
    int numRand;

    GestoreStringhe(Socket socket, int numRand){
        this.socket = socket;
        this.numRand = numRand;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String stringaRic;
            int numRic;
            int tentativi =0;
            boolean indovinato= false;
            boolean rigioca = false;
            System.out.println(numRand);

            do {
                do {
                    tentativi++;
                    stringaRic = in.readLine();
                    numRic = Integer.parseInt(stringaRic);
    

                    if(numRic<0 || numRic>100){
                        tentativi--;
                        out.writeBytes("!" + "\n");
                    }
                    else if(numRic == numRand){
                        out.writeBytes("=;" + tentativi + "\n");
                        indovinato = true;
                    }
                    else if(numRic > numRand){
                        out.writeBytes(">" + "\n");
                    }
                    else if(numRic < numRand){
                        out.writeBytes("<" + "\n");
                    }
    
                } while (!indovinato);
    
                if(in.readLine().equals("s")){
                    rigioca = true;
                    tentativi = 0;
                }
                else {
                    rigioca = false;
                }
            } while (rigioca);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client disconnesso");
    }
}