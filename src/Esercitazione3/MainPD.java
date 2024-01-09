package Esercitazione3;

public class MainPD {

    public static void main(String[] args) {
        int a[] = {1, 5, 3, 6, 1, 3, 4, 2, 56, 7, 7, 2};
        int b[] = {2, 3, 5, 1, 32, 4, 3, 4, 3, 4, 3, 21};

        if (a.length != b.length){
            throw new RuntimeException("I vettori hanno dimensioni differenti");
        }


        int n = a.length; //lunghezza array
        int m = 3; //numero thread

        ProdottoScalare p[] = new ProdottoScalare[m];
        int porzione = n/m;

        for(int i=0; i < p.length; i++){
            int inizio = i*porzione;
            int fine = inizio + porzione-1;

            p[i] = new ProdottoScalare(a, b, inizio, fine);
            p[i].start();
        }

        for(int i=0; i < p.length; i++){
            try{
                p[i].join();;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        int ps=0;
        for(int i=0; i<p.length; i++){
            ps += p[i].getProdottoScalare();
            System.out.println("Prodotto scale del thread: "+i+" = "+p[i].getProdottoScalare());
        }
        System.out.println("Prodotto scalare = "+ps);
    }
    
}
