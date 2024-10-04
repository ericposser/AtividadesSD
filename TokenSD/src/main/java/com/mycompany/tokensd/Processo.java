/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tokensd;

/**
 *
 * @author laboratorio
 */
import java.util.Random;

public class Processo implements Runnable {
    private int id;  
    private TokenRing tokenRing;  
    private boolean possuiToken;  
    private boolean solicitouSecaoCritica;  
    private boolean ativo;

    
    public Processo(int id, TokenRing tokenRing, boolean possuiToken) {
        this.id = id;  
        this.tokenRing = tokenRing;  
        this.possuiToken = possuiToken;  
        this.solicitouSecaoCritica = false;  
        this.ativo = true; 
    }

    
    public int getId() {
        return id;
    }

    //verifica se possui token
    public boolean possuiToken() {
        return possuiToken;
    }

    // defina se possui token 
    public void setPossuiToken(boolean possuiToken) {
        this.possuiToken = possuiToken;
    }

    // verifica se esta solicitando secao critica
    public boolean isSolicitandoSecaoCritica() {
        return solicitouSecaoCritica;
    }

    // solicita a secao critica
    public void solicitarSecaoCritica() {
        solicitouSecaoCritica = true;  
    }

    // libera a secao critica
    public void liberarSecaoCritica() {
        solicitouSecaoCritica = false; 
    }

    
    public void desativar() {
        ativo = false; 
    }

    // verifica se esta ativo
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            if (ativo) {  
                if (possuiToken) {
                    if (solicitouSecaoCritica) { 
                        System.out.println("Processo " + id + " está na seção crítica.");
                        try {
                            Thread.sleep(1000);  
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        liberarSecaoCritica();  // libera a secao critica
                        System.out.println("Processo " + id + " saiu da seção crítica.");
                    }
                    // passa o token para o proximo processo
                    tokenRing.passarToken(this);
                }
                // simula a solicitação da seção crítica
                if (random.nextInt(10) < 2) {  // 20% de chance de solicitar a seção crítica
                    solicitarSecaoCritica();
                    System.out.println("Processo " + id + " requisitou seção crítica.");
                }
            } else {
                System.out.println("Processo " + id + " está inativo.");  //caso o processo esteja inativo
            }

            try {
                Thread.sleep(500);  // espera meio segundo antes de verificar proximo processo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


