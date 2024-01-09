package Es12_AppelloAziendaAgr;

import java.util.concurrent.TimeUnit;

public class Magazziniere extends Thread {
    private AziendaAgricola aziendaAgricola;

    public Magazziniere(AziendaAgricola ag){
        this.aziendaAgricola = ag;
    }

    public void run(){
        while (true) {
            try {
                aziendaAgricola.carica();
                TimeUnit.SECONDS.sleep(10);            
            
        }    catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String toString(){
        return "Il magazziniere ha caricato ["+aziendaAgricola.sacchettiIniziali+"] sacchi nel magazzino";
    }

}
