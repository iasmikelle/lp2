import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sistema {
    private Map<String, Cliente> clientes;
    private Cliente usuarioLogado;

    public Sistema() {
        this.clientes = new HashMap<>();
        this.usuarioLogado = null;
    }

    public void cadastrarCliente(String nome, String cpf, String senha) {
        if (!clientes.containsKey(cpf)) {
            clientes.put(cpf, new Cliente(nome, cpf, senha));
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("CPF já cadastrado.");
        }
    }

    public void autenticar(String cpf, String senha) {
        Cliente cliente = clientes.get(cpf);
        if (cliente != null && cliente.autenticar(cpf, senha)) {
            usuarioLogado = cliente;
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("CPF ou senha inválidos.");
        }
    }

    public void criarConta(String tipo, double saldoInicial) {
        if (usuarioLogado != null) {
            Conta novaConta = new Conta(tipo, saldoInicial);
            usuarioLogado.adicionarConta(novaConta);
            System.out.println("Conta criada com sucesso! Número: " + novaConta.getNumeroConta());
        } else {
            System.out.println("É necessário estar logado para criar uma conta.");
        }
    }

    public void consultarSaldo() {
        if (usuarioLogado != null) {
            for (Conta conta : usuarioLogado.getContas()) {
                System.out.println("Conta " + conta.getNumeroConta() + " - Saldo: R$ " + conta.getSaldo());
            }
        } else {
            System.out.println("Faça login para consultar o saldo.");
        }
    }

    public void logout() {
        if (usuarioLogado != null) {
            System.out.println("Logout realizado com sucesso!");
            usuarioLogado = null;
        } else {
            System.out.println("Nenhum usuário logado.");
        }
    }

    public void realizarDeposito() {
        if (usuarioLogado != null) {
            System.out.println("Escolha uma conta para depósito:");
            for (Conta conta : usuarioLogado.getContas()) {
                System.out.println("Conta número: " + conta.getNumeroConta() + " - Saldo: R$ " + conta.getSaldo());
            }
            System.out.print("Digite o número da conta: ");
            Scanner scanner = new Scanner(System.in);
            int numeroConta = scanner.nextInt();
            Conta contaEscolhida = null;

            for (Conta conta : usuarioLogado.getContas()) {
                if (conta.getNumeroConta() == numeroConta) {
                    contaEscolhida = conta;
                    break;
                }
            }

            if (contaEscolhida != null) {
                System.out.print("Quanto deseja depositar? ");
                double valorDeposito = scanner.nextDouble();
                contaEscolhida.depositar(valorDeposito);
                System.out.println("Depósito realizado com sucesso!");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Faça login primeiro.");
        }
    }

    public void realizarSaque() {
        if (usuarioLogado != null) {
            System.out.println("Escolha uma conta para saque:");
            for (Conta conta : usuarioLogado.getContas()) {
                System.out.println("Conta número: " + conta.getNumeroConta() + " - Saldo: R$ " + conta.getSaldo());
            }
            System.out.print("Digite o número da conta: ");
            Scanner scanner = new Scanner(System.in);
            int numeroConta = scanner.nextInt();
            Conta contaEscolhida = null;

            for (Conta conta : usuarioLogado.getContas()) {
                if (conta.getNumeroConta() == numeroConta) {
                    contaEscolhida = conta;
                    break;
                }
            }

            if (contaEscolhida != null) {
                System.out.print("Quanto deseja sacar? ");
                double valorSaque = scanner.nextDouble();
                if (contaEscolhida.sacar(valorSaque)) {
                    System.out.println("Saque realizado com sucesso!");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Faça login primeiro.");
        }
    }

    public void realizarTransferencia() {
        if (usuarioLogado != null) {
            System.out.println("Escolha a conta de origem para transferência:");
            for (Conta conta : usuarioLogado.getContas()) {
                System.out.println("Conta número: " + conta.getNumeroConta() + " - Saldo: R$ " + conta.getSaldo());
            }
            System.out.print("Digite o número da conta de origem: ");
            Scanner scanner = new Scanner(System.in);
            int numeroContaOrigem = scanner.nextInt();
            Conta contaOrigem = null;

            for (Conta conta : usuarioLogado.getContas()) {
                if (conta.getNumeroConta() == numeroContaOrigem) {
                    contaOrigem = conta;
                    break;
                }
            }

            if (contaOrigem != null) {
                System.out.print("Digite o número da conta de destino: ");
                int numeroContaDestino = scanner.nextInt();
                Conta contaDestino = null;

                for (Conta conta : usuarioLogado.getContas()) {
                    if (conta.getNumeroConta() == numeroContaDestino) {
                        contaDestino = conta;
                        break;
                    }
                }

                if (contaDestino != null) {
                    System.out.print("Quanto deseja transferir? ");
                    double valorTransferencia = scanner.nextDouble();
                    if (contaOrigem.transferir(contaDestino, valorTransferencia)) {
                        System.out.println("Transferência realizada com sucesso!");
                    } else {
                        System.out.println("Falha na transferência. Verifique o saldo.");
                    }
                } else {
                    System.out.println("Conta de destino não encontrada.");
                }
            } else {
                System.out.println("Conta de origem não encontrada.");
            }
        } else {
            System.out.println("Faça login primeiro.");
        }
    }
}
