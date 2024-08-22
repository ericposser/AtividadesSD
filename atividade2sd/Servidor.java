/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atividade2sd;

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
                            // Recebe operação e números do cliente
                            String operacao = dis.readUTF();
                            double num1 = dis.readDouble();
                            double num2 = dis.readDouble();

                            // Calcula o resultado
                            double resultado = 0;
                            switch (operacao) {
                                case "soma":
                                    resultado = num1 + num2;
                                    break;
                                case "subtracao":
                                    resultado = num1 - num2;
                                    break;
                                case "multiplicacao":
                                    resultado = num1 * num2;
                                    break;
                                case "divisao":
                                    if (num2 != 0) {
                                        resultado = num1 / num2;
                                    } else {
                                        dos.writeUTF("Erro: Divisão por zero");
                                        continue;
                                    }
                                    break;
                                default:
                                    dos.writeUTF("Operação inválida");
                                    continue;
                            }

                            // Envia o resultado de volta para o cliente
                            dos.writeUTF("Resultado: " + resultado);
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
