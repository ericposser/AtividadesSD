/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tokensd;

/**
 *
 * @author laboratorio
 */
public class TokenRing {

    private Processo[] processos;  
    private int detentorAtualDoToken; 

    // inicia o tokenrings com numero de processos
    public TokenRing(int numProcessos) {
        processos = new Processo[numProcessos]; 
        detentorAtualDoToken = 0;  
        for (int i = 0; i < numProcessos; i++) {
            processos[i] = new Processo(i, this, i == 0); 
        }
    }

    // inicia todos os processos com thread
    public void iniciar() {
        for (Processo processo : processos) {
            new Thread(processo).start();
        }
    }

    // passa o token para o proximo processo
    public void passarToken(Processo processoAtual) {
        processoAtual.setPossuiToken(false);  // remove o token do processo atual
        int proximoProcesso = (detentorAtualDoToken + 1) % processos.length;  // calcula o processo token ring

        // pula processos inativos ate encontrar um processo ativo
        while (!processos[proximoProcesso].isAtivo()) {
            proximoProcesso = (proximoProcesso + 1) % processos.length;
        }

        processos[proximoProcesso].setPossuiToken(true);  // atribui o token para próximo processo ativo
        detentorAtualDoToken = proximoProcesso;  // atualiza o indice atual do token
        System.out.println("Token passou do processo " + processoAtual.getId() + " para o processo " + proximoProcesso + ".");
    }

    //desativa um processo no token ring
    public void desativarProcesso(int idProcesso) {
        processos[idProcesso].desativar();  // desativa pelo id
        System.out.println("Processo " + idProcesso + " foi desativado.");
    }
}


