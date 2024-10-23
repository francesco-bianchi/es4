package com.example;

import java.util.ArrayList;
import java.util.Random;

public class LivelliClassifica {
    private ArrayList livelli = new ArrayList <Integer>();

        Random random = new Random();
        int numRand = random.nextInt(100);

    public void creaLivelli(){
        livelli.add(numRand);
        
    }

    public ArrayList<Integer> getLivelli() {
        return livelli;
    }

    
}
