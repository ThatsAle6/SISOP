package A_18_09_17_Funevia;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FuneviaSem2 extends Funevia{
    
    //Gestione Pilota
    private Semaphore start = new Semaphore(1);
    private Semaphore stampa = new Semaphore(0);
    private Semaphore permettiSali = new Semaphore(0);
    private Semaphore permettiScendi = new Semaphore(0);

    //Gestione Turisiti
    private Semaphore saliPiedi = new Semaphore(0);
    private Semaphore saliBici = new Semaphore(0);
    private Semaphore scendiPiedi = new Semaphore(0);
    private Semaphore scendiBici = new Semaphore(0);

    //Supporto
    private Semaphore mutex = new Semaphore(1);
    private ArrayList<Long> idTuristi = new ArrayList<>(); //tiene conto dei Turisti che sono saliti sulla funevia
    private int postiOccupati = 0;
    private int tipo;
    private final int numPersonePiedi = 6;
    private final int numPersoneBici = 3;




    public void pilotaStart() throws InterruptedException{
        start.acquire();
        if(postiOccupati == 0){
            permettiSali.release(6);
            if (tipo==0) {
                saliPiedi.release(numPersonePiedi); 
            }else{
                saliBici.release(numPersoneBici);
            }
        }
        System.out.println("\n--------------------------------");

        stampa.release();
    }

    public void pilotaEnd() throws InterruptedException{
        stampa.acquire();
        System.out.println("Cabina giunta a destinazione");
		System.out.print("ID turisti presenti: ");
		for (int i = 0; i < idTuristi.size(); i++) {
			System.out.print(idTuristi.get(i) + " ");
		}

        if (postiOccupati==POSTI){
            permettiScendi.release(6);
            if (tipo==1) {
                scendiBici.release(numPersoneBici);    
            }else{
                scendiPiedi.release(numPersonePiedi);
            }
        }


    }

    public void turistaSali(int t) throws InterruptedException{ 
        permettiSali.acquire();
        if(t == TURISTA_A_PIEDI){
            saliPiedi.acquire();
            mutex.acquire();
            idTuristi.add(Thread.currentThread().getId());
            postiOccupati++;
            System.out.println("Sale il turista a piedi: "+Thread.currentThread().getId());
            if (postiOccupati == POSTI) {
                tipo=1;
                start.release();
            }
            mutex.release();
        }else{
            saliBici.acquire();
            mutex.acquire();
            idTuristi.add(Thread.currentThread().getId());
            System.out.println("Sale il turista in bici: "+Thread.currentThread().getId());
            postiOccupati+=2;
            if (postiOccupati == POSTI) {
                tipo=0;
                start.release();
            }
            mutex.release();
        }
    }

    public void turistaScendi(int t) throws InterruptedException{
        permettiScendi.acquire();
        if(t == TURISTA_A_PIEDI){
            scendiPiedi.acquire();
            mutex.acquire();
            idTuristi.remove(Thread.currentThread().getId());
            postiOccupati--;
            System.out.println("scende il turista a piedi: "+Thread.currentThread().getId());
            mutex.release();
        }else{
            scendiBici.acquire();
            mutex.acquire();
            idTuristi.remove(Thread.currentThread().getId());
            postiOccupati--;
            System.out.println("scende il turista in bici: "+Thread.currentThread().getId());
            mutex.release();
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Funevia funevia = new FuneviaSem2();
        funevia.test(18, 9);
    }

    
}
