package A_18_09_23_Bowling;

public abstract class SalaBowling {
    
    protected int numGiocatori;

    public abstract String fornisciInformazioni() throws InterruptedException;
    
    public abstract void preparaPartita() throws InterruptedException;

    public abstract void gioca(String info) throws InterruptedException;

    public abstract void deposita() throws InterruptedException;

    public void test(int k){
        
    }
}
