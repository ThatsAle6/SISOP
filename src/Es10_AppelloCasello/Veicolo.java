package Es10_AppelloCasello;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Veicolo extends Thread {
    
    //ivari tempi sono abbassati rispetto alla traccia per poter effetturare una simulazione più veloce
    private final int minKm = 5, maxKm = 10;
    private final int minAttesa = 3, maxAttesa = 6, tempoKm = 4;

    private Random random = new Random();
    private Casello casello;

    public Veicolo(Casello c){
        casello = c;
    }

    public void run(){
        try {
            //Op1
            int km = random.nextInt(maxKm - minKm +1) + minKm;
            attendi(km * tempoKm);

            //Op2 si accoda sulla porta
            int porta = random.nextInt(casello.getNumPorte());
            System.out.format("Veicolo %d è entrato nella porta %d%n", getId(), porta);

            //Op3 entra nel casello
            casello.accedi(porta);
            System.out.format("Veicolo %d è entrato nella porta %d%n", getId(), km, porta );

            //Op4
            attendi(minAttesa, maxAttesa);

            //Op5
            casello.pagaEAbbandona(porta, km);
            System.out.format("Veicolo %d paga per %d km e abbandona la porta %d%n",getId(), km, porta);
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attendi(int min, int max) throws InterruptedException{
        TimeUnit.SECONDS.sleep(random.nextInt(max - min +1) + min);
    }

    private void attendi(int secs) throws InterruptedException{
        TimeUnit.SECONDS.sleep(secs);
    }

}
