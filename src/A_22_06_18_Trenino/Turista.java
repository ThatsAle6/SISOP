package A_22_06_18_Trenino;

import java.util.concurrent.TimeUnit;

public class Turista extends Thread{
    
    private Trenino trenino;

    public Turista(Trenino t){
        this.trenino = t;
    }

    public void run(){
        try {
            trenino.turSali();
            TimeUnit.SECONDS.sleep(10*3);
            System.out.format("Il turista %d sale sul Trenino"+Thread.currentThread().getId());
            trenino.turScendi();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
