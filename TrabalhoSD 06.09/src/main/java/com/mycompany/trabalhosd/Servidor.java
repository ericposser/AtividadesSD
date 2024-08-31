/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosd;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor extends UnicastRemoteObject implements Interface {
    //construtor
    protected Servidor() throws RemoteException {
        super();
    }

    //metodo olamundo retornando a string "ola mundo"
    @Override
    public String OlaMundo() throws RemoteException {
        return "Olá, mundo!";
    }
}
