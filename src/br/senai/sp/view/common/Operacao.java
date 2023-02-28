/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.common;

/**
 *
 * @author Marcos
 */
public enum Operacao {
    
    INCLUIR("INCLUIR"),
    ALTERAR("ALTERAR"),
    EXCLUIR("EXCLUIR"),
    CONSULTAR("CONSULTAR");
    
    private String nomeOperacao;

    Operacao(String nomeOperacao) {
        this.nomeOperacao = nomeOperacao;
    }

    public String getNomeOperacao() {
        return nomeOperacao;
    }
    
}
