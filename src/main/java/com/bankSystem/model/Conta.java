package com.bankSystem.model;

import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class Conta {
    private static int SEQUENCIAL = 1;

    protected String numeroConta;
    protected BigDecimal saldo;
    protected Cliente cliente;
    protected HistoricoTransacoes historico;


    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = BigDecimal.ZERO;
        this.historico = new HistoricoTransacoes();
        this.numeroConta = gerarNumeroConta();
    }

    protected String gerarNumeroConta() {
        return "AC" + System.currentTimeMillis(); // Gera um número de conta único baseado no timestamp atual
    }

    public void depositar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }

        saldo = saldo.add(valor);
        historico.adicionar(new Transacao(TipoTransacao.DEPOSITO, valor, "Depósito realizado"));
    }

    public void sacar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo");
        }

        if (valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente para saque");
        }

        saldo = saldo.subtract(valor);
        historico.adicionar(new Transacao(TipoTransacao.SAQUE, valor, "Saque realizado"));
    }

    public void transferir(BigDecimal valor, Conta destino) {
        this.sacar(valor);
        destino.depositar(valor);
        historico.adicionar(new Transacao(TipoTransacao.TRANSFERENCIA, valor, "Transferência para " + destino.getNumeroConta()));
    }

    public String getTipo() {
        return null;
    }

    public void exibirExtrato() {
        System.out.println("Extrato da Conta " + getTipo());
        for (Transacao evento : historico.getTransacoes()) {
            System.out.println(" - " + evento);
        }
        System.out.println("Saldo atual: R$" + saldo);
    }
}
