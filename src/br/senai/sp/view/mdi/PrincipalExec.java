/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.mdi;

import br.senai.sp.model.service.CommonService;
import br.senai.sp.view.common.SgsConstantes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JFrame;

/**
 * @author Marcos
 */
public interface PrincipalExec {
    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(() -> {
                
            PrincipalMDI principalMdi = new PrincipalMDI();
            CommonService commonService = new CommonService();
            principalMdi.setTitle(SgsConstantes.NOME_SISTEMA + " - [" + commonService.getNameDataBase() + "]");
            principalMdi.inicializa();
            principalMdi.setJMenuBar(principalMdi.getMenuSistema());
            principalMdi.getContentPane().add(principalMdi.getStatusBar(), java.awt.BorderLayout.SOUTH);
            principalMdi.add(principalMdi.getjDesktopPane(), BorderLayout.CENTER);
            principalMdi.setMinimumSize(new Dimension(500, 500));
            principalMdi.pack();
            principalMdi.setExtendedState(Frame.MAXIMIZED_BOTH);
            principalMdi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            principalMdi.setVisible(true);
            Dimension dimension = principalMdi.getSize();
            PrincipalSize.setHeight(dimension.getHeight());
            PrincipalSize.setWidth(dimension.getWidth()); 
                
        });
        
    }
    
}
