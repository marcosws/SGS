/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.service;

import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class SaidaService {
    
    public boolean verificaEstoque(int qtdEstoque, int qtdSaida){
        
        if(qtdEstoque == 0){
            int option = JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(null, "Produto fora de estoque", "Sobre", option);
            return false;
        }
        else if(qtdSaida > qtdEstoque){
            int option = JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(null, "Quantidade informada não pode ser maior que a quantidade disponivel no estoque", "Sobre", option);
            return false;
        }
        else{
            return true;
        }
    }
    public boolean verificaAlteracaoEstoque(int qtdCadastrada, int qtdAlterada){
        
        if(qtdAlterada > qtdCadastrada){
            int option = JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(null, "Quantidade informada não pode ser maior que a quantidade cadastrada", "Sobre", option);
            return false;
        }
        else{
            return true;
        }
    }
    public boolean validaExclusao(){
        int option = JOptionPane.OK_CANCEL_OPTION;
        int op = JOptionPane.showConfirmDialog(null, "Deseja deletar a operação de saida, com isso a quantidade em estoque será restaurada.", "Sobre", option);
        return op == 0;
        
    }
    public int atualizaEstoque(int qtdEstoque, int qtdSaida){
        return qtdEstoque - qtdSaida;
    }
    
}
