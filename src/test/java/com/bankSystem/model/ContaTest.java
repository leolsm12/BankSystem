package com.bankSystem.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    private Conta conta;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Leo", "12345678900");
        conta = new ContaCorrente(cliente);
    }

    @Test
    void deveDepositarCorretamente() {
        conta.depositar(BigDecimal.valueOf(300));
        assertEquals(BigDecimal.valueOf(300), conta.getSaldo());
        assertEquals(1, conta.getHistorico().getTransacoes().size());
    }

    @Test
    void deveLancarExcecaoAoDepositarValorNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                conta.depositar(BigDecimal.valueOf(-50))
        );
    }

    @Test
    void deveSacarCorretamente() {
        conta.depositar(BigDecimal.valueOf(500));
        conta.sacar(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(300), conta.getSaldo());
        assertEquals(2, conta.getHistorico().getTransacoes().size());
    }

    @Test
    void deveLancarExcecaoAoSacarSemSaldo() {
        assertThrows(IllegalArgumentException.class, () ->
                conta.sacar(BigDecimal.valueOf(100))
        );
    }

    @Test
    void deveTransferirEntreContas() {
        Conta destino = new ContaCorrente(new Cliente("Lucas", "98765432100"));
        conta.depositar(BigDecimal.valueOf(1000));
        conta.transferir(BigDecimal.valueOf(400), destino);

        assertEquals(BigDecimal.valueOf(600), conta.getSaldo());
        assertEquals(BigDecimal.valueOf(400), destino.getSaldo());
        assertEquals(3, conta.getHistorico().getTransacoes().size());
    }

    @Test
    void deveLancarExcecaoAoTransferirSemSaldo() {
        Conta destino = new ContaCorrente(new Cliente("Lucas", "98765432100"));
        assertThrows(IllegalArgumentException.class, () ->
                conta.transferir(BigDecimal.valueOf(200), destino)
        );
    }

}