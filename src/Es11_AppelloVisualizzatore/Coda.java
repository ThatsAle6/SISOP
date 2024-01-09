package Es11_AppelloVisualizzatore;

import java.util.LinkedList;

public abstract class Coda {
    protected int maxSize;
    protected LinkedList<String> lista;

    public Coda(int maxSize){
        this.maxSize = maxSize;
        lista = new LinkedList<String>();
    }

    public abstract void inserisci(String stringhe[]) throws InterruptedException;
    public abstract String preleva() throws InterruptedException;

    public void test(){
        Visualizzatore visualizzatore = new Visualizzatore(this);

        int numUtenti = 10;
        Utente utenti[] = new Utente[numUtenti];
        for(int i=0; i<numUtenti; i++){
            utenti[i] = new Utente(i, this);
        }

        visualizzatore.start();
        for(int i=0; i<numUtenti; i++){
            utenti[i].start();
        }
    }
}
