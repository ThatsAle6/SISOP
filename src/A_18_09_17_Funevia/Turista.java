package A_18_09_17_Funevia;

public class Turista extends Thread{

    private Funevia funevia;
    private int tipo;

    public Turista(Funevia f, int t){
        this.funevia = f;
        this.tipo = t;
    }

    public void run(){
        try {
            funevia.turistaSali(tipo);

            funevia.turistaScendi(tipo);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}
