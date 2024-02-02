package A_10_11_23_UfficioPost;

public abstract class UfficioPostale {

    protected String[] operazione = {"A","B","C"};
    protected final int TICKET = 50;
 
    

    public abstract boolean ritiraTicket(String operazioni) throws InterruptedException;

    public abstract void attendiSportello(String operazione) throws InterruptedException;

    public abstract void prossimoCliente() throws InterruptedException;

    public abstract void eseguiOperazione() throws InterruptedException;

    public String getOperazione(int i) {
        return operazione[i];
    }

    public void Test(int nClienti){
        Cliente[] clienti = new Cliente[nClienti];

        Impiegato A = new Impiegato(this);
        Impiegato B = new Impiegato(this);
        Impiegato C = new Impiegato(this);

        A.start();
        B.start();
        C.start();

        for(int i=0; i<nClienti; i++){
            clienti[i] = new Cliente(this);
            clienti[i].start();
        }
    }
}