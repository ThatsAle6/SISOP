package A_18_09_17_Funevia;

import java.util.concurrent.TimeUnit;

public class Pilota extends Thread{

    private Funevia funevia;

    public Pilota(Funevia f){
        this.funevia = f;
    }

    public void run(){
        try {
            while (true) {
                funevia.pilotaStart();
                TimeUnit.SECONDS.sleep(5);

                funevia.pilotaEnd();;
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
