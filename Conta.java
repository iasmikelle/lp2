import java.util.ArrayList;
import java.util.List;

public class Conta {
    private static int contadorContas = 1000;
    private int numeroConta;
    private String tipo;
    private double saldo;
    private List<Movimentacao> movimentacoes;

    public Conta(String tipo, double saldoInicial) {
        this.numeroConta = contadorContas++;
        this.tipo = tipo;
        this.saldo = saldoInicial;
        this.movimentacoes = new ArrayList<>();
        movimentacoes.add(new Movimentacao("Abertura de Conta", saldoInicial));
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            movimentacoes.add(new Movimentacao("Depósito", valor));
        } else {
            System.out.println("O valor do depósito deve ser positivo.");
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            movimentacoes.add(new Movimentacao("Saque", valor));
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
            return false;
        }
    }

    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            movimentacoes.add(new Movimentacao("Transferência para conta " + destino.getNumeroConta(), valor));
            return true;
        }
        return false;
    }

    public void exibirMovimentacoes() {
        System.out.println("Movimentações da conta " + numeroConta + ":");
        for (Movimentacao m : movimentacoes) {
            System.out.println(m);
        }
    }
}
