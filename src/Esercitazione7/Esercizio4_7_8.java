package Esercitazione7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4_7_8 {
    
    //seq = AAAAB AAAB AAB AB
    private static int N = 4;
    private static Semaphore semA = new Semaphore(N);
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
                if(cnt == N){
                    cnt=0;
                    semB.release();
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
                System.out.print("B ");
                mutex.acquire();
                N-=1;
                mutex.release();
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
