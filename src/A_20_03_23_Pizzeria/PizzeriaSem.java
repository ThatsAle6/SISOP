package A_20_03_23_Pizzeria;

import java.util.concurrent.Semaphore;

public class PizzeriaSem extends Pizzeria{

    private Semaphore entra = new Semaphore(5, true);   //semaforo che gestisce quandi clienti possono sedersi al tavolo
    private Semaphore mangia = new Semaphore(0);    //Da la possibilità ai clienti di mangiare
    private Semaphore prepara = new Semaphore(0);   //Da il via al pizzaiolo di prepaprare la pizza poichè il tavolo è al completo
    private Semaphore servi = new Semaphore(0);     //Serve la pizza ai clienti
    private Semaphore uscita = new Semaphore(0);

    private Semaphore mutex = new Semaphore(1);
    private int clientiSeduti = 0; //tiene conto di quanti clienti si sono seduti
    private int filaEsterna = 0;
    private int[] numPersoneFila = new int[numFile];
 


    public PizzeriaSem(){
        super();
        for(int i=0; i<numFile; i++){
            numPersoneFila[i] = 0;
        }
        System.out.println(this);
    }

    public void entra() throws InterruptedException{
        entra.acquire();
        mutex.acquire();
        clientiSeduti++;
        System.out.println("Il cliente "+Thread.currentThread().getId()+" si è seduto");
        if(clientiSeduti == postiLiberi){
            numPersoneFila[filaEsterna]++;
            prepara.release();
            System.out.println("Cliente in coda.");
        }
        mutex.release();
    }

    public void mangiaPizza()throws InterruptedException{
        mangia.acquire();
        mutex.acquire();
        System.out.println("Il cliente "+Thread.currentThread().getId()+" ha mangiato");
        clientiSeduti--; //il cliente che ha mangiato libera il posto 
        uscita.release();
        if(clientiSeduti==0){
            entra.release(5);
            numPersoneFila[filaEsterna]-=5;
        }
        System.out.println("Cliente libera il posto");
        mutex.release();
    }

    public void preparaPizza()throws InterruptedException{
        prepara.acquire();
        mutex.acquire();
        if(clientiSeduti == postiLiberi){
            System.out.println("Il pizzaiolo sta preparando la pizza");
        }
        mutex.release();
        //System.out.println("Il pizzaiolo iniza a preparare la pizza");
        servi.release();
    }

    public void serviPizza()throws InterruptedException{
        servi.acquire();
        System.out.println("Il pizzaiolo serve la pizza");
        mangia.release(5);
    }

    public static void main( String[] args){
        PizzeriaSem pizzeria = new PizzeriaSem();
        pizzeria.test(10);
    }
}
