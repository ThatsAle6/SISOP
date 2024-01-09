package Esercitazione6;

public abstract class ContoCorrente {
    
    protected int deposito;

    public ContoCorrente(int depositoIniziale){
        this.deposito = depositoIniziale;
    }

    public abstract void deposita(int i);

    public abstract void preleva(int i);

    public int getDeposito(){
        return deposito;
    }
}
