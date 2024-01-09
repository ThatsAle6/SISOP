package Es11_AppelloVisualizzatore;

import java.util.concurrent.Semaphore;

public class CodaSem extends Coda {

    private Semaphore inserimento;
    private Semaphore prelievo;
    private Semaphore mutex;

    public CodaSem(int maxSize){
        super(maxSize);
        inserimento = new Semaphore(maxSize);
        prelievo = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void inserisci(String stringhe[]){
        try {
            inserimento.acquire();

            mutex.acquire();
            for(int i=0; i<stringhe.length; i++){
                lista.addLast(stringhe[i]);
            }
            mutex.release();
            prelievo.release(stringhe.length);
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String preleva(){
        String result = null;
        try {
            prelievo.acquire();

            mutex.acquire();
            result = lista.removeFirst();
            mutex.release();

            inserimento.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        CodaSem coda = new CodaSem(maxSize);
        coda.test(); 
    }
    
}
