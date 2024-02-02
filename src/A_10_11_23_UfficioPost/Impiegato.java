package A_10_11_23_UfficioPost;

public class Impiegato extends Thread{
    private UfficioPostale ufficioPostale;

    public Impiegato(UfficioPostale uf){
        this.ufficioPostale = uf;
    }

    public void run(){
        try{
            ufficioPostale.prossimoCliente();
            

            ufficioPostale.eseguiOperazione();

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
