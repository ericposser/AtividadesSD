/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhosd;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
    //criando metodo ola mundo
    String OlaMundo() throws RemoteException;
}
