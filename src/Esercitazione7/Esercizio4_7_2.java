package Esercitazione7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4_7_2 {
            
    // seq: AA BB AA BB AA BB
    private static Semaphore semA = new Semaphore(2);
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1);

    private static int cnt = 0;

    static class A extends Thread{
        public void run(){
            try {
                semA.acquire();
                System.out.print("A");
                mutex.acquire();

                cnt++;
                if( cnt==2 ){
                    System.out.print(" ");
                    cnt = 0;
                    semB.release(2);
                }
                mutex.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    static class B extends Thread {
        public void run(){
            try {
                semB.acquire();
                System.out.print("B");
                mutex.acquire();

                cnt++;
                if( cnt==2){
                    System.out.print(" ");
                    cnt = 0;
                    semA.release(2);
                }
                mutex.release();

 
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
