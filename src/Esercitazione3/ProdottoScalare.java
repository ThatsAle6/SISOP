package Esercitazione3;

public class ProdottoScalare extends Thread {
    
    private int a [];
    private int b [];

    private int inizio,fine;
    private int ProdottoScalare = 0;



    public ProdottoScalare( int a[], int b[], int inizio, int fine ){
        this.a = a;
        this.b = b;
        this.inizio = inizio;
        this.fine = fine;
    }

    public void run(){
        for(int i=inizio; i <= fine; i++){
            ProdottoScalare += a[i] * b[i];
        }
    }

    public int getProdottoScalare(){
        try{
            this.join();
        }catch( InterruptedException e){
            e.printStackTrace();
        }
        return ProdottoScalare;
    }
}
