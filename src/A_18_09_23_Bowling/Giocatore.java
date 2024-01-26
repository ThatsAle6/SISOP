package A_18_09_23_Bowling;

public class Giocatore extends Thread{
    
    private SalaBowling sala;
    private String nome;
    private int numScarpe;

    public Giocatore(SalaBowling sb, String nome, int num){
        this.sala = sb;
        this.nome = nome;
        this.numScarpe = num;
    }

    public void run(){
        int numTiri = 10;
        try{

            sala.fornisciInformazioni();
            
            String tiri= new String(); 
            for(int i=0; i<numTiri; i++){
                tiri = new String("Il giocatore: "+getName()+" ha ancora a dispozione: "+(numTiri--)+" tiri");
            }
            sala.gioca(tiri);

        }catch( InterruptedException e){
            e.printStackTrace();
        }
    }

    
}
