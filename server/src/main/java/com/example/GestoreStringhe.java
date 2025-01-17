
package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GestoreStringhe extends Thread {
    Socket socket;
    int size =0;
    int numRand;
    LivelliClassifica livelli;

    GestoreStringhe(Socket socket){
        this.socket = socket;
        livelli = new LivelliClassifica();
        livelli.creaLivelli();
        numRand = livelli.getLivelli().get(0);
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

            do {
                do {
                    System.out.println(numRand);
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
                    if(livelli.getLivelli().size() <= size){
                        livelli.creaLivelli();
                    }
                    numRand = livelli.getLivelli().get(size);
                    size++;
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