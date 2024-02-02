package A_10_11_23_UfficioPost;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UfficioPostaleLC extends UfficioPostale {

    private Lock l = new ReentrantLock();
    private Condition prossimo = l.newCondition();
    private Condition svolgiA = l.newCondition();
    private Condition svolgiB = l.newCondition();
    private Condition svolgiC = l.newCondition();

    private LinkedList<Thread> sportelloA = new LinkedList<Thread>();
    private LinkedList<Thread> sportelloB = new LinkedList<Thread>();
    private LinkedList<Thread> sportelloC = new LinkedList<Thread>();

    public UfficioPostaleLC(){
        super();
    }



    public boolean ritiraTicket(String operazione) throws InterruptedException{
        l.lock();
        try{
            LinkedList<Thread> coda = getCoda(operazione);
            if(coda.size() < TICKET){
                System.out.format("Il cliente [%d] ha ritirato un ticket: %s%n",Thread.currentThread().getId(), operazione);
                return true;
            }
            else{
                return false;
            }
        }finally{
            l.unlock();
        }
    }

    public void attendiSportello(String operazione) throws InterruptedException{
        l.lock();
        try{
            LinkedList<Thread> coda = getCoda(operazione);
            coda.add(Thread.currentThread());
            prossimo.signal();
        }finally{
            l.unlock();
        }
    }


    public void prossimoCliente() throws InterruptedException{
        l.lock();
        try{
            while (!ciSonoClienti()) {
                prossimo.await();                
            }
            
            if (Thread.currentThread() == sportelloA.getLast()) {
                svolgiA.signal();
            }else if (Thread.currentThread() == sportelloB.getLast()) {
                svolgiB.signal();
            }else if (Thread.currentThread() == sportelloC.getLast()) {
                svolgiC.signal();
            }else{

            }
        }finally{
            l.unlock();
        }
    }
    private boolean ciSonoClienti(){
        if( sportelloA.isEmpty() || sportelloB.isEmpty() || sportelloC.isEmpty()){
            return false;
        }
        return true;
    }


    public void eseguiOperazione() throws InterruptedException{
        l.lock();
        try{
            while (!possoEseguire()) {
                svolgiA.await();
                svolgiB.await();
                svolgiC.await();
            }
            if (Thread.currentThread() == sportelloA.getLast()) {
                TimeUnit.SECONDS.sleep(3);
                System.out.format("L'impiegato [%d] sta servendo il cliente [%d]", Thread.currentThread(), sportelloA.getLast());
            }else if (Thread.currentThread() == sportelloB.getLast()) {
                TimeUnit.SECONDS.sleep(5);
                System.out.format("L'impiegato [%d] sta servendo il cliente [%d]", Thread.currentThread(), sportelloB.getLast());
            }else if (Thread.currentThread() == sportelloC.getLast()) {
                TimeUnit.SECONDS.sleep(7);
                System.out.format("L'impiegato [%d] sta servendo il cliente [%d]", Thread.currentThread(), sportelloC.getLast());
            }else{

            }

        }finally{
            l.unlock();
        }
    }
    private boolean possoEseguire(){
        if( sportelloA.isEmpty() || sportelloB.isEmpty() || sportelloC.isEmpty()){
            return false;
        }
        return true;
    }


    private LinkedList<Thread> getCoda(String operazione){
        if(operazione.equals("A")){
            return sportelloA;
        }else if(operazione.equals("B")){
            return sportelloB;
        }else if(operazione.equals("C")){
            return sportelloC;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        UfficioPostaleLC ufficioPostaleLC = new UfficioPostaleLC();

        ufficioPostaleLC.Test(10);
    }
    
}
