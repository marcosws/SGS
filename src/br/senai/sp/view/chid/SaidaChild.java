/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.chid;

import br.senai.sp.controller.ControllerSaida;
import br.senai.sp.view.common.GridView;
import br.senai.sp.view.common.Operacao;
import br.senai.sp.view.mdi.PrincipalSize;
import br.senai.sp.view.common.SgsConstantes;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Marcos
 */
public class SaidaChild extends GenericChild{
    
    private JTextField txtId;
    private JTextField txtData;
    private JTextField txtQuantidade;
    private JComboBox cbCompra;
    private String operacao;
    private GridView gridView;
    
    public GridView getGridView() {
        return gridView;
    }

    public JComboBox getCbCompra() {
        return cbCompra;
    }

    public JTextField getTxtQuantidade() {
        return txtQuantidade;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JTextField getTxtData() {
        return txtData;
    }

    public void setTxtData(JTextField txtData) {
        this.txtData = txtData;
    }
    
    public SaidaChild(){
         
        super("Saida","Operação de Saida");
        
        txtId = new JTextField();
        txtData = new JTextField();
        txtQuantidade = new JTextField();
        cbCompra = new JComboBox();   

        JLabel lbId = new JLabel("Id:");
        lbId.setBounds(20, 120, 500, 20);
        txtId.setBounds(20, 140, 100, 20);
        txtId.setEditable(false);

        JLabel lbData = new JLabel("Data:");
        lbData.setBounds(20, 160, 500, 20);
        txtData.setBounds(20, 180, 100, 20);
        txtData.setEditable(false);
        
        JLabel lbCompra = new JLabel("Compra:");
        lbCompra.setBounds(20, 200, 500, 20);
        cbCompra.setBounds(20, 220, 700, 20);
        
        JLabel lbQuantidade = new JLabel("Quantidade Retirada:");
        lbQuantidade.setBounds(20, 240, 500, 20);
        txtQuantidade.setBounds(20, 260, 200, 20);
       
        JLabel lbSaidasCadastrados = new JLabel("Saidas Cadastradas:");
        lbSaidasCadastrados.setBounds(20, 280, 500, 20);
        gridView = new GridView();
        gridView.getModel().addColumn("Id"); 
        gridView.getModel().addColumn("Data"); 
        gridView.getModel().addColumn("Quantidade"); 
        gridView.getModel().addColumn("Compra"); 
        gridView.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = gridView.getTable().getSelectedRow();
                txtId.setText(gridView.getTable().getModel().getValueAt(row, 0).toString());
                txtData.setText(gridView.getTable().getModel().getValueAt(row, 1).toString());
                txtQuantidade.setText(gridView.getTable().getModel().getValueAt(row, 2).toString());
                String compraGrid = gridView.getTable().getModel().getValueAt(row, 3).toString();
                for(int i = 0;i < cbCompra.getItemCount();i++){
                    if(cbCompra.getModel().getElementAt(i).toString().contains(compraGrid)){
                        cbCompra.getModel().setSelectedItem(cbCompra.getModel().getElementAt(i).toString());
                    }
                }
               
            }
        });
        gridView.getjScrollPane().setBounds(20, 300, 700, 200);

        panel.add(lbId);
        panel.add(lbData);
        panel.add(lbCompra);
        panel.add(lbQuantidade);
        panel.add(lbSaidasCadastrados);
        panel.add(txtId);
        panel.add(txtData);
        panel.add(cbCompra);
        panel.add(txtQuantidade);
        panel.add(gridView.getjScrollPane());
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setBounds(0, 0, (int)PrincipalSize.getWidth(), (int)PrincipalSize.getHeight());
        frame.setVisible(true);
        
    }
    public void inicializa(){
        ControllerSaida controllerSaida = new ControllerSaida();
        controllerSaida.carregarCombo(this);
        this.carregaDataCorrente();
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        if(evento.getSource().equals(botaoIncluir)){
            this.operacaoBotoesHabilita(false);
            operacao = Operacao.INCLUIR.getNomeOperacao();
            operacaoNome.setText(SgsConstantes.OP_INCLUIR);
        }
        else if(evento.getSource().equals(botaoAlterar)){
            this.operacaoBotoesHabilita(false);
            operacao = Operacao.ALTERAR.getNomeOperacao();
            operacaoNome.setText(SgsConstantes.OP_ALTERAR);
        }
        else if(evento.getSource().equals(botaoExcluir)){
            this.operacaoBotoesHabilita(false);
            operacao = Operacao.EXCLUIR.getNomeOperacao();
            operacaoNome.setText(SgsConstantes.OP_EXCLUIR);
        }
        else if(evento.getSource().equals(botaoConsultar)){
            this.operacaoBotoesHabilita(false);
            operacao = Operacao.CONSULTAR.getNomeOperacao();
            operacaoNome.setText(SgsConstantes.OP_CONSULTAR);
        }
        else if(evento.getSource().equals(botaoCancelar)){
            this.operacaoBotoesHabilita(true);
            operacaoNome.setText(SgsConstantes.OP_DEFAULT);
        }
        else if(evento.getSource().equals(botaoConfirmar)){
            this.operacaoBotoesHabilita(true);
            operacaoNome.setText(SgsConstantes.OP_DEFAULT);
            if(operacao.equals(Operacao.INCLUIR.getNomeOperacao())){
                ControllerSaida controllerSaida = new ControllerSaida();
                controllerSaida.incluir(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.ALTERAR.getNomeOperacao())){
                ControllerSaida controllerSaida = new ControllerSaida();
                controllerSaida.alterar(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.EXCLUIR.getNomeOperacao())){
                ControllerSaida controllerSaida = new ControllerSaida();
                controllerSaida.excluir(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.CONSULTAR.getNomeOperacao())){
                ControllerSaida controllerSaida = new ControllerSaida();
                controllerSaida.consultar(this);
            }
         
        }
        else if(evento.getSource().equals(botaoFechar)){
            this.getFrame().dispose();
        }
        
    }
    public void limparCampos(){
        txtId.setText("");
        txtData.setText("");
        txtQuantidade.setText("");
    }
    private void carregaDataCorrente(){ 
        txtData.setText(this.dataCorrente()); 
    }
    private String dataCorrente(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now); 
    }
 
}
