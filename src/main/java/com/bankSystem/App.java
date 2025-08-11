package com.bankSystem;

import com.bankSystem.model.Cliente;
import com.bankSystem.model.Conta;
import com.bankSystem.service.BancoService;

import java.math.BigDecimal;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    private static final BancoService bancoService = new BancoService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;

        System.out.println("=== Bem-vindo ao BankSystem ===");

        while (!sair) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Abrir conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Extrato");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1" -> abrirConta();
                    case "2" -> depositar();
                    case "3" -> sacar();
                    case "4" -> transferir();
                    case "5" -> extrato();
                    case "6" -> {
                        System.out.println("Saindo... Obrigado por usar o BankSystem!");
                        sair = true;
                    }
                    default -> System.out.println("Opção inválida, tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }
    private static boolean isCpfValido(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    private static void abrirConta() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        String cpf;
        do {
            System.out.print("CPF do cliente (apenas números, 11 dígitos): ");
            cpf = scanner.nextLine();
            if (!isCpfValido(cpf)) {
                System.out.println("CPF inválido! Digite um CPF válido com 11 números.");
            }
        } while (!isCpfValido(cpf));
        System.out.print("Tipo de conta (corrente/poupanca): ");
        String tipo = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        Conta conta = bancoService.abrirConta(tipo, cliente);

        System.out.println("Conta criada com sucesso! Número da conta: " + conta.getNumeroConta());
    }

    private static void depositar() {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        System.out.print("Valor para depositar: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        bancoService.depositar(numero, valor);
        System.out.println("Depósito realizado com sucesso!");
    }

    private static void sacar() {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();
        System.out.print("Valor para sacar: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        bancoService.sacar(numero, valor);
        System.out.println("Saque realizado com sucesso!");
    }

    private static void transferir() {
        System.out.print("Número da conta de origem: ");
        String origem = scanner.nextLine();
        System.out.print("Número da conta de destino: ");
        String destino = scanner.nextLine();
        System.out.print("Valor para transferir: ");
        BigDecimal valor = new BigDecimal(scanner.nextLine());

        bancoService.transferir(origem, destino, valor);
        System.out.println("Transferência realizada com sucesso!");
    }

    private static void extrato() {
        System.out.print("Número da conta: ");
        String numero = scanner.nextLine();

        Conta conta = bancoService.buscarConta(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.println("\n=== Extrato da Conta " + numero + " ===");
        System.out.println("Cliente: " + conta.getCliente().getNome());
        System.out.println("Saldo atual: R$ " + conta.getSaldo());

        conta.getHistorico().getTransacoes().forEach(t -> {
            System.out.println(t.getTipo() + " | Valor: R$ " + t.getValor() + " | " + t.getDescricao());
        });
        System.out.println("===============================");
    }
}