package Appello_BarMode;

public abstract class Bancone {

    protected static final int cassa=0;
    protected static final int bancone=1;

    protected static final int NUM_FILE=2;
    protected static final int[] MAX_PERSONE_FILA = {1,4};

    protected int[] numPostiLiberi = new int[NUM_FILE];

    public Bancone(){
        for(int i=0; i<NUM_FILE; i++){
            numPostiLiberi[i] = MAX_PERSONE_FILA[i];
        }
    }

    
    public abstract int scegli() throws InterruptedException;

    public abstract void inizia(int i) throws InterruptedException;

    public abstract void finisci(int i) throws InterruptedException;

    public void Test(int n){
        
        Persona persona[] = new Persona[n];

        for(int i=0; i<n; i++){
            persona[i] = new Persona(this);
            persona[i].start();
        }



    }
}
