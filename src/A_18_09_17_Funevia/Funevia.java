package A_18_09_17_Funevia;

public abstract class Funevia {
    
    protected final int TURISTA_A_PIEDI = 0;
    protected final int TURISTA_IN_BICI = 1;

    protected final int POSTI = 6;

    public abstract void pilotaStart() throws InterruptedException;
    public abstract void pilotaEnd() throws InterruptedException;
    public abstract void turistaSali(int t) throws InterruptedException;
    public abstract void turistaScendi(int t) throws InterruptedException;

    public void test(int p, int b){
        Turista[] turista_piedi = new Turista[p];
        Turista[] turista_bici = new Turista[b];

        for(int i=0; i<p; i++){
            turista_piedi[i] = new Turista(this, 0);
            turista_piedi[i].start();
        }

        for(int i=0; i<b; i++){
            turista_bici[i] = new Turista(this, 1);
            turista_bici[i].start();
        }

        Pilota pilota = new Pilota(this);
        pilota.start();

    }

}
