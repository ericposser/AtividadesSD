/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aularmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author laboratorio
 */
public class Server {
    String HOST_URL = "rmi://localhost/Incrementa";
    
    public Server(){
        try{
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Incrementa c = new Incrementa();
            Naming.bind(HOST_URL, c);
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    
    public static void main(String args[]) {
        new Server();
    } 
}
