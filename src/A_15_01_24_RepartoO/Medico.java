package A_15_01_24_RepartoO;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Medico extends Thread{

    private Reparto reparto;
    private Random random = new Random();
    
    private int MAX_VISITA = 5;
    private int MIN_VISITA = 3;

    public Medico(Reparto r){
        this.reparto = r;
    }

    public void run(){
        try {
            while(true){
                int s = random.nextInt(reparto.getNumStanze());
                reparto.entraMedico(s);
                System.out.format("stanza: %d%n",s);
                visita();
                reparto.esciMedico(s);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void visita()throws InterruptedException{
       TimeUnit.SECONDS.sleep(random.nextInt(MAX_VISITA - MIN_VISITA +1)- MIN_VISITA);
    }
    
}
