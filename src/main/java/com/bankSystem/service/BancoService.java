package com.bankSystem.service;

import com.bankSystem.model.Cliente;
import com.bankSystem.model.Conta;
import com.bankSystem.model.ContaCorrente;
import com.bankSystem.model.ContaPoupanca;
import com.bankSystem.repository.ContaRepository;
import com.bankSystem.repository.MemoryContaRepository;

import java.math.BigDecimal;


public class BancoService {

    private final ContaRepository contaRepository;

    public BancoService() {
        this.contaRepository = new MemoryContaRepository();
    }

    public Conta abrirConta(String tipo, Cliente cliente) {
        Conta conta;
        switch (tipo.toLowerCase()) {
            case "corrente" -> conta = new ContaCorrente(cliente);
            case "poupanca" -> conta = new ContaPoupanca(cliente);
            default -> throw new IllegalArgumentException("Tipo de conta inválido");
        }
        contaRepository.salvar(conta);
        return conta;
    }

    public void depositar(String numeroConta, BigDecimal valor) {
        Conta conta = contaRepository.buscarPorNumero(numeroConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.depositar(valor);


    }

    public void sacar(String numeroConta, BigDecimal valor) {
        Conta conta = contaRepository.buscarPorNumero(numeroConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.sacar(valor);
    }

    public void transferir(String numeroOrigem, String numeroDestino, BigDecimal valor) {
        Conta origem = contaRepository.buscarPorNumero(numeroOrigem)
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada"));
        Conta destino = contaRepository.buscarPorNumero(numeroDestino)
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada"));
        origem.transferir(valor, destino);
    }


    public Conta buscarConta(String numeroConta) {
        return contaRepository.buscarPorNumero(numeroConta)
                .orElse(null);
    }

}
