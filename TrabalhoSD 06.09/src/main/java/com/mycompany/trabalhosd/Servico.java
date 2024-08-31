/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalhosd;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servico {
    public static void main(String[] args) {
        try {
            //inicia o rmi na porta 1099
            LocateRegistry.createRegistry(1099);

            //craindo uma instacia da classe servidor
            Servidor servidor = new Servidor();

            //nomeando a instancia com nome de "ClasseServico" 
            Naming.rebind("ClasseServico", servidor);
            
            //imprime a mensagem alerta dizendo que o serviço esta em execução
            System.out.println("ClasseServico está em execução");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
