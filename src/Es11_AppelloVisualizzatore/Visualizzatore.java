package Es11_AppelloVisualizzatore;

import java.util.Random;

public class Visualizzatore extends Thread  {
    private Coda coda;
    private Random random = new Random();

    private int MAX_ATTESA = 1000;

    public Visualizzatore(Coda coda){
        this.coda = coda;
    }

    public void run(){
        while (true) {
            long attesaCasuale = random.nextInt(MAX_ATTESA);
            try {
                sleep(attesaCasuale);
                String stringaPrelevata = coda.preleva();
                System.out.println("Visualizzatore ha prelevato: "+stringaPrelevata);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }  
    }
}
