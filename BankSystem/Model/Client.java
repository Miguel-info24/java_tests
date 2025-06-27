package Model;

public class Client {
    private String nome;
    private String cpf;
    private Account account;
    private Address address;

    public Client(String nome, String cpf, Account account, Address address) {
        this.nome = nome;
        this.cpf = cpf;
        this.account = account;
        this.address = address;
    }

    public Client() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
