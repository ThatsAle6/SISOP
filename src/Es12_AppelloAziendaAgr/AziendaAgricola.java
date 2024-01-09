package Es12_AppelloAziendaAgr;

public abstract class AziendaAgricola {

    protected int incasso = 0;
    protected int sacchetti;
    protected final int sacchettiIniziali;

    public AziendaAgricola(int sacchettiIn){
        this.sacchettiIniziali = sacchettiIn;
        this.sacchetti = sacchettiIn;
    }

    public abstract void acquistaSacchi(int nSacchi) throws InterruptedException;

    public abstract void ritiraSacchi() throws InterruptedException;

    public abstract void carica() throws InterruptedException;

    public void Test(int nClienti){
        Cliente clienti[] = new Cliente[nClienti];
        for(int i=0; i<nClienti; i++){
            clienti[i] = new Cliente(this, i);
            clienti[i].start();
        }

        Magazziniere magazziniere = new Magazziniere(this);
        magazziniere.start();

        for(int i=0; i<nClienti; i++){
            try {
                clienti[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.format("Incasso totale %d", this.incasso);
    }
    
}
