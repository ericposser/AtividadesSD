/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aularmi;

/**
 *
 * @author laboratorio
 */
import java.rmi.*;
import java.rmi.server.*;

public class Incrementa extends UnicastRemoteObject implements IIncrementa {
    public Incrementa() throws RemoteException {
        
    }
    
    @Override
    public double soma(double x, double y) throws RemoteException{
        return x + y;
    }
    
    @Override
    public double sub(double x, double y) throws RemoteException{
        if(x < b){
            throw new ArithmeticException("erro");
        }                
        return x - y;      
    }
    
    @Override
    public double multi(double x, double y) throws RemoteException{
        return x * y;      
    }
    
    @Override
    public double divisao(double x, double y) throws RemoteException{
        if(y == 0){
            throw new ArithmeticException("erro");
        }
        return x / y;      
    }
}
