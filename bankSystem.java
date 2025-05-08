import java.util.ArrayList;
import java.util.Scanner;

public class bankSystem {
    public static ArrayList<Account> contas = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int op = 0;

        while (op != 9) {
            Menu();
            op = scan.nextInt();
            opMenu(op);
        }
        scan.close();
    }

    public static void Menu() {
        System.out.println("1 - criar conta");
        System.out.println("2 - alterar conta");
        System.out.println("3 - listar todas as contas");
        System.out.println("4 - sacar dinheiro");
        System.out.println("5 - depositar dinheiro");
        System.out.println("6 - transferir dinheiro");
        System.out.println("7 - verificar saldo");
        System.out.println("8 - verificar movimentação");
        System.out.println("9 - sair");
    }

    public static void opMenu(int op) {
        switch (op) {
            case 1:
                System.out.println("Criar Conta");
                criaConta();
                break;
            case 2:
                System.out.println("Alterar Conta");
                // alteraConta();
                break;
            case 3:
                System.out.println("Lista de Contas");
                // ListarContas();
                break;
            case 4:
                System.out.println("Saque");
                // Saque();
                break;
            case 5:
                System.out.println("depósito");
                // Deposito();
                break;
            case 6:
                System.out.println("transferência");
                // Transferir();
                break;
            case 7:
                System.out.println("ver saldo");
                // verSaldo();
                break;
            case 8:
                System.out.println("ver movimentação");
                // verMovimentacao();
                break;
            default:
                break;
        }
    }

    public static void criaConta() {
        Account c = new Account();
        System.out.println("digite o nome do usuário:");
        c.setNome(scanString());
        while(!verifyPassword(c.getSenha()))
        {
            System.out.println("digite a senha da sua conta: ");// fazer o verifica senha
            c.setSenha(scanString());
        }
        

        c.setSaldo(0.0);

        System.out.println("digite o nome do banco: ");
        c.setNmBanco(scanString());

        System.out.println("digite a Agencia:");
        c.setAgencia(scanInt());

        System.out.println("digite o numero da sua conta:");
        c.setNumConta(scanString());

        System.out.println("digite o seu cpf");
        c.setCpf(scanString());

        System.out.println("digite o seu endereço");// if
        c.setEndereco(scanString());

        System.out.println("digite o numero de recuperação");// if
        c.setNumRec(scanString());

        contas.add(c);
    }

    public static String scanString() {

        String str;
        str = scan.next();

        return str;
    }

    public static int scanInt() {
        int num;
        num = scan.nextInt();
        return num;
    }

    public static double scanDouble() {
        double num;
        num = scan.nextDouble();
        return num;
    }

    public static boolean verifyPassword(String str) {
        if(str/*tamanho < 6*/) {
            System.out.println("digite uma senha com mais de 6 letras");
            return false;
        }
        if (str /*não tiver pelo menos uma letra*/) {
            System.out.println("digite no minimo uma letra");
            return false;
        }
        if (str /*não tiver pelo menos um numero  */) {
            System.out.println("digite pelo menos um numero");
            return false;
        }
        return true;

    }
}