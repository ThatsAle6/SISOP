package Es11_AppelloVisualizzatore;

import java.util.Random;

public class Utente extends Thread {
    
    private Coda coda;
    private Random random = new Random();
    private int id;

    private int MAX_ATTESA = 1000;

    private int MAX_STRINGHE = 5;
    private int MIN_STRINGHE = 1;

    public Utente(int id, Coda coda){
        this.id = id;
        this.coda = coda;
    }

    public void run(){
        int ns = 0;
        while (true) {
            long attesaCasuale = random.nextInt(MAX_ATTESA);
            try {
                sleep(attesaCasuale);
                
                int numStringhe = MIN_STRINGHE + random.nextInt(MAX_STRINGHE);
                String stringheDaInseririe[] = new String[numStringhe];
                for(int i=0; i<numStringhe; i++){
                    stringheDaInseririe[i] = new String("S_"+id+"_"+(ns++));
                }
                coda.inserisci(stringheDaInseririe);
                System.out.println("Utente: "+id+" ha inserito: "+numStringhe+" Stringhe.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
