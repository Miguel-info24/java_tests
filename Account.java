import java.util.ArrayList;

public class Account {

    private String nome;
    private String senha;
    private double saldo;
    private String nmBanco;
    private int agencia;
    private String numConta;
    private String cpf;
    private String endereco;
    private String numRec;
    private ArrayList<String> movimentacoes = new ArrayList<>();

    public Account(String nome, String senha, double saldo, String nmBanco, int agencia, String numConta, String cpf,
            String endereco, String numRec) {
        this.nome = nome;
        this.senha = senha;
        this.saldo = saldo;
        this.nmBanco = nmBanco;
        this.agencia = agencia;
        this.numConta = numConta;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numRec = numRec;
    }

    public Account(String nome, String senha, double saldo, String nmBanco, int agencia, String numConta, String cpf,
            String numRec) {
        this.nome = nome;
        this.senha = senha;
        this.saldo = saldo;
        this.nmBanco = nmBanco;
        this.agencia = agencia;
        this.numConta = numConta;
        this.cpf = cpf;
        this.numRec = numRec;
    }

    public Account(String nome, String senha, String nmBanco, int agencia, String numConta, String cpf,
            String endereco, String numRec) {
        this.nome = nome;
        this.senha = senha;
        this.nmBanco = nmBanco;
        this.agencia = agencia;
        this.numConta = numConta;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numRec = numRec;
    }

    public Account(String nome, String senha, String nmBanco, int agencia, String numConta, String cpf,
            String numRec) {
        this.nome = nome;
        this.senha = senha;
        this.nmBanco = nmBanco;
        this.agencia = agencia;
        this.numConta = numConta;
        this.cpf = cpf;
        this.numRec = numRec;
    }

    public Account() {
    }

    public void setNome(String newNome) {
        this.nome = newNome;
    }

    public void setSenha(String newSenha) {
        this.senha = newSenha;
    }

    public void setSaldo(double newSaldo) {
        this.saldo = newSaldo;
    }

    public void setNmBanco(String newNmBanco) {
        this.nmBanco = newNmBanco;
    }

    public void setAgencia(int newAgencia) {
        this.agencia = newAgencia;
    }

    public void setNumConta(String newNumConta) {
        this.numConta = newNumConta;
    }

    public void setCpf(String newCpf) {
        this.cpf = newCpf;
    }

    public void setEndereco(String newEndereco) {
        this.endereco = newEndereco;
    }

    public void setNumRec(String newNumRec) {
        this.numRec = newNumRec;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNmBanco() {
        return nmBanco;
    }

    public int getAgencia() {
        return agencia;
    }

    public String getNumConta() {
        return numConta;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumRec() {
        return numRec;
    }

    // Novos métodos
    public ArrayList<String> getMovimentacoes() {
        return movimentacoes;
    }

    public void adicionarMovimentacao(String descricao) {
        movimentacoes.add(descricao);
    }
}
