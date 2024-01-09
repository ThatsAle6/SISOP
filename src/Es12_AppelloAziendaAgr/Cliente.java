package Es12_AppelloAziendaAgr;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente extends Thread {

    private int id;
    private int numSacchiPrelevare;
    private Random random = new Random();
    private AziendaAgricola aziendaAgricola;

    public Cliente(AziendaAgricola aziendaAgricola, int id){
        this.aziendaAgricola = aziendaAgricola;
        this.id = id;
    }

    public void run(){
        numSacchiPrelevare = decidi(10);
        try {
            //OP1 si presenta in cassa per l'acquisto dei sacchetti
            aziendaAgricola.acquistaSacchi(numSacchiPrelevare);
            while (numSacchiPrelevare>0) {
                aziendaAgricola.ritiraSacchi();
                System.out.print(this);
                TimeUnit.SECONDS.sleep(1);
                numSacchiPrelevare--;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int decidi(int max){
        return random.nextInt(max)+1;
    }
    public String toString(){
        return "Il cliente ["+id+"] ha ritirato 1 sacco. Deve ancora ritirare["+numSacchiPrelevare+"] sacchi.\n"; 
    }
    
}
