package Appello_BarMode;

import java.util.concurrent.Semaphore;

public class BanconeSem extends Bancone{
    private Semaphore mutex = new Semaphore(1);
    private Semaphore[] fila = new Semaphore[NUM_FILE];

    protected int[] numPersoneInFila = new int[NUM_FILE];

    public BanconeSem(){
        super();
        for(int i=0; i<NUM_FILE; i++){
            fila[i] = new Semaphore(MAX_PERSONE_FILA[i], true);
            numPersoneInFila[i] = 0;
        }

    }

    public int scegli() throws InterruptedException{
        int i = -1;
        mutex.acquire();

        if(numPersoneInFila[cassa] > 0)
            i = cassa;
        else if (numPersoneInFila[bancone] > 0) {
            i = bancone;
        } else if (numPersoneInFila[cassa] <= numPersoneInFila[bancone]) {
            i = cassa;
        }else{
            i = bancone;
        }
        System.out.format("Persona[%s] vuole andre %s%n",Thread.currentThread().getName(),
                        (i == cassa ? "in cassa" : "al bancone"));
        mutex.release();
        return i;
    }

    public void inizia(int i) throws InterruptedException{
        mutex.acquire();
        numPersoneInFila[i]++;
        mutex.release();
        
        fila[i].acquire();

        mutex.acquire();
        numPersoneInFila[i]--;
		numPostiLiberi[i]--;
		System.out.format("Persona[%s] si trova in %s%n", Thread
				.currentThread().getName(), (i == cassa ? "in CASSA"
				: "al BANCONE"));
		System.out.println(this);
		mutex.release();

    }

    public void finisci(int i) throws InterruptedException{
        mutex.acquire();
        numPostiLiberi[i]++;

        fila[i].release();
        System.out.format("Persona[%s] � uscita %s%n", Thread.currentThread()
				.getName(), (i == cassa ? "dalla CASSA" : "dal BANCONE"));
		System.out.println(this);
        mutex.release();
    }
    public static void main(String[] args) {
    new BanconeSem().Test(20);
}
    
}
