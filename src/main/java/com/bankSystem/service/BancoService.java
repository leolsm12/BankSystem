package com.bankSystem.service;

import com.bankSystem.model.Cliente;
import com.bankSystem.model.Conta;
import com.bankSystem.model.ContaCorrente;
import com.bankSystem.model.ContaPoupanca;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BancoService {
    private final Map<String, Conta> contas = new HashMap<>();

    public Conta abrirConta(String tipo, Cliente cliente) {
        Conta conta;
        switch (tipo.toLowerCase()) {
            case "corrente" -> conta = new ContaCorrente(cliente);
            case "poupanca" -> conta = new ContaPoupanca(cliente);
            default -> throw new IllegalArgumentException("Tipo de conta inválido");
        }
        contas.put(conta.getNumeroConta(), conta);
        return conta;
    }

    public void depositar(String numeroConta, BigDecimal valor) {
        Conta conta = contas.get(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada");
        }
        conta.depositar(valor);

    }

    public void sacar(String numeroConta, BigDecimal valor) {
        Conta conta = contas.get(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada");
        }
        conta.sacar(valor);
    }

    public void transferir(String numeroOrigem, String numeroDestino, BigDecimal valor) {
        Conta origem = contas.get(numeroOrigem);
        Conta destino = contas.get(numeroDestino);

        if (origem == null || destino == null) {
            throw new IllegalArgumentException("Conta de origem ou destino não encontrada");
        }
        origem.transferir(valor, destino);
    }

    public Conta buscarConta(String numeroConta) {
        return contas.get(numeroConta);
    }
}
