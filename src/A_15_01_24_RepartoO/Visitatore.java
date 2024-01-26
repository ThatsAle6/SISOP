package A_15_01_24_RepartoO;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Visitatore extends Thread{

    private Reparto reparto;
    private Random random = new Random();

    private int MAX_VISITA = 15;
    private int MIN_VISITA = 10;

    public Visitatore(Reparto r){
        this.reparto = r;
    }

    public void run(){
        try{
            int s=random.nextInt(reparto.getNumStanze());
            reparto.entraVisitatore(s);
            visita();
            reparto.esciVisitatore(s);

        }catch( InterruptedException e){
            e.printStackTrace();
        }
    }

    public void visita()throws InterruptedException{
        TimeUnit.SECONDS.sleep(random.nextInt(MAX_VISITA-MIN_VISITA+1)-MIN_VISITA);
    }
}
