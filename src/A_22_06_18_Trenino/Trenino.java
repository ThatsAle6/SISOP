package A_22_06_18_Trenino;

public abstract class Trenino {


    protected int[] cabine = new int[10];
    protected static int numPostiCabina = 10;


    public abstract void turSali() throws InterruptedException;

    public abstract void turScendi() throws InterruptedException;

    public abstract void impFaiScendere() throws InterruptedException;

    public abstract void impFaiSalire() throws InterruptedException;

    public abstract void impMuovi() throws InterruptedException;

     public void Test(){

     }
}