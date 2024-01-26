package A_15_01_24_RepartoO;


import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RepartoLC extends Reparto{

    private Lock l = new ReentrantLock();
    private Condition entraMedico = l.newCondition();
    private Condition entraVisitatore  = l.newCondition();

    private LinkedList<Thread> codaMedico = new LinkedList<>();
    private LinkedList<Thread> codaVisitatori = new LinkedList<>();

    private boolean medico = false;
    private int visitatori = 0;
   
    public RepartoLC(int numStanze){
        super(numStanze);
    }

    public void entraMedico(int s) throws InterruptedException{
        l.lock();
        try{
            
            codaMedico.add(Thread.currentThread());
            medico=true;
            while (!puoVisitareM()) {
                entraMedico.await();
                System.out.println("Il medico aspetta");
            }
            
            System.out.println("Il medico: "+Thread.currentThread().getId()+" entra nella stanza");
            
        }finally{
            l.unlock();
        }
    }
    private boolean puoVisitareM(){
        if(!medico && visitatori<5)
            return true;
        return false;
    }

    public void esciMedico(int s) throws InterruptedException{
        l.lock();
        try{
            medico = false;
            System.out.println("Il medico: "+Thread.currentThread().getId()+" lascia la stanza");
            codaMedico.remove(Thread.currentThread());
            entraMedico.signalAll();
        }finally{
            l.unlock();
        }
    }

    public void entraVisitatore(int s) throws InterruptedException{
        l.lock();
        try{
            codaVisitatori.add(Thread.currentThread());
            while (!puoVisitareV()) {
                entraVisitatore.await();                
            }
            visitatori++;
            System.out.println("Il visitatore: "+Thread.currentThread().getId()+" entra nella stanza");
            
        }finally{
            l.unlock();
        }
    }
    private boolean puoVisitareV(){
        if(!medico || visitatori>4){
            return false;
        }
        return true;
    }

    public void esciVisitatore(int s) throws InterruptedException{
        l.lock();
        try{

            visitatori--;
            System.out.println("Il visitatore: "+Thread.currentThread().getId()+" lascia la stanza");
            codaVisitatori.remove(Thread.currentThread());
            entraVisitatore.signalAll();
        }finally{
            l.unlock();
        }
    }

    public static void main(String[] args) {
        RepartoLC rs = new RepartoLC(3);
        rs.test(1, 10);
    }
    
}
