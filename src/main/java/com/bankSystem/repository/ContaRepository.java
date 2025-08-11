package com.bankSystem.repository;

import com.bankSystem.model.Conta;

import java.util.List;
import java.util.Optional;

public interface ContaRepository {
    void salvar(Conta conta);

    Optional<Conta> buscarPorNumero(String numeroConta);

    List<Conta> listarTodas();
}
