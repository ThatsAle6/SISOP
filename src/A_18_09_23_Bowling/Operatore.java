package A_18_09_23_Bowling;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Operatore extends Thread {
    
    public SalaBowling sala;
    public Random random = new Random();

    public int MIN_ATTESA = 5;
    public int MAX_ATTESA = 10; 

    public Operatore(SalaBowling sb){
        this.sala = sb;
    }

    public void run(){
        try {
            sala.preparaPartita();
            attendi(); //si attende la preparazione della pista

            sala.deposita();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void attendi() throws InterruptedException{
        TimeUnit.SECONDS.sleep(random.nextInt(MAX_ATTESA-MIN_ATTESA+1)-MIN_ATTESA);
    }

}
