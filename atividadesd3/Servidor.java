/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atividadesd3;

/**
 *
 * @author eric_
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static double saldo = 0.0;

    public static void main(String[] args) {
        try {
            // Cria um socket (socket->bind->listen)
            ServerSocket servidor = new ServerSocket(1234);

            // Servidor aguarda uma conexão de um cliente
            System.out.println("Aguardando conexão de um cliente...");
            Socket cliente = servidor.accept(); // bloqueante
            // Mostra dados do cliente que se conectou
            System.out.println("Recebi uma conexao de um cliente: " + cliente.getInetAddress().getHostAddress() + ":" + cliente.getPort());

            // Thread do servidor que fica aguardando mensagens do cliente
            Thread t = new Thread() {
                public void run() {
                    try {
                        DataInputStream dis = new DataInputStream(cliente.getInputStream());
                        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                        while (true) {
                            // Recebe operação do cliente
                            String operacao = dis.readUTF();

                            switch (operacao) {
                                case "saque":
                                    double valorSaque = dis.readDouble();
                                    if (valorSaque <= saldo) {
                                        saldo -= valorSaque;
                                        dos.writeUTF("Saque realizado com sucesso. Saldo atual: " + saldo);
                                    } else {
                                        dos.writeUTF("Erro: Saldo insuficiente.");
                                    }
                                    break;
                                case "deposito":
                                    double valorDeposito = dis.readDouble();
                                    saldo += valorDeposito;
                                    dos.writeUTF("Depósito realizado com sucesso. Saldo atual: " + saldo);
                                    break;
                                case "saldo":
                                    dos.writeUTF("Saldo atual: " + saldo);
                                    break;
                                default:
                                    dos.writeUTF("Operação inválida.");
                                    break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Erro na thread de recebimento de mensagens do servidor");
                    }
                }
            };
            t.start();

        } catch (IOException ex) {
            System.out.println("Porta TCP 1234 já está sendo utilizada");
        }
    }
}
