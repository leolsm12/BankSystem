package com.bankSystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricoTransacoesTest {
    private HistoricoTransacoes historico;

    @BeforeEach
    void setUp() {
        historico = new HistoricoTransacoes();
    }

    @Test
    void deveAdicionarTransacao() {
        Transacao t = new Transacao(TipoTransacao.DEPOSITO, BigDecimal.valueOf(200), "Depósito inicial");
        historico.adicionar(t);

        List<Transacao> transacoes = historico.getTransacoes();
        assertEquals(1, transacoes.size());
        assertEquals(t, transacoes.getFirst());
    }

    @Test
    void listaDeTransacoesNaoPodeSerModificadaExternamente() {
        Transacao t = new Transacao(TipoTransacao.SAQUE, BigDecimal.valueOf(50), "Saque");
        historico.adicionar(t);

        List<Transacao> transacoes = historico.getTransacoes();

        assertThrows(UnsupportedOperationException.class, () -> {
            transacoes.add(new Transacao(TipoTransacao.DEPOSITO, BigDecimal.valueOf(10), "Hack"));
        });
    }
}
