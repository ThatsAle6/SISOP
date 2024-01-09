package Esercitazione6;

import java.util.concurrent.Semaphore;

public class ContoCorrenteSemaphore extends ContoCorrente {
    
    private Semaphore mutex = new Semaphore(1);

    public ContoCorrenteSemaphore(int depositoIniziale){
        super(depositoIniziale);
    }

    public void deposita(int importo){
        try{
            mutex.acquire();
            deposito += importo;
            mutex.release();

        }catch(InterruptedException e){
            // TODO: handle exception
        }
    }

    public void preleva(int importo){
        try {
            mutex.acquire();
            deposito -= importo;
            mutex.release();

        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
