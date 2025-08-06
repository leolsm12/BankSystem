package com.bankSystem.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testDeposito() {
        conta.depositar(100.0);
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    void testSaqueComSaldo() {
        conta.depositar(200.0);
        boolean resultado = conta.sacar(150.0);
        assertTrue(resultado);
        assertEquals(50.0, conta.getSaldo());
    }

    @Test
    void testSaqueSemSaldo() {
        boolean resultado = conta.sacar(50.0);
        assertFalse(resultado);
    }

    @Test
    void testTransferencia() {
        Cliente outro = new Cliente("Maria", "98765432100");
        Conta contaDestino = new ContaPoupanca(outro);

        conta.depositar(300.0);
        boolean resultado = conta.transferir(100.0, contaDestino);

        assertTrue(resultado);
        assertEquals(200.0, conta.getSaldo());
        assertEquals(100.0, contaDestino.getSaldo());
    }
}