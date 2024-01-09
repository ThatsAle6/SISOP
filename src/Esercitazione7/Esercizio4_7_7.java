package Esercitazione7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4_7_7 {
    
    //seq = ABABC ABABC ABABC ...
    private static int N = 1;
    private static Semaphore semA = new Semaphore(N);
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore semC = new Semaphore(0);
    private static Semaphore mutexA = new Semaphore(1);
    private static Semaphore mutexB = new Semaphore(1);

    private static int cntA = 0;
    private static int cntB = 0;

    static class A extends Thread{
        public void run(){
            try {
                semA.acquire();
                System.out.print("A");

                mutexA.acquire();
                cntA++;
                if(cntA == N){
                    semB.release();
                }else if (cntA == 2) {
                    cntA = 0;
                    semB.release();
                }
                mutexA.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class B extends Thread{
        public void run(){
            try {
                semB.acquire();
                System.out.print("B");

                mutexB.acquire();
                cntB++;
                if(cntB == N){
                    semA.release();
                }else if (cntB == 2) {
                    cntB = 0;
                    semC.release();
                }
                mutexB.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    static class C extends Thread {
        public void run(){
            try {
                semC.acquire();
                System.out.print("C ");
                semA.release();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    public static void main(String[] args) {
        while(true){
            new A().start();
            new B().start();
            new C().start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
