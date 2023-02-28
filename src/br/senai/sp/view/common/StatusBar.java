/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.common;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Marcos
 */
public class StatusBar extends JLabel{
    
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 18));
        this.textoMensagem(SgsConstantes.NOME_SISTEMA);
    }
    
    private void textoMensagem(String msg){
        this.setText(" " + msg);
    }
    
}
