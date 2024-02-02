package A_10_11_23_UfficioPost;

import java.util.Random;

public class Cliente extends Thread{

    private UfficioPostale ufficioPostale;
    private Random random = new Random();

    public Cliente(UfficioPostale uf){
        this.ufficioPostale = uf;
    }

    public void run(){
        try{
            int op=scegli();
            ufficioPostale.ritiraTicket(ufficioPostale.getOperazione(op));
            //System.out.format("Cliente %d ritira ticket: %s%n",Thread.currentThread().getId(),ufficioPostale.getOperazione(op));

            ufficioPostale.attendiSportello(ufficioPostale.getOperazione(op));
            //System.out.format("Cliente %d in attesa allo sportello: %s%n",Thread.currentThread().getId(),ufficioPostale.getOperazione(op));

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public int scegli(){
        int op=random.nextInt(ufficioPostale.operazione.length);
        return op;
    }
    
}
