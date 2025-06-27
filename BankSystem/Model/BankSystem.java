package Model;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class BankSystem {
    public static ArrayList<Client> clients = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);
    public static Client activeClient = null;
    public static String a = "=================================";
    public static Random rand = new Random();

    public static Util Util = new Util();

    public static void main(String[] args) {
        int op = 0;
        carregaArquivo();
        while (op != 12) {
            Model.Util.clear();
            printMenuHomossexual();
            Menu();
            System.out.println(a);
            op = scan.nextInt();
            scan.nextLine();

            opMenu(op);
        }
        guardaArquivo();
        scan.close();
    }

    public static void Menu() {
        if (activeClient != null) {
            System.out.println(
                    "Conta ativa: " + activeClient.getNome() + " - " + activeClient.getAccount().getNumConta());
        } else {
            System.out.println("Nenhuma conta ativa no momento.");
        }

        System.out.println("(1) - Criar conta");
        System.out.println("(2) - excluir conta");
        System.out.println("(3) - Trocar conta");
        System.out.println("(4) - Listar todas as contas");
        System.out.println("(5) - Sacar dinheiro");
        System.out.println("(6) - Depositar dinheiro");
        System.out.println("(7) - Transferir dinheiro");
        System.out.println("(8) - Verificar saldo");
        System.out.println("(9) - Verificar movimentacao");
        System.out.println("(10) - Recuperar conta");
        System.out.println("(11) - Logout");
        System.out.println("(12) - Sair");
    }

    public static void opMenu(int op) {
        Model.Util.clear();
        printMenuHomossexual();
        switch (op) {
            case 1:
                System.out.println("Criar Conta");
                criaConta();
                break;

            case 2:
                System.out.println("excluir conta");
                deleteAccount();
                break;
            case 3:

                System.out.println("Trocar de Conta");
                trocaConta();
                break;
            case 4:
                System.out.println("Lista de Contas");
                listarContas();
                break;
            case 5:
                System.out.println("Saque");
                Saque();
                break;
            case 6:
                System.out.println("Deposito");
                deposito();
                break;
            case 7:
                System.out.println("Transferencia");
                transferir();
                break;
            case 8:
                System.out.println("Ver saldo");
                verSaldo();
                break;
            case 9:
                System.out.println("Ver movimentacao");
                verMovimentacao();
                break;
            case 10:
                System.out.println("Recuperar Conta");
                recuperarConta();
                break;
            case 11:
                System.out.println("Logout");
                logout();
                break;
            default:
                break;
        }
    }

    public static void logout() {

        System.out.println("Voce realmente deseja sair da sua conta? (s/n)");

        if (scanChar() == 's') {
            if (activeClient != null) {
                System.out.println("Voce foi desconectado da conta de " + activeClient.getNome());
                activeClient = null;
            } else {
                System.out.println("Nenhuma conta ativa no momento.");
            }
        } else {
            System.out.println("Voce cancelou o logout.");
        }
        System.out.println(a);
        Model.Util.pause();
    }

    public static void criaConta() {
        Client client = new Client();
        Account account = new Account();
        Address address = new Address();
        char op = 'g';

        System.out.println("Digite o nome do usuario:");
        client.setNome(scanString());

        do {
            System.out.println("Digite a senha da sua conta: ");
            account.setSenha(scanString());
        } while (!verifyPassword(account.getSenha()));

        account.setSaldo(0.0);

        System.out.println("Digite o nome do banco: ");
        Bank bank = new Bank();
        bank.setNmBanco(scanString());

        System.out.println("Digite a Agencia:");
        bank.setAgencia(scanInt());

        account.setBank(bank);

        int num;
        String str;
        num = rand.nextInt();
        str = Integer.toString(num);
        account.setNumConta(str);

        do {
            System.out.println("Digite o seu CPF:");
            client.setCpf(scanString());
        } while (!verifyCpf(client.getCpf()));

        while (op != 's' && op != 'n') {
            System.out.println("Voce quer adicionar um endereco? \n(s/n)");
            op = scanChar();
            scan.nextLine();
        }
        if (op == 's') {
            System.out.println("Digite a rua do seu endereco: ");
            address.setStreet(scanString());
            System.out.println("Digite o numero do seu endereco:");
            address.setNumber(scanString());

            client.setAddress(address);
        }

        op = 'g';

        while (op != 's' && op != 'n') {
            System.out.println("Voce quer adicionar um numero de recuperacao? (s/n)");
            op = scanChar();
            scan.nextLine();
        }
        if (op == 's') {
            System.out.println("Digite o numero de recuperacao:");
            account.setNumRec(scanString());
        }
        client.setAccount(account);
        client.setAddress(address);
        clients.add(client);

        activeClient = client;

        System.out.println("Conta criada com sucesso!");
        System.out.println("Conta ativa: " + activeClient.getNome() + " - " + activeClient.getAccount().getNumConta());
        System.out.println(a);
        Model.Util.pause();
    }

    public static boolean verifyPassword(String str) {
        if (str.length() < 6) {
            System.out.println("Erro");
            System.out.println("Digite uma senha com mais de 6 caracteres");
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
            System.out.println("Erro");
            System.out.println("Digite no minimo uma letra");
            return false;
        }
        if (!str.matches(".*\\d.*")) {
            System.out.println("Erro");
            System.out.println("Digite pelo menos um numero");
            return false;
        }
        if (!str.matches(".*[^a-zA-Z0-9].*")) {
            System.out.println("Erro");
            System.out.println("Digite pelo menos 1 caractere especial");
            return false;
        }
        return true;
    }

    public static boolean verifyCpf(String str) {
        if (str.length() != 11) {
            System.out.println("Erro");
            System.out.println("Digite um CPF com exatamente 11 números.");
            return false;
        }

        if (!str.matches("\\d+")) {
            System.out.println("Erro");
            System.out.println("Não digite caracteres que não sejam números.");
            return false;
        }

        for (Client client : clients) {
            if (str.equals(client.getCpf())) {
                System.out.println("Erro!!");
                System.out.println("Esse CPF já está em uso.");
                return false;
            }
        }

        System.out.println("CPF válido e único.");
        return true;
    }

    public static void recuperarConta() {
        for (Client client : clients) {
            if (client != null) {
                if (!client.getCpf().equals(client.getCpf())) {
                    System.out.println("Cpf:" + client.getCpf());

                }
            }
        }
        System.out.println("Digite o CPF da conta que deseja recuperar:");
        String cpf = scanString();

        Account contaRecuperada = null;
        for (Client client : clients) {
            if (client.getCpf().equals(cpf)) {
                contaRecuperada = client.getAccount();
                break;
            }
        }

        if (contaRecuperada == null) {
            System.out.println("Nenhuma conta encontrada com esse CPF.");
            Model.Util.pause();
            return;
        }

        if (contaRecuperada.getNumRec() == null || contaRecuperada.getNumRec().isEmpty()) {
            System.out.println("Essa conta nao possui numero de recuperacao cadastrado. Impossivel recuperar.");
            Model.Util.pause();
            return;
        }

        System.out.println("Digite o numero de recuperacao:");
        String numRec = scanString();

        if (!contaRecuperada.getNumRec().equals(numRec)) {
            System.out.println("Numero de recuperacao incorreto.");
            Model.Util.pause();
            return;
        }

        String novaSenha;
        do {
            System.out.println("Digite a nova senha para a conta:");
            novaSenha = scanString();
        } while (!verifyPassword(novaSenha));

        contaRecuperada.setSenha(novaSenha);
        activeClient.setAccount(contaRecuperada);

        System.out.println("Senha redefinida com sucesso!");
        System.out.println("Voce esta logado como " + activeClient.getNome());
        System.out.println(a);
        Model.Util.pause();
    }

    public static void deleteAccount() {
        String str = "sla";
        int index = 0;
        String senha;
        for (index = 0; index < clients.size(); index++) {
            Client client = clients.get(index);
            System.out.println(index + " - " + client.getNome());
        }
        System.out.println("digite o nome ou o indice da conta que voce deseja excluir.");
        str = scanString();
        scan.nextLine();

        if (isDigit(str)) {
            index = Integer.parseInt(str);
            if (index < 0 || index > clients.size()) {
                System.out.println("Erro!! indice invalido.");
                return;
            } else {
                System.out.println("digite a senha da sua conta para confirmar a exclusão");
                senha = scanString();
                if (senha.equals(clients.get(index).getAccount().getSenha())) {
                    clients.remove(index);
                    System.out.println("cliente excluido com sucesso.");
                } else {
                    System.out.println("Erro!! senha invalida.");
                }

            }
        } else {
            for (index = 0; index < clients.size(); index++) {
                Client client = clients.get(index);
                if (str.equals(client.getNome())) {
                    System.out.println("digite a senha da sua conta para confirmar a exclusão");
                    senha = scanString();
                    if (senha.equals(clients.get(index).getAccount().getSenha())) {
                        clients.remove(index);
                        System.out.println("cliente excluido com sucesso.");
                    } else {
                        System.out.println("Erro!! senha invalida.");
                    }
                }
            }
        }
        Model.Util.pause();
        return;
    }

    public static boolean isDigit(String str) {

        return str != null && str.matches("\\d+");
    }

    public static void trocaConta() {
        if (clients.size() < 1) {
            System.out.println("Nao ha contas cadastradas.");
            Model.Util.pause();
            return;
        }

        System.out.println("Lista de Contas:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(
                    (i + 1) + " - " + clients.get(i).getNome() + " - " + clients.get(i).getAccount().getNumConta());
        }

        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Digite o numero da conta que voce deseja acessar: ");

        int escolha = scanInt();

        if (escolha == 0) {
            return;
        }

        if (escolha < 1 || escolha > clients.size()) {
            System.out.println("Escolha invalida, por favor tente novamente.");
            Model.Util.pause();
            return;
        }

        String senha;
        do {
            System.out.println("Ola " + clients.get(escolha - 1).getNome() + ", digite a sua senha:");
            senha = scanString();

            if (senha.equals(clients.get(escolha - 1).getAccount().getSenha())) {
                break;
            } else {
                System.out.println("Senha invalida. Por favor, digite a senha corretamente.");
            }
        } while (!senha.equals(clients.get(escolha - 1).getAccount().getSenha()));

        activeClient = clients.get(escolha - 1);

        System.out.println("Voce selecionou a conta de " + activeClient.getNome() + " com o numero da conta "
                + activeClient.getAccount().getNumConta());
        System.out.println(a);
        Model.Util.pause();
    }

    public static void listarContas() {
        if (clients.isEmpty()) {
            System.out.println("Nao ha contas cadastradas.");
            Model.Util.pause();
            return;
        }

        System.out.println("Lista de Contas (sem dados restritos):");
        for (int i = 0; i < clients.size(); i++) {
            System.out
                    .println((i + 1) + " - Titular: " + clients.get(i).getNome() + " | Numero da Conta: "
                            + clients.get(i).getAccount().getNumConta()
                            + " | Banco: " + clients.get(i).getAccount().getBank().getNmBanco());
        }
        System.out.println(a);
        Model.Util.pause();
    }

    public static void Saque() {
        if (activeClient == null) {
            System.out.println("Nao ha conta ativa. Por favor, selecione uma conta.");
            Model.Util.pause();
            return;
        }

        System.out.println("Conta ativa: " + activeClient.getNome() + " - Numero da conta: "
                + activeClient.getAccount().getNumConta());

        String senha = "|";

        while (!senha.equals(activeClient.getAccount().getSenha())) {
            System.out.print("Digite a sua senha para confirmar o saque: ");
            senha = scanString();

            if (!senha.equals(activeClient.getAccount().getSenha())) {
                System.out.println("Senha incorreta. por favor digite a senha corretamente.");
                Model.Util.pause();
            }
        }

        System.out.print("Digite o valor do saque: ");
        double valorSaque = scanDouble();
        scan.nextLine();

        if (valorSaque <= 0) {
            System.out.println("O valor do saque deve ser maior que zero.");
            Model.Util.pause();
            return;
        }

        if (valorSaque > activeClient.getAccount().getSaldo()) {
            System.out.println("Saldo insuficiente para o saque.");
            Model.Util.pause();
            return;
        }

        activeClient.getAccount().setSaldo(activeClient.getAccount().getSaldo() - valorSaque);
        activeClient.getAccount().adicionarMovimentacao("Saque de R$" + valorSaque);

        System.out.println("Saque de R$" + valorSaque + " realizado com sucesso.");
        System.out.println("Novo saldo: R$" + activeClient.getAccount().getSaldo());

        Model.Util.pause();
    }

    public static void deposito() {
        if (activeClient == null) {
            System.out.println("Nao ha conta ativa. Por favor, selecione uma conta.");
            Model.Util.pause();
            return;
        }

        System.out.println("Conta ativa: " + activeClient.getNome() + " - Numero da conta: "
                + activeClient.getAccount().getNumConta());

        String senha = "|";

        while (!senha.equals(activeClient.getAccount().getSenha())) {
            System.out.print("Digite a sua senha para confirmar o depC3sito: ");
            senha = scanString();

            if (!senha.equals(activeClient.getAccount().getSenha())) {
                System.out.println("Senha incorreta. por favor \ndigite a sua senha corretamente.");
                Model.Util.pause();
            }
        }

        System.out.print("Digite o valor do depC3sito: ");
        double valorDeposito = scanDouble();
        scan.nextLine();

        if (valorDeposito <= 0) {
            System.out.println("O valor do depC3sito deve ser maior que zero.");
            Model.Util.pause();
            return;
        }

        activeClient.getAccount().setSaldo(activeClient.getAccount().getSaldo() + valorDeposito);
        activeClient.getAccount().adicionarMovimentacao("DepC3sito de R$" + valorDeposito);
        System.out.println("DepC3sito de R$" + valorDeposito + " realizado com sucesso.");
        System.out.println("Novo saldo: R$" + activeClient.getAccount().getSaldo());

        Model.Util.pause();
    }

    public static void transferir() {
        if (activeClient == null) {
            System.out.println("Nao ha conta ativa. Por favor, selecione uma conta.");
            Model.Util.pause();
            return;
        }

        System.out.println("Conta ativa: " + activeClient.getNome() + " - Numero da conta: "
                + activeClient.getAccount().getNumConta());

        System.out.print("Digite a sua senha para confirmar a transferencia: ");
        String senha = scanString();

        if (!senha.equals(activeClient.getAccount().getSenha())) {
            System.out.println("Senha incorreta. A transferencia nao pode ser realizada.");
            Model.Util.pause();
            return;
        }

        ArrayList<Client> availableClient = new ArrayList<>();
        for (Client c : clients) {
            if (!c.equals(activeClient)) {
                availableClient.add(c);
            }
        }

        if (availableClient.isEmpty()) {
            System.out.println("Nao ha outras contas para transferir.");
            Model.Util.pause();
            return;
        }

        System.out.println("\nContas disponiveis para transferencia:");
        for (int i = 0; i < availableClient.size(); i++) {
            Client c = availableClient.get(i);
            System.out.println((i + 1) + " - " + c.getNome() + " - " + c.getAccount().getNumConta());
        }

        System.out.print("Escolha a conta para transferir: ");
        int escolhaTransferencia = scanInt();
        scan.nextLine();

        if (escolhaTransferencia <= 0 || escolhaTransferencia > availableClient.size()) {
            System.out.println("Escolha invalida.");
            Model.Util.pause();
            return;
        }

        Client destinyClient = availableClient.get(escolhaTransferencia - 1);

        System.out.print("Digite o valor da transferencia: ");
        double valorTransferencia = scanDouble();
        scan.nextLine();

        if (valorTransferencia <= 0) {
            System.out.println("O valor da transferencia deve ser maior que zero.");
            Model.Util.pause();
            return;
        }

        if (valorTransferencia > activeClient.getAccount().getSaldo()) {
            System.out.println("Saldo insuficiente para a transferencia.");
            Model.Util.pause();
            return;
        }

        activeClient.getAccount().setSaldo(activeClient.getAccount().getSaldo() - valorTransferencia);
        destinyClient.getAccount().setSaldo(destinyClient.getAccount().getSaldo() + valorTransferencia);

        activeClient.getAccount()
                .adicionarMovimentacao("Transferencia enviada de R$" + valorTransferencia + "\npara a conta "
                        + destinyClient.getNome() + " - " + destinyClient.getAccount().getNumConta());
        destinyClient.getAccount()
                .adicionarMovimentacao("Transferencia recebida de R$" + valorTransferencia + " da conta "
                        + activeClient.getNome() + " - " + activeClient.getAccount().getNumConta());

        System.out.println("Transferencia de R$" + valorTransferencia + "\nrealizada com sucesso.");
        System.out.println("Seu novo saldo: R$" + activeClient.getAccount().getSaldo());
        Model.Util.pause();
    }

    public static void verSaldo() {
        String str = "sla";
        if (activeClient == null) {
            System.out.println("Nao ha conta ativa.");
            Model.Util.pause();
            return;
        }
        while (!str.equals(activeClient.getAccount().getSenha())) {
            System.out.println("digite a sua senha.");
            str = scanString();

            if (!str.equals(activeClient.getAccount().getSenha())) {
                System.out.println("digite a senha corretamente.");
            }
        }

        System.out
                .println("Saldo da conta de " + activeClient.getNome() + ": R$" + activeClient.getAccount().getSaldo());
        Model.Util.pause();
    }

    public static void verMovimentacao() {
        if (activeClient == null) {
            System.out.println("Nao ha conta ativa.");
            Model.Util.pause();
            return;
        }

        String str = "sla";
        while (!str.equals(activeClient.getAccount().getSenha())) {
            System.out.println("digite a sua senha.");
            str = scanString();
            if (!str.equals(activeClient.getAccount().getSenha())) {
                System.out.println("digite a senha corretamente.");
            }
        }

        System.out.println("MovimentacC5es da conta de " + activeClient.getNome() + ":");
        for (String movimentacao : activeClient.getAccount().getMovimentacoes()) {
            System.out.println(movimentacao);
        }

        Model.Util.pause();
    }

    public static void guardaArquivo() {
        String caminho = "arquivo.txt";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(caminho)))) {
            for (Client client : clients) {

                writer.print(client.getNome() + ";" +
                        client.getAccount().getSenha() + ";" +
                        client.getAccount().getSaldo() + ";" +
                        client.getAccount().getBank().getNmBanco() + ";" +
                        client.getAccount().getBank().getAgencia() + ";" +
                        client.getAccount().getNumConta() + ";" +
                        client.getCpf() + ";" +
                        (client.getAddress() != null ? client.getAddress() : "") + ";" +
                        (client.getAccount().getNumRec() != null ? client.getAccount().getNumRec() : "") + ";");

                ArrayList<String> movs = client.getAccount().getMovimentacoes();
                for (int i = 0; i < movs.size(); i++) {
                    writer.print(movs.get(i));
                    if (i < movs.size() - 1)
                        writer.print("|");
                }
                writer.println();
            }

            System.out.println("Contas salvas com sucesso em: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }

        Model.Util.pause();
    }

    public static void carregaArquivo() {
        String caminho = "arquivo.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            clients.clear();
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length < 10)
                    continue;

                Client client = new Client();
                client.setNome(partes[0]);
                Account account = new Account();
                account.setSenha(partes[1]);
                account.setSaldo(Double.parseDouble(partes[2]));
                Bank bank = new Bank();
                bank.setNmBanco(partes[3]);
                bank.setAgencia(Integer.parseInt(partes[4]));
                account.setBank(bank);
                account.setNumConta(partes[5]);
                client.setAccount(account);
                client.setCpf(partes[6]);
                String[] addressParts = partes[7].split(",", 2);
                String street = addressParts.length > 0 ? addressParts[0].trim() : "";
                String number = addressParts.length > 1 ? addressParts[1].trim() : "";
                Address address = new Address(street, number);
                client.setAddress(address);
                client.getAccount().setNumRec(partes[8]);

                String[] movs = partes[9].split("\\|");
                for (String m : movs) {
                    if (!m.isEmpty())
                        client.getAccount().adicionarMovimentacao(m);
                }

                clients.add(client);
            }

            System.out.println("Contas carregadas com sucesso do arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }

        Model.Util.pause();
    }

    public static int scanInt() {
        int num = 0;
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Por favor, digite um numero valido.");
        }
        num = scan.nextInt();
        scan.nextLine();
        return num;
    }

    public static String scanString() {
        String str;
        str = scan.nextLine();
        return str;
    }

    public static double scanDouble() {
        double num = 0;

        while (!scan.hasNextDouble()) {
            scan.next();
            System.out.println("Por favor, digite um numero valido.");
        }
        num = scan.nextDouble();
        scan.nextLine();
        return num;
    }

    public static char scanChar() {
        String input = scan.next();
        return input.length() == 1 ? input.charAt(0) : '\0';
    }

    public static void printMenuHomossexual() {
        System.out.println("=================================");
        System.out.println("||                             ||");
        System.out.println("||     BANCO DIGITAL JAVA      ||");
        System.out.println("||                             ||");
        System.out.println("=================================");

    }

}