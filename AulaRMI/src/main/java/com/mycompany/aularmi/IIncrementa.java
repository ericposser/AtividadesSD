/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.aularmi;

/**
 *
 * @author laboratorio
 */
import java.rmi.*;

public interface IIncrementa extends Remote {
    public double soma(double x, double y) throws RemoteException;
    public double sub(double x, double y) throws RemoteException;
    public double multi(double x, double y) throws RemoteException;
    public double divisao(double x, double y) throws RemoteException;
}

