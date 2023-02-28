/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.chid;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Marcos
 */
public abstract class GenericChild implements ActionListener{
    
    protected JInternalFrame frame;
    protected JLabel lbTituloFrame;
    protected JLabel operacaoNome;
    protected JButton botaoIncluir;
    protected JButton botaoAlterar;
    protected JButton botaoExcluir;
    protected JButton botaoConsultar;
    protected JButton botaoCancelar;
    protected JButton botaoConfirmar;
    protected JButton botaoFechar;
    public JPanel panel;

    public JInternalFrame getFrame() {
        return frame;
    }
    
    public GenericChild(String nomeJanela ,String nomeFrame){
       

        
        panel = new JPanel();
        panel.setLayout(null);
        frame = new JInternalFrame(nomeJanela, true, true, true, true);
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\IMG\\iconsgs16.png");
	frame.setFrameIcon(icon);
        
        lbTituloFrame = new JLabel(nomeFrame);
        lbTituloFrame.setFont(new Font("Arial",Font.BOLD,14));
        lbTituloFrame.setBounds(20, 20, 500, 20);
       
        botaoIncluir = new JButton("Incluir");
        botaoAlterar = new JButton("Alterar");
        botaoExcluir = new JButton("Excluir");
        botaoConsultar = new JButton("Consultar");
        botaoCancelar = new JButton("Cancelar");
        botaoConfirmar = new JButton("Confirmar");
        botaoFechar = new JButton("Fechar");
        
        botaoIncluir.setBounds(20, 60, 100, 20);
        botaoAlterar.setBounds(120, 60, 100, 20);
        botaoExcluir.setBounds(220, 60, 100, 20);
        botaoConsultar.setBounds(320, 60, 100, 20);
        botaoCancelar.setBounds(420, 60, 100, 20);
        botaoConfirmar.setBounds(520, 60, 100, 20);
        botaoFechar.setBounds(620, 60, 100, 20);
        botaoCancelar.setEnabled(false);
        botaoConfirmar.setEnabled(false);
        
        operacaoNome = new JLabel("Selecione a Operação");
        operacaoNome.setFont(new Font("Arial",Font.BOLD,14));
        operacaoNome.setBounds(20, 100, 500, 20);
        operacaoNome.setForeground(Color.blue);
        
        panel.add(lbTituloFrame);
        panel.add(botaoIncluir);
        panel.add(botaoAlterar);
        panel.add(botaoExcluir);
        panel.add(botaoConsultar);
        panel.add(botaoCancelar);
        panel.add(botaoConfirmar);
        panel.add(botaoFechar);
        panel.add(operacaoNome);
        
    }
    public void adicionaActionListener(){
        
        botaoIncluir.addActionListener(this);
        botaoAlterar.addActionListener(this);
        botaoExcluir.addActionListener(this);
        botaoConsultar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoConfirmar.addActionListener(this);
        botaoFechar.addActionListener(this);
        
    }
    protected void operacaoBotoesHabilita(boolean habilita){
        
        botaoIncluir.setEnabled(habilita);
        botaoAlterar.setEnabled(habilita);
        botaoExcluir.setEnabled(habilita);
        botaoConsultar.setEnabled(habilita);
        botaoCancelar.setEnabled(!habilita);
        botaoConfirmar.setEnabled(!habilita);
        
    }
    
}
