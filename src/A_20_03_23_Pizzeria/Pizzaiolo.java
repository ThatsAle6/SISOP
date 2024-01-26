package A_20_03_23_Pizzeria;

import java.util.concurrent.TimeUnit;

public class Pizzaiolo extends Thread {
    
    public Pizzeria pizzeria;
    
    public Pizzaiolo(Pizzeria p){
        pizzeria = p;
    }

    public void run(){
        try{
            while (true) {
                pizzeria.preparaPizza();
                //System.out.format("Il pizzaiolo %d sta preparando la pizza",Thread.currentThread().getId());
                TimeUnit.SECONDS.sleep(10);
                //System.out.println("La pizza è servita");
                pizzeria.serviPizza();
            }
			
		}catch(InterruptedException e){
		}
    }
}
