package A_20_03_23_Pizzeria;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente extends Thread {

    private Pizzeria pizzeria;
    private Random random = new Random();

    private final int MIN_ATTESA = 5;
    private final int MAX_ATTESA = 10;

    public Cliente(Pizzeria p){
        pizzeria = p;
    }

    public void run(){
        try {
            pizzeria.entra();
            //System.out.format("Il cliente %d entra",Thread.currentThread().getId());
            pizzeria.mangiaPizza();
            consumazione();
            //System.out.format("Il cliente %d ha finito di mangiare",Thread.currentThread().getId());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumazione() throws InterruptedException{
        TimeUnit.SECONDS.sleep(random.nextInt(MAX_ATTESA-MIN_ATTESA+1)+MIN_ATTESA);
    }

}
