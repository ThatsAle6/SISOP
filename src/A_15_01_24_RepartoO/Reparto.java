package A_15_01_24_RepartoO;

public abstract class Reparto {

    protected int numStanze = 3;


    public Reparto(int s){
        this.numStanze = s;
    }

    public int getNumStanze(){
        return numStanze;
    }

    public abstract void entraMedico(int s) throws InterruptedException;

    public abstract void esciMedico(int s) throws InterruptedException;

    public abstract void entraVisitatore(int s) throws InterruptedException;

    public abstract void esciVisitatore(int s) throws InterruptedException;

    public void test(int m, int v){
        
        Medico[] medico = new Medico[m];
        Visitatore[] visitatore = new Visitatore[v];

        for(int i=0; i<m; i++){
            medico[i] = new Medico(this);
            medico[i].start();
        }

        for(int i=0; i<v; i++){
            visitatore[i] = new Visitatore(this);
            visitatore[i].start();
        }
        
        for(int i=0; i<v; i++){
            try {
                visitatore[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
