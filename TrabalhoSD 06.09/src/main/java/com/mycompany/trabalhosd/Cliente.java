/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosd;

import java.rmi.Naming;

public class Cliente {
    public static void main(String[] args) {
        try {
            //procura o objeto remoto no registro RMI
            Interface saudacao = (Interface) Naming.lookup("rmi://localhost/ClasseServico");

            //chama metodo remoto ola mundo
            String resposta = saudacao.OlaMundo();
            System.out.println(resposta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
