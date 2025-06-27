package Model;

import java.util.ArrayList;

public class Account {

    private String senha;
    private double saldo;
    private Bank bank;
    private String numConta;
    private String numRec;
    private final ArrayList<String> movimentacoes = new ArrayList<>();

    public Account() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getNumRec() {
        return numRec;
    }

    public void setNumRec(String numRec) {
        this.numRec = numRec;
    }

    public ArrayList<String> getMovimentacoes() {
        return movimentacoes;
    }

    public void adicionarMovimentacao(String descricao) {
        movimentacoes.add(descricao);
    }
}