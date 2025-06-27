package Model;

public class Bank {

    private String nmBanco;
    private int agencia;

    public Bank(String nmBanco, int agencia) {
        this.nmBanco = nmBanco;
        this.agencia = agencia;
    }

    public Bank()
    {

    }

    public String getNmBanco() {
        return nmBanco;
    }

    public void setNmBanco(String nmBanco) {
        this.nmBanco = nmBanco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }
}
