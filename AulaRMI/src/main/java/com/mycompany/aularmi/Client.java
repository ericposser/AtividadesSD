/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.aularmi;

/**
 *
 * @author laboratorio
 */
import java.rmi.*;
import java.rmi.server.*;

public class Client {

    public static void main(String[] args) {
        try{
            IIncrementa c = (IIncrementa) Naming.lookup("rmi://localhost/Incrementa");
            double n1 = 5;
            double n2 = 5;
            System.out.println("soma: " + c.soma(n1, n2));
           System.out.println("subtracao: " + c.sub(n1, n2));
           System.out.println("multiplicacao: " + c.multi(n1, n2));
           System.out.println("divisao: " + c.divisao(n1, n2));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
}
