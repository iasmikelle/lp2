import java.util.Scanner;

public class Banco1000 {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== Banco 1000 ===");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Login");
            System.out.println("3. Criar conta");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Depositar");
            System.out.println("6. Sacar");
            System.out.println("7. Transferir");
            System.out.println("8. Logout");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    scanner.nextLine(); // consume the newline
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    sistema.cadastrarCliente(nome, cpf, senha);
                    break;
                case 2:
                    System.out.print("CPF: ");
                    String loginCpf = scanner.next();
                    System.out.print("Senha: ");
                    String loginSenha = scanner.next();
                    sistema.autenticar(loginCpf, loginSenha);
                    break;
                case 3:
                    System.out.print("Tipo da conta (Corrente/Poupança): ");
                    String tipoConta = scanner.next();
                    System.out.print("Saldo inicial: ");
                    double saldoInicial = scanner.nextDouble();
                    sistema.criarConta(tipoConta, saldoInicial);
                    break;
                case 4:
                    sistema.consultarSaldo();
                    break;
                case 5:
                    sistema.realizarDeposito();
                    break;
                case 6:
                    sistema.realizarSaque();
                    break;
                case 7:
                    sistema.realizarTransferencia();
                    break;
                case 8:
                    sistema.logout();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }
}
