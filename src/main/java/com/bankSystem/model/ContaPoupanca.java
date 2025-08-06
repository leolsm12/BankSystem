package com.bankSystem.model;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String getTipo() {
        return "Poupança";
    }
}
