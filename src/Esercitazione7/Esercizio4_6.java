package Esercitazione7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4_6 {
    
    // seq: AAB AAB AAB
    private static Semaphore semA = new Semaphore(2);
    private static Semaphore semB = new Semaphore(0);

    static class A extends Thread{
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

    static class B extends Thread {
        public void run(){
            try {
                semB.acquire(2);
                System.out.print("B\n");
                semA.release(2);
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
