package Esercitazione7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4_7_3 {

    // abbb abb ab
    //seq = AB AAB AAAB AAAAB
    private static int N = 1;
    private static Semaphore semA = new Semaphore(N);
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore mutexA = new Semaphore(1);
    private static Semaphore mutexB = new Semaphore(1);

    private static int cnt = 0;

    static class A extends Thread {
        public void run(){
            try {
                semA.acquire();
                System.out.print("A");
                mutexA.acquire();
                cnt++;
                if(cnt == N){
                    cnt = 0;
                    semB.release();
                }
                mutexA.release();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }   
    }

    static class B extends Thread {
        public void run(){
            try {
                semB.acquire();
                System.out.print("B ");
                mutexB.acquire();
                N++;
                mutexB.release();
                semA.release(N);

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
