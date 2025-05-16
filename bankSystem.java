import java.util.ArrayList;
import java.util.Scanner;

public class bankSystem {
    public static ArrayList<Account> contas = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);
    public static Account contaAtiva = null;

    public static void main(String[] args) {
        int op = 0;

        while (op != 9) {
            Util.clear();
            Menu();
            op = scan.nextInt();
            opMenu(op);

        }
        scan.close();
    }

    public static void Menu() {
        if (contaAtiva != null) {
            System.out.println("Conta ativa: " + contaAtiva.getNome() + " - " + contaAtiva.getNumConta());
        } else {
            System.out.println("Nenhuma conta ativa no momento.");
        }

        System.out.println("1 - Criar conta");
        System.out.println("2 - Alterar conta");
        System.out.println("3 - Listar todas as contas");
        System.out.println("4 - Sacar dinheiro");
        System.out.println("5 - Depositar dinheiro");
        System.out.println("6 - Transferir dinheiro");
        System.out.println("7 - Verificar saldo");
        System.out.println("8 - Verificar movimentação");
        System.out.println("9 - Sair");
        System.out.println("10 - Logout");  // Nova opção de logout
    }

    public static void opMenu(int op) {
        Util.clear();
        switch (op) {
            case 1:
                System.out.println("Criar Conta");
                criaConta();
                break;
            case 2:
                System.out.println("Trocar de Conta");
                trocaConta();
                break;
            case 3:
                System.out.println("Lista de Contas");
                listarContas();
                break;
            case 4:
                System.out.println("Saque");
                Saque();
                break;
            case 5:
                System.out.println("Depósito");
                deposito();
                break;
            case 6:
                System.out.println("Transferência");
                transferir();
                break;
            case 7:
                System.out.println("Ver saldo");
                verSaldo();
                break;
            case 8:
                System.out.println("Ver movimentação");
                verMovimentacao();
                break;
            case 10:
                System.out.println("Logout");
                logout();
                break;
            default:
                break;
        }
    }

    public static void logout() {
        if (contaAtiva != null) {
            System.out.println("Você foi desconectado da conta de " + contaAtiva.getNome());
            contaAtiva = null;
        } else {
            System.out.println("Nenhuma conta ativa no momento.");
        }
        Util.pause();
    }

    public static void criaConta() {
        Account c = new Account();
        char op = 'g';

        System.out.println("Digite o nome do usuário:");
        c.setNome(scanString());

        do {
            System.out.println("Digite a senha da sua conta: ");
            c.setSenha(scanString());
        } while (!verifyPassword(c.getSenha()));

        c.setSaldo(0.0);

        System.out.println("Digite o nome do banco: ");
        c.setNmBanco(scanString());

        System.out.println("Digite a Agência:");
        c.setAgencia(scanInt());

        System.out.println("Digite o número da sua conta:");
        c.setNumConta(scanString());

        System.out.println("Digite o seu CPF:");
        c.setCpf(scanString());

        op = 'g';
        while (op != 's' && op != 'n') {
            System.out.println("Você quer adicionar um endereço? (s/n)");
            op = scanChar();
        }
        if (op == 's') {
            System.out.println("Digite o seu endereço:");
            c.setEndereco(scanString());
        }

        op = 'g';
        while (op != 's' && op != 'n') {
            System.out.println("Você quer adicionar um número de recuperação? (s/n)");
            op = scanChar();
        }
        if (op == 's') {
            System.out.println("Digite o número de recuperação:");
            c.setNumRec(scanString());
        }

        contas.add(c);

        contaAtiva = c;

        System.out.println("Conta criada com sucesso!");
        System.out.println("Conta ativa: " + contaAtiva.getNome() + " - " + contaAtiva.getNumConta());

        Util.pause();
    }

    public static boolean verifyPassword(String str) {
        if (str.length() < 6) {
            System.out.println("digite uma senha com mais de 6 letras");
            return false;
        }
        boolean temLetra = false;
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                temLetra = true;
                break;
            }
        }
        if (!temLetra) {
            System.out.println("digite no minimo uma letra");
            return false;
        }
        if (!str.matches(".*\\d.*")) {
            System.out.println("digite pelo menos um numero");
            return false;
        }
        if (!str.matches(".*[^a-zA-Z0-9].*")) {
            System.out.println("digite pelo menos 1 caracter especial");
            return false;
        }
        return true;
    }

    public static void trocaConta() {
        if (contas.size() < 1) {
            System.out.println("Não há contas cadastradas.");
            Util.pause();
            return;
        }

        System.out.println("Lista de Contas:");
        for (int i = 0; i < contas.size(); i++) {
            System.out.println((i + 1) + " - " + contas.get(i).getNome() + " - " + contas.get(i).getNumConta());
        }

        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Digite o número da conta que você deseja acessar: ");

        int escolha = scanInt();

        if (escolha == 0) {
            return;
        }

        if (escolha < 1 || escolha > contas.size()) {
            System.out.println("Escolha inválida, por favor tente novamente.");
            Util.pause();
            return;
        }

        String senha;
        while (true) {
            System.out.println("Olá " + contas.get(escolha - 1).getNome() + ", digite a sua senha:");
            senha = scanString();

            if (senha.equals(contas.get(escolha - 1).getSenha())) {
                break;
            } else {
                System.out.println("Senha inválida. Por favor, digite a senha corretamente.");
            }
        }

        contaAtiva = contas.get(escolha - 1);

        System.out.println("Você selecionou a conta de " + contaAtiva.getNome() + " com o número da conta "
                + contaAtiva.getNumConta());
        Util.pause();
    }

    public static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Não há contas cadastradas.");
            Util.pause();
            return;
        }

        System.out.println("Lista de Contas (sem dados restritos):");
        for (int i = 0; i < contas.size(); i++) {
            Account conta = contas.get(i);

            System.out.println((i + 1) + " - Titular: " + conta.getNome() + " | Número da Conta: " + conta.getNumConta()
                    + " | Banco: " + conta.getNmBanco());
        }

        Util.pause();
    }

    public static void Saque() {

        if (contaAtiva == null) {
            System.out.println("Não há conta ativa. Por favor, selecione uma conta.");
            Util.pause();
            return;
        }

        System.out.println("Conta ativa: " + contaAtiva.getNome() + " - Número da conta: " + contaAtiva.getNumConta());

        String senha;
        System.out.print("Digite a sua senha para confirmar o saque: ");
        senha = scanString();

        if (!senha.equals(contaAtiva.getSenha())) {
            System.out.println("Senha incorreta. O saque não pode ser realizado.");
            Util.pause();
            return;
        }

        System.out.print("Digite o valor do saque: ");
        double valorSaque = scanDouble();

        if (valorSaque <= 0) {
            System.out.println("O valor do saque deve ser maior que zero.");
            Util.pause();
            return;
        }

        if (valorSaque > contaAtiva.getSaldo()) {
            System.out.println("Saldo insuficiente para o saque.");
            Util.pause();
            return;
        }

        contaAtiva.setSaldo(contaAtiva.getSaldo() - valorSaque);
        contaAtiva.adicionarMovimentacao("Saque de R$" + valorSaque);

        System.out.println("Saque de R$" + valorSaque + " realizado com sucesso.");
        System.out.println("Novo saldo: R$" + contaAtiva.getSaldo());

        Util.pause();
    }

    public static void deposito() {

        if (contaAtiva == null) {
            System.out.println("Não há conta ativa. Por favor, selecione uma conta.");
            Util.pause();
            return;
        }

        System.out.println("Conta ativa: " + contaAtiva.getNome() + " - Número da conta: " + contaAtiva.getNumConta());

        String senha;
        System.out.print("Digite a sua senha para confirmar o depósito: ");
        senha = scanString();

        if (!senha.equals(contaAtiva.getSenha())) {
            System.out.println("Senha incorreta. O depósito não pode ser realizado.");
            Util.pause();
            return;
        }

        System.out.print("Digite o valor do depósito: ");
        double valorDeposito = scanDouble();

        if (valorDeposito <= 0) {
            System.out.println("O valor do depósito deve ser maior que zero.");
            Util.pause();
            return;
        }

        contaAtiva.setSaldo(contaAtiva.getSaldo() + valorDeposito);
        contaAtiva.adicionarMovimentacao("Depósito de R$" + valorDeposito);
        System.out.println("Depósito de R$" + valorDeposito + " realizado com sucesso.");
        System.out.println("Novo saldo: R$" + contaAtiva.getSaldo());

        Util.pause();
    }

    public static void transferir() {

        if (contaAtiva == null) {
            System.out.println("Não há conta ativa. Por favor, selecione uma conta.");
            Util.pause();
            return;
        }

        System.out.println("Conta ativa: " + contaAtiva.getNome() + " - Número da conta: " + contaAtiva.getNumConta());

        String senha;
        System.out.print("Digite a sua senha para confirmar a transferência: ");
        senha = scanString();

        if (!senha.equals(contaAtiva.getSenha())) {
            System.out.println("Senha incorreta. A transferência não pode ser realizada.");
            Util.pause();
            return;
        }

        System.out.println("\nContas disponíveis para transferência:");
        int index = 1;
        for (Account c : contas) {
            if (!c.equals(contaAtiva)) {
                System.out.println(index + " - " + c.getNome() + " - Número da conta: " + c.getNumConta());
                index++;
            }
        }

        System.out.print("Digite o número da conta destino: ");
        String numContaDestino = scanString();

        Account contaDestino = null;
        for (Account c : contas) {
            if (c.getNumConta().equals(numContaDestino)) {
                contaDestino = c;
                break;
            }
        }

        if (contaDestino == null) {
            System.out.println("Conta destino não encontrada. A transferência não pode ser realizada.");
            Util.pause();
            return;
        }

        System.out.print("Digite o valor da transferência: ");
        double valorTransferencia = scanDouble();

        if (valorTransferencia <= 0) {
            System.out.println("O valor da transferência deve ser maior que zero.");
            Util.pause();
            return;
        }

        if (valorTransferencia > contaAtiva.getSaldo()) {
            System.out.println("Saldo insuficiente para realizar a transferência.");
            Util.pause();
            return;
        }

        contaAtiva.setSaldo(contaAtiva.getSaldo() - valorTransferencia);
        contaDestino.setSaldo(contaDestino.getSaldo() + valorTransferencia);

        contaAtiva.adicionarMovimentacao(
                "Transferência de R$" + valorTransferencia + " para conta " + contaDestino.getNumConta());
        contaDestino.adicionarMovimentacao(
                "Recebimento de R$" + valorTransferencia + " da conta " + contaAtiva.getNumConta());

        System.out.println("Transferência de R$" + valorTransferencia + " realizada com sucesso.");
        System.out.println("Novo saldo da conta ativa: R$" + contaAtiva.getSaldo());
        System.out.println("Novo saldo da conta destino: R$" + contaDestino.getSaldo());

        Util.pause();
    }

    public static void verSaldo() {

        if (contaAtiva == null) {
            System.out.println("Nenhuma conta ativa selecionada. Selecione uma conta antes de verificar o saldo.");
            Util.pause();
            return;
        }

        System.out.println("Conta ativa: " + contaAtiva.getNome() + " - Número da conta: " + contaAtiva.getNumConta());

        System.out.print("Digite a sua senha para ver o saldo: ");
        String senha = scanString();

        if (!senha.equals(contaAtiva.getSenha())) {
            System.out.println("Senha incorreta. Não foi possível acessar o saldo.");
            Util.pause();
            return;
        }

        System.out.printf("Saldo atual da conta: R$ %.2f\n", contaAtiva.getSaldo());
        Util.pause();
    }

    public static void verMovimentacao() {
    if (contaAtiva == null) {
        System.out.println("Nenhuma conta ativa selecionada. Selecione uma conta antes de verificar a movimentação.");
        Util.pause();
        return;
    }

    System.out.println("Conta ativa: " + contaAtiva.getNome() + " - Número da conta: " + contaAtiva.getNumConta());

    System.out.print("Digite sua senha para ver as movimentações: ");
    String senha = scanString();

    if (!senha.equals(contaAtiva.getSenha())) {
        System.out.println("Senha incorreta. A movimentação não pode ser exibida.");
        Util.pause();
        return;
    }

    ArrayList<String> movimentacoes = contaAtiva.getMovimentacoes();

    if (movimentacoes.isEmpty()) {
        System.out.println("Nenhuma movimentação registrada.");
    } else {
        System.out.println("Movimentações da conta:");
        for (String m : movimentacoes) {
            System.out.println("- " + m);
        }
    }

    Util.pause();
}

    public static String scanString() {

        String str;
        str = scan.next();

        return str;
    }

    public static char scanChar() {
        char c;
        c = scan.next().charAt(0);
        return c;
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

}