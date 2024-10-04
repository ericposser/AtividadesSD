/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tokensd;

/**
 *
 * @author laboratorio
 */
public class TokenSD {
    public static void main(String[] args) {
        TokenRing tokenRing = new TokenRing(3); //cria com 3 processos
        tokenRing.iniciar(); //inicia

        try {
            Thread.sleep(5000);  // Aguarda 5 segundos antes de desativar um processo       
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


