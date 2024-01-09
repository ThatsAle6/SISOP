package Es10_AppelloCasello;

public abstract class Casello {
    
    protected final  int numPorte;    //numero porte
    protected final int tariffa;    //tariffa
    protected int incasso;

    public Casello(int numPorte, int tariffa){
        this.incasso = 0;
        this.numPorte = numPorte;
        this.tariffa = tariffa;
    }

    public abstract void accedi(int porta) throws InterruptedException;
    public abstract void pagaEAbbandona(int porta, int km) throws InterruptedException;

    public int getNumPorte(){
        return numPorte;
    }
    public int getIncasso(){
        return incasso;
    }

    public void test(int V){    //V numero di veicoli
        Veicolo[] veicoli = new Veicolo[V];
        
        //creo i veicoli
        for(int i=0; i<V; i++){
            veicoli[i] = new Veicolo(this);
            veicoli[i].start();
        }

        //creo i thread
        for(int i=0; i<V; i++){
            try {
                veicoli[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.format("Incasso finale di %d%n", getIncasso());
    }
}
