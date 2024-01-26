package A_15_01_24_RepartoO;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RepartoLC2 extends Reparto {

    private Lock l;
    private Condition[] medicConditions;
    private Condition[] vistatorConditions;

    private boolean[] mediciPresenti;
    private int numVisitatori = 0;

    public RepartoLC2(int numStanze){
        super(numStanze);
        l = new ReentrantLock();
        medicConditions = new Condition[numStanze];
        vistatorConditions = new Condition[numStanze];
        mediciPresenti = new boolean[numStanze];

        for(int i=0; i<numStanze; i++){
            medicConditions[i] = l.newCondition();
            vistatorConditions[i] = l.newCondition();
            mediciPresenti[i] = false;
        }
    }

    @Override
    public void entraMedico(int s) throws InterruptedException {
        l.lock();
        try{
            while (mediciPresenti[s] || numVisitatori > 0) {
                medicConditions[s].await();
            }
            mediciPresenti[s] = true;
            System.out.format("Il medico %d entra nella stanza %d.%n",Thread.currentThread().getId(), s);
        }finally{
            l.unlock();
        }
    }

    @Override
    public void esciMedico(int s) throws InterruptedException {
        l.lock();
        try{
            System.out.format("Il medico %d lascia la stanza %d.%n",Thread.currentThread().getId(), s);
            mediciPresenti[s] = false;
            medicConditions[s].signalAll();

        }finally{
            l.unlock();
        }
    }

    @Override
    public void entraVisitatore(int s) throws InterruptedException {
        l.lock();
        try{
            while (mediciPresenti[s] || numVisitatori>=5) {
                vistatorConditions[s].await();
            }
            System.out.format("Il visitatore %d entra nella stanza %d.%n",Thread.currentThread().getId(), s);
            numVisitatori++;
        }finally{
            l.unlock();
        }
    }

    @Override
    public void esciVisitatore(int s) throws InterruptedException {
        l.lock();
        try{
            numVisitatori--;
            if(numVisitatori == 0){
                vistatorConditions[s].signalAll();
            }

        }finally{
            l.unlock();
        }
    }
    
    public static void main(String[] args) {
        RepartoLC2 rp2 = new RepartoLC2(3);
        rp2.test(4, 20);
    }
}
