/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividade;

/**
 *
 * @author eric_
 */
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import com.google.gson.Gson;

import java.net.URI;
import java.util.Scanner;

/**
 *
 * @author Andre
 */
public class WebSocketCalculatorClient extends WebSocketClient {

    private static Gson gson = new Gson();
    private boolean receivedResponse = false; // Adicionada a variável recebida

    public WebSocketCalculatorClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Conectado ao servidor WebSocket");
        Scanner scanner = new Scanner(System.in);

        // Menu de opções
        System.out.println("Escolha uma operacao:");
        System.out.println("1. Soma");
        System.out.println("2. Subtracao");
        System.out.println("3. Multiplicacao");
        System.out.println("4. Divisao");
        System.out.print("OPCAO: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        // Recebe os números do usuário
        System.out.print("Digite o primeiro numero: ");
        double num1 = Double.parseDouble(scanner.nextLine());

        System.out.print("Digite o segundo numero: ");
        double num2 = Double.parseDouble(scanner.nextLine());

        // Cria o objeto de requisição
        CalculatorRequest request = new CalculatorRequest(opcao, num1, num2);

        // Envia a requisição serializada para o servidor
        String jsonRequest = gson.toJson(request);
        send(jsonRequest);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Resposta do servidor: " + message);
        receivedResponse = true;
        
        // Verifica se a resposta é a esperada e fecha a conexão
        if (message.startsWith("Resultado:")) {
            close();  // Fecha a conexão após receber a resposta de resultado
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Conexão encerrada.");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args) {
        try {
            WebSocketCalculatorClient client = new WebSocketCalculatorClient(new URI("ws://localhost:8080"));
            client.connect();

            // Aguarda até que a resposta de resultado seja recebida
            while (!client.receivedResponse) {
                Thread.sleep(100); // Aguarda 100ms para verificar novamente
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Classe para representar a requisição de cálculo
    private static class CalculatorRequest {
        int opcao;
        double num1;
        double num2;

        public CalculatorRequest(int opcao, double num1, double num2) {
            this.opcao = opcao;
            this.num1 = num1;
            this.num2 = num2;
        }
    }
}
