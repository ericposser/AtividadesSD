/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atividade;

/**
 *
 * @author eric_
 */
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import com.google.gson.Gson;
import java.net.InetSocketAddress;
/**
 *
 * @author Andre
 */
public class WebSocketCalculatorServer extends WebSocketServer {

    private static Gson gson = new Gson();

    public WebSocketCalculatorServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Novo cliente conectado: " + conn.getRemoteSocketAddress());
        conn.send("Bem-vindo ao servidor de calculadora via WebSocket!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Mensagem recebida: " + message);

        // Deserializa a mensagem do cliente
        CalculatorRequest request = gson.fromJson(message, CalculatorRequest.class);

        // Realiza o cálculo
        String resultado = calcular(request.num1, request.num2, request.opcao);

        // Envia o resultado de volta ao cliente
        conn.send("Resultado: " + resultado);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Cliente desconectado: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }
    
    @Override
    public void onStart() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        System.out.println("Servidor WebSocket foi iniciado com sucesso.");
    }

    private String calcular(double num1, double num2, int opcao) {
        switch (opcao) {
            case 1:
                return String.valueOf(num1 + num2);
            case 2:
                // Verifica se num1 é menor que num2, e troca a ordem para evitar resultado negativo
                if (num1 < num2) {
                    return String.valueOf(num2 - num1);
                } else {
                    return String.valueOf(num1 - num2);
                }                
            case 3:
                return String.valueOf(num1 * num2);
            case 4:
                if (num2 != 0) {
                    return String.valueOf(num1 / num2);
                } else {
                    return "Erro: Divisão por zero!";
                }
            default:
                return "Erro: Operação inválida!";
        }
    }

    public static void main(String[] args) {
        WebSocketServer server = new WebSocketCalculatorServer(new InetSocketAddress("localhost", 8080));
        server.start();
        System.out.println("Servidor WebSocket de calculadora rodando na porta 8080...");
    }

    // Classe para representar a requisição de cálculo
    private static class CalculatorRequest {
        int opcao;
        double num1;
        double num2;
    }
}
