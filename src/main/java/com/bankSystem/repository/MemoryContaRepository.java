package com.bankSystem.repository;

import com.bankSystem.model.Conta;

import java.util.*;

public class MemoryContaRepository implements ContaRepository {
    private final Map<String, Conta> contas = new HashMap<>();

    @Override
    public void salvar(Conta conta) {
        contas.put(conta.getNumeroConta(), conta);
    }

    @Override
    public Optional<Conta> buscarPorNumero(String numeroConta) {
        return Optional.ofNullable(contas.get(numeroConta));
    }

    @Override
    public List<Conta> listarTodas() {
        return new ArrayList<>(contas.values());
    }
}
