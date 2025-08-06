package com.bankSystem.model;

public class ContaCorrente extends Conta {
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String getTipo() {
        return "Corrente";
    }
}
