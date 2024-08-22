/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividadesd3;

/**
 *
 * @author eric_
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        try {
            // Cria um socket e tenta se conectar ao servidor (socket->connect)
            Socket s = new Socket("localhost", 1234);
            System.out.println("Conectado ao servidor");

            Thread t = new Thread() {
                public void run() {
                    try {
                        DataInputStream dis = new DataInputStream(s.getInputStream());
                        while (true) {
                            // Cliente aguarda uma mensagem do servidor
                            String mensagem = dis.readUTF(); // cliente aguarda mensagem do servidor
                            System.out.println(mensagem);
                        }
                    } catch (IOException e) {
                        System.out.println("Erro na thread de recebimento de mensagens do cliente");
                    }
                }
            };
            t.start();

            // Thread principal do cliente fica lendo pelo teclado e mandando pro servidor
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            while (true) {
                // Menu de operações
                System.out.println("Escolha a operação: saque, deposito, saldo");
                String operacao = teclado.nextLine();

                // Envia operação para o servidor
                dos.writeUTF(operacao);

                if (operacao.equals("saque") || operacao.equals("deposito")) {
                    // Leitura do valor para saque ou depósito
                    System.out.println("Digite o valor: ");
                    double valor = teclado.nextDouble();
                    teclado.nextLine(); // Consumir a nova linha

                    // Envia valor para o servidor
                    dos.writeDouble(valor);
                }
            }

        } catch (IOException ex) {
            System.out.println("Servidor não encontrado");
        }
    }
}
