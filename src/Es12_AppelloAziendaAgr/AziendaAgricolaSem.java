package Es12_AppelloAziendaAgr;

import java.util.concurrent.Semaphore;

public class AziendaAgricolaSem extends AziendaAgricola {

    private Semaphore cassa = new Semaphore(1, true);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore magazzino;
    private Semaphore magazziniere = new Semaphore(0);

    public AziendaAgricolaSem(int sacchetti){
        super(sacchetti);
        magazzino = new Semaphore(sacchetti, true);
    }

    public void acquistaSacchi(int nSacchi) throws InterruptedException{
        cassa.acquire();
        incasso += nSacchi * 3;
        cassa.release();
    }

    public void ritiraSacchi() throws InterruptedException{
        magazzino.acquire();
        mutex.acquire();
        sacchetti--;
        if (sacchetti == 0){
            magazziniere.release();
        }
        mutex.release();
    }

    public void carica() throws InterruptedException{
        magazziniere.acquire();
        mutex.acquire();
        sacchetti += sacchettiIniziali;
        System.out.println("Sacchetti: "+sacchetti);
        mutex.release();
        magazzino.release(200);
    }
  
  
  
    public static void main(String[] args) {
        AziendaAgricolaSem aa = new AziendaAgricolaSem(200);
        aa.Test(100);
    }
    
}
