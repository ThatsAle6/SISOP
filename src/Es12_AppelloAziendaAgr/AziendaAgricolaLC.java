package Es12_AppelloAziendaAgr;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AziendaAgricolaLC extends AziendaAgricola{
    private Lock l = new ReentrantLock();
    private Condition paga = l.newCondition();
    private Condition ritira = l.newCondition();
    private Condition carica = l.newCondition();

    private LinkedList<Thread> cassa = new LinkedList<Thread>();
    private LinkedList<Thread> magazzino = new LinkedList<Thread>();

    public AziendaAgricolaLC(int sacchetti){
        super(sacchetti);
    }

    @Override
    public void acquistaSacchi(int nSacchi) throws InterruptedException { 
        l.lock();
        try{
            cassa.add(Thread.currentThread());
            while (!possoPagare()) {
                paga.await();
            }
            cassa.remove();
            incasso += nSacchi*3;
            paga.signalAll();

        }finally{
            l.unlock();
        }
    }
    public boolean possoPagare(){
        return Thread.currentThread() == cassa.getFirst();
    }

    @Override
    public void ritiraSacchi() throws InterruptedException {
        l.lock();
        try{
            magazzino.add(Thread.currentThread());
            while (!possoPrelevare()) {
                ritira.await();
            }
            magazzino.remove();
            sacchetti--;
            System.out.println("Sacchetti: "+sacchetti);
            if(sacchetti == 0)
                carica.signal();
            
            ritira.signalAll();
        }finally{
            l.unlock();
        }
    }
    public boolean possoPrelevare(){
        return Thread.currentThread() == magazzino.getFirst() && sacchetti >0;
    }

    @Override
    public void carica() throws InterruptedException {
        l.lock();
        try{
            while (sacchetti > 0) {
                carica.await();
            }
            sacchetti+=sacchettiIniziali;
            System.out.println("Restock: "+sacchetti);
            ritira.signalAll();
        }finally{
            l.unlock();
        }
    }

    public static void main(String[] args) {
        AziendaAgricolaLC aa = new AziendaAgricolaLC(50);
        int numClienti = 100;
        aa.Test(numClienti);
    }
    
}
