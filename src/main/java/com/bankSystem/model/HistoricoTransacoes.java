package com.bankSystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoTransacoes {
    private final List<Transacao> transacoes = new ArrayList<>();

    public void adicionar(Transacao transacao) {
        transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

    public void imprimirHistorico() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
            return;
        }

        for (Transacao t : transacoes) {
            System.out.println(t);
        }
    }
}
