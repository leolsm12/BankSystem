package com.bankSystem.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Conta {
    private static int SEQUENCIAL = 1;

    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<String> historico = new ArrayList<>();

    public Conta(Cliente cliente) {
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void depositar(double valor) {
        saldo += valor;
        historico.add("Depósito: R$" + valor);
    }

    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            historico.add("Saque: R$" + valor);
            return true;
        }
        return false;
    }

    public boolean transferir(double valor, Conta destino) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            historico.add("Transferência para conta " + destino.numero + ": R$" + valor);
            return true;
        }
        return false;
    }

    public String getTipo() {
        return null;
    }

    public void exibirExtrato() {
        System.out.println("Extrato da Conta " + getTipo());
        for (String evento : historico) {
            System.out.println(" - " + evento);
        }
        System.out.println("Saldo atual: R$" + saldo);
    }
}
