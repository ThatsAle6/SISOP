package Esercitazione7;

//import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class StructSeq {
    
    
    //private static Semaphore semA = new Semaphore(1);
    //private static Semaphore semB = new Semaphore(1);

    static class A extends Thread{
        public void run(){
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class B extends Thread {
        public void run(){
            try {
 
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
