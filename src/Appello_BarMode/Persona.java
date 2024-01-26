package Appello_BarMode;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Persona extends Thread {

    private static final int[] MIN_ATTESA = {5, 20};
    private static final int[] MAX_ATTESA = {10, 40};

    private Random random = new Random();
    private Bancone bancone;

    public Persona(Bancone b){
        this.bancone = b;
    }

    public void run(){
        try {
            attendi(0,20);
            
            int op = bancone.scegli();
            bancone.inizia(op);
            attendi(MIN_ATTESA[op], MAX_ATTESA[op]);
            bancone.finisci(op);

            op = 1 - op;
            bancone.inizia(op);
            attendi(MIN_ATTESA[op], MAX_ATTESA[op]);
            bancone.finisci(op);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attendi(int min, int max) throws InterruptedException{
        TimeUnit.SECONDS.sleep(random.nextInt(max - min + 1)+ min);
    }
}
