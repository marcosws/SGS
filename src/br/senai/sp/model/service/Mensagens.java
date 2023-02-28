/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.service;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class Mensagens {
    
    private final String MSG_ERRO_19 = "Não é possivel excluir, Existe(m) registro(s) que utiliza(m) este cadastro. \nDelete os registros que utiliza este cadastro para concluir esta operação.";
    
    public void apresentaMensagem(SQLException e){
        if(e.getErrorCode() == 19){
            int option = JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(null, MSG_ERRO_19, "Erro", option);
        }
        else{
            int option = JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(null, e.getMessage() + " Erro N: " + e.getErrorCode(), "Erro", option);
        }
    }
    
}
