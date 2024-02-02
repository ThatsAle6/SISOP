package A_18_09_17_Funevia;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FuneviaSem extends Funevia {

    //Gestione Pilota
    private Semaphore start = new Semaphore(1);
    private Semaphore stampa = new Semaphore(0);

    //Gestione Turisiti
    private Semaphore sali = new Semaphore(0);
    //private Semaphore entraBici = new Semaphore(0);
    private Semaphore scendi = new Semaphore(0);
    //private Semaphore esciBici = new Semaphore(0);

    //Supporto
    private Semaphore mutex = new Semaphore(1);
    private ArrayList<Long> idTuristi = new ArrayList<>(); //tiene conto dei Turisti che sono saliti sulla funevia
    private int postiOccupati = 0;




    public void pilotaStart() throws InterruptedException{
        start.acquire();
        if(postiOccupati == 0){
            sali.release(6);
        }
        stampa.release();
    }

    public void pilotaEnd() throws InterruptedException{
        stampa.acquire();
        System.out.println("Inizia viaggio");
		System.out.print("ID turisti presenti: ");
		for (int i = 0; i < idTuristi.size(); i++) {
			System.out.print(idTuristi.get(i) + " ");
		}
        System.out.println("\n");

        scendi.release(6);
    }

    public void turistaSali(int t) throws InterruptedException{ 
        sali.acquire();
        if(t == TURISTA_A_PIEDI){
            mutex.acquire();
            idTuristi.add(Thread.currentThread().getId());
            postiOccupati++;
            System.out.println("Sale il turista a piedi: "+Thread.currentThread().getId());
            if (postiOccupati == POSTI) {
                start.release();
            }
            mutex.release();
        }else{
            mutex.acquire();
            idTuristi.add(Thread.currentThread().getId());
            System.out.println("Sale il turista in bici: "+Thread.currentThread().getId());
            postiOccupati++;
            if (postiOccupati == POSTI) {
                start.release();
            }
            mutex.release();
        }
    }

    public void turistaScendi(int t) throws InterruptedException{
        scendi.acquire();
        if(t == TURISTA_A_PIEDI){
            mutex.acquire();
            idTuristi.remove(Thread.currentThread().getId());
            postiOccupati--;
            System.out.println("scende il turista in piedi: "+Thread.currentThread().getId());
            mutex.release();
        }else{
            mutex.acquire();
            idTuristi.remove(Thread.currentThread().getId());
            postiOccupati--;
            System.out.println("scende il turista in bici: "+Thread.currentThread().getId());
            mutex.release();
        }
    }

    public static void main(String[] args) {
        Funevia funevia = new FuneviaSem();
        funevia.test(18, 9);
    }
}
