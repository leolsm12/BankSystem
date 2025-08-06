package com.bankSystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoTest {
    @Test
    void deveCriarTransacaoCorretamente() {
        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, 100.0, "Depósito inicial");

        assertEquals(TipoTransacao.DEPOSITO, transacao.getTipo());
        assertEquals(100.0, transacao.getValor());
        assertEquals("Depósito inicial", transacao.getDescricao());
        assertNotNull(transacao.getDataHora());
    }

    @Test
    void toStringDeveRetornarFormatoCorreto() {
        Transacao transacao = new Transacao(TipoTransacao.SAQUE, 50.0, "Retirada");
        String toString = transacao.toString();

        assertTrue(toString.contains("SAQUE"));
        assertTrue(toString.contains("50.0"));
        assertTrue(toString.contains("Retirada"));
    }
}
