/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.chid;

import br.senai.sp.controller.ControllerEstoque;
import br.senai.sp.view.common.GridView;
import br.senai.sp.view.mdi.PrincipalSize;
import br.senai.sp.view.common.SgsConstantes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Marcos
 */
public class EstoqueChild  implements ActionListener{

    protected JInternalFrame frame;
    protected JLabel lbTituloFrame;
    protected JLabel operacaoNome;
    protected JButton botaoConsultar;
    protected JButton botaoCancelar;
    protected JButton botaoConfirmar;
    protected JButton botaoFechar;
    public JPanel panel;
    protected GridView gridView;
    protected JComboBox cbDepartamento;

    public JComboBox getCbDepartamento() {
        return cbDepartamento;
    }
    

    public GridView getGridView() {
        return gridView;
    }
    

    public JInternalFrame getFrame() {
        return frame;
    }
    
    
    public EstoqueChild(){
        
        panel = new JPanel();
        panel.setLayout(null);
        frame = new JInternalFrame("Estoque", true, true, true, true);
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\IMG\\iconsgs16.png");
	frame.setFrameIcon(icon);
        
        lbTituloFrame = new JLabel("Consulta Estoque");
        lbTituloFrame.setFont(new Font("Arial",Font.BOLD,14));
        lbTituloFrame.setBounds(20, 20, 500, 20);
        
        botaoConsultar = new JButton("Consultar");
        botaoCancelar = new JButton("Cancelar");
        botaoConfirmar = new JButton("Confirmar");
        botaoFechar = new JButton("Fechar");
        cbDepartamento = new JComboBox();
        
        botaoConsultar.setBounds(20, 60, 100, 20);
        botaoCancelar.setBounds(120, 60, 100, 20);
        botaoConfirmar.setBounds(220, 60, 100, 20);
        botaoFechar.setBounds(320, 60, 100, 20);
        botaoCancelar.setEnabled(false);
        botaoConfirmar.setEnabled(false);
        
        operacaoNome = new JLabel("Selecione a Operação");
        operacaoNome.setFont(new Font("Arial",Font.BOLD,14));
        operacaoNome.setBounds(20, 100, 500, 20);
        operacaoNome.setForeground(Color.blue);
        
        JLabel lbDepartamento = new JLabel("Departamento:");
        lbDepartamento.setBounds(20, 120, 500, 20);
        cbDepartamento.setBounds(20, 140, 500, 20);
        
        JLabel lbEstoque = new JLabel("Estoque:");
        lbEstoque.setBounds(20, 160, 500, 20);
        gridView = new GridView();
        gridView.getModel().addColumn("Departamento"); 
        gridView.getModel().addColumn("Empresa"); 
        gridView.getModel().addColumn("Marca"); 
        gridView.getModel().addColumn("Produto"); 
        gridView.getModel().addColumn("Preço"); 
        gridView.getModel().addColumn("Data Cadastro"); 
        gridView.getModel().addColumn("Quantidade"); 
        gridView.getModel().addColumn("Estoque"); 
        gridView.getjScrollPane().setBounds(20, 180, 700, 200);
        
        /* IMPLEMENT HERE */
        
        
        panel.add(lbTituloFrame);
        panel.add(botaoConsultar);
        panel.add(botaoCancelar);
        panel.add(botaoConfirmar);
        panel.add(botaoFechar);
        panel.add(operacaoNome);
        panel.add(lbDepartamento);
        panel.add(lbEstoque);
        panel.add(cbDepartamento);
        panel.add(gridView.getjScrollPane());
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setBounds(0, 0, (int)PrincipalSize.getWidth(), (int)PrincipalSize.getHeight());
        frame.setVisible(true);
        
        
    }
    public void adicionaActionListener(){
        
        botaoConsultar.addActionListener(this);
        botaoCancelar.addActionListener(this);
        botaoConfirmar.addActionListener(this);
        botaoFechar.addActionListener(this);
        
    }
    public void inicializa(){
        ControllerEstoque controllerEstoque = new ControllerEstoque();
        controllerEstoque.carregarComboDepartamento(this);
    }
    protected void operacaoBotoesHabilita(boolean habilita){
        
        botaoConsultar.setEnabled(habilita);
        botaoCancelar.setEnabled(!habilita);
        botaoConfirmar.setEnabled(!habilita);
        
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        
    
        if(evento.getSource().equals(botaoConsultar)){
            this.operacaoBotoesHabilita(false);
            operacaoNome.setText(SgsConstantes.OP_CONSULTAR);
        }
        else if(evento.getSource().equals(botaoCancelar)){
            this.operacaoBotoesHabilita(true);
            operacaoNome.setText(SgsConstantes.OP_DEFAULT);
        }
        else if(evento.getSource().equals(botaoConfirmar)){
            this.operacaoBotoesHabilita(true);
            operacaoNome.setText(SgsConstantes.OP_DEFAULT);
            ControllerEstoque controllerEstoque = new ControllerEstoque();
            controllerEstoque.consultar(this);
        }
        else if(evento.getSource().equals(botaoFechar)){
            this.getFrame().dispose();
        }
    }
    
}
