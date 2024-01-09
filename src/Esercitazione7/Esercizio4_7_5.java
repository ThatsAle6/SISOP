package Esercitazione7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4_7_5 {

    //seq = ABA ABA ABA ABA
    private static int N = 1;
    private static Semaphore semA = new Semaphore(N);
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1);

    private static int cnt = 0;

    static class A extends Thread{
        public void run(){
            try {
                semA.acquire(1);
                System.out.print("A");
                mutex.acquire();
                cnt++;
                if(cnt == N){
                    semB.release();
                }else if (cnt == 2) {
                    cnt = 0;
                    System.out.print(" ");
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
