package A_20_03_23_Pizzeria;

public abstract class Pizzeria{

    protected final int  postiLiberi = 5;
    protected final int numFile = 1;

    abstract void entra() throws InterruptedException;
    abstract void mangiaPizza() throws InterruptedException;
    abstract void preparaPizza() throws InterruptedException;
    abstract void serviPizza() throws InterruptedException;

    public void test(int n){

        Cliente c[] = new Cliente[n];
        for(int i=0; i<n; i++){
            c[i] = new Cliente(this);
        }

        Pizzaiolo p = new Pizzaiolo(this);
        p.setDaemon(true);
        p.start();

        for(int i=0; i<n; i++){
            c[i].start();
        }
    }

}