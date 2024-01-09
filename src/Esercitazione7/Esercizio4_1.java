package Esercitazione7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4_1 extends Thread{

    // seq: AB AB AB
    private static Semaphore semA = new Semaphore(1);
    private static Semaphore semB = new Semaphore(0);

    static class A extends Thread {

        public void run(){
            try {
                semA.acquire();
                System.out.print("A");
                semB.release();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    static class B extends Thread{
        
        public void run(){
            try {
                semB.acquire();
                System.out.print("B ");
                semA.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        
        while (true) {
            new A().start();
            new B().start();

            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
