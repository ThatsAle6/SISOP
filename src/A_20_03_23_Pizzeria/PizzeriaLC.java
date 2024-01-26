package A_20_03_23_Pizzeria;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PizzeriaLC extends Pizzeria {

    private Lock l = new ReentrantLock();
    private Condition entra = l.newCondition();
    private Condition mangia = l.newCondition();
    private Condition prepara = l.newCondition();
    private Condition servi = l.newCondition();
    private Condition uscita = l.newCondition();

    private int filaEsterna = 0;
    private int clientiSeduti = 0;
    private int[] numPersoneFila = new int[numFile];

    private boolean pizzaPronta = false;

    public PizzeriaLC(){
        super();
        for(int i=0; i<numFile; i++){
            numPersoneFila[i] = 0;
        }
    }

    public void entra() throws InterruptedException{
        l.lock();
        try{
            while (!possoEntrare()) {
                entra.await();
                numPersoneFila[filaEsterna]++;
                System.out.println("Il cliente si mette in coda");
            }
            clientiSeduti++;
            System.out.format("Il cliente %d si siede al tavolo %n",Thread.currentThread().getId());
            prepara.signal();
        }finally{
            l.unlock();
        }
    }
    private boolean possoEntrare(){
        if(clientiSeduti < 5){
            return true;
        }
        return false;
    }

    public void mangiaPizza() throws InterruptedException{
        l.lock();
        try{
            while(!pizzaPronta){
                mangia.await();
            }
            clientiSeduti--;
            uscita.signal();
            System.out.format("Il cliente %d libera il posto %n", Thread.currentThread().getId());
            numPersoneFila[filaEsterna]--;
            entra.signal();
        }finally{
            l.unlock();
        }
    }

    public void preparaPizza() throws InterruptedException{
        l.lock();
        try{
            while (!possoPreparare()) {
                prepara.await();                
            }
            System.out.println("Il pizzaiolo sta preparando la pizza. . . .");
            pizzaPronta = true;
            
        }finally{
            l.unlock();
        }
    }
    private boolean possoPreparare(){
        return clientiSeduti == postiLiberi;
    }

    public void serviPizza() throws InterruptedException{
        l.lock();
        try{
            while (!possoServire()) {
                servi.await();
            }
            mangia.signal();
            System.out.println("Il pizzaiolo serve la pizza.");
        }finally{
            l.unlock();
        }
    }
    private boolean possoServire(){
        if(pizzaPronta){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        PizzeriaLC pizzeria = new PizzeriaLC();
        pizzeria.test(10);

    }
    
}
