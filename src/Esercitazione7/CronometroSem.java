package Esercitazione7;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CronometroSem extends Thread {

    private static Semaphore mutex = new Semaphore(0); //avvia il cronometro
    private static Semaphore action = new Semaphore(1); //effettua le azioni del cronometro


    public static void main(String[] args) throws InterruptedException{
        Scanner in = new Scanner(System.in);
        CronometroT cronometro = new CronometroT();
        cronometro.start();
        boolean finito = false;

        while(!finito){
            String input = in.nextLine();

            switch (input) {
                case "A":{
                    mutex.release();
                    break;
                }
                case "F":{
                    mutex.acquire();
                    break;
                }
                case "R":{
                    cronometro.reset();
                    break;
                }
                case "E":{
                    cronometro.interrupt();
                    in.close();
                    finito=true;
                    break;
                }
                default:
                    System.out.println("ERRORE!");
                    break;
            }
        }
    }

    static class CronometroT extends Thread {
        private int secondi = 1;
        
        public void reset() throws InterruptedException{
            action.acquire();
            secondi = 1;
            action.release();
        }

        public void run(){
            while (!isInterrupted()) {
                try {
                    mutex.acquire();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(secondi);
                    action.acquire();
                    secondi++;
                    mutex.release();
                    action.release();
                } catch (Exception e) {
                    break;
                }    
            }
        }
    
        
    }
    
}
