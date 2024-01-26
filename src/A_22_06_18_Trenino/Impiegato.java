package A_22_06_18_Trenino;

public class Impiegato extends Thread {
     
    private Trenino trenino;

    public Impiegato(Trenino t){
        this.trenino = t;
    }
    
    public void run(){
        while (true) {
            try {

                trenino.impFaiScendere();
                
                trenino.impFaiSalire();

                trenino.impMuovi();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
