package A_22_06_18_Trenino;

import java.util.concurrent.Semaphore;

public class TreninoSem extends Trenino {

    private int[] turistiCabina= new int[10];

    private Semaphore permettiSalita = new Semaphore(0);
    private Semaphore permettiDiscesa = new Semaphore(0);
    private Semaphore scatto = new Semaphore(0);

    private Semaphore turistaScendi = new Semaphore(0);
    private Semaphore turistaSale = new Semaphore(1);

    private Semaphore[] cabine;
    private Semaphore mutex = new Semaphore(1); 
    
    private int turistiAttesa = 0;
    private int scatti=0;

    public TreninoSem(){
        for(int i=0; i<10; i++){
            cabine[i] = new Semaphore(0);
        }
    }
    
    int i=0; //indica la cabina attuale

    @Override
    public void turSali() throws InterruptedException {
        turistaSale.acquire();
        mutex.acquire();
        turistiAttesa++;
        if(turistiAttesa == 10){
            permettiSalita.release();
        }
        System.out.format("Il turista %d è in attesa di salire sulla cabina"+Thread.currentThread().getId());
        mutex.release();
    }

    @Override
    public void turScendi() throws InterruptedException {
        turistaScendi.acquire();
        mutex.acquire();
        turistiCabina[i]=0;

        mutex.release();
    }

    @Override
    public void impFaiScendere() throws InterruptedException {
        permettiDiscesa.acquire();
        mutex.acquire();
        if(turistiCabina[i] > 0){
            turistaScendi.release();
        }
        mutex.release();
    }

    @Override
    public void impFaiSalire() throws InterruptedException {
        permettiSalita.acquire();
        cabine[i].acquire();
        mutex.acquire();
        turistiCabina[i]++;
        if(turistiCabina[i] == 10){
            scatto.release();
            turistiAttesa=0;
        }
        mutex.release();

        
    }

    @Override
    public void impMuovi() throws InterruptedException {
        scatto.acquire();
        mutex.acquire();
        scatti++;
        if(scatti == 10){
            cabine[i].release();
            permettiDiscesa.release();
        }
        mutex.release();
    }
    

}
