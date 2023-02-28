/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.chid;

import br.senai.sp.controller.ControllerCompra;
import br.senai.sp.view.common.GridView;
import br.senai.sp.view.common.Operacao;
import br.senai.sp.view.mdi.PrincipalSize;
import br.senai.sp.view.common.SgsConstantes;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Marcos
 */
public class CompraChild extends GenericChild{

    private JTextField txtId;
    private JTextField txtData;
    private JTextField txtQuantidade;
    private JTextField txtTotal;
    private JComboBox cbProduto;
    private JTextField txtPreco;
    private String operacao;
    private GridView gridView;

    public GridView getGridView() {
        return gridView;
    }

    public JTextField getTxtQuantidade() {
        return txtQuantidade;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JComboBox getCbProduto() {
        return cbProduto;
    }

    public JTextField getTxtPreco() {
        return txtPreco;
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
    
    public CompraChild(){
        
        super("Compra","Operação de Compra");
        
        txtId = new JTextField();
        txtData = new JTextField();
        txtQuantidade = new JTextField();
        txtTotal = new JTextField();
        cbProduto = new JComboBox();   
        txtPreco = new JTextField();
        
        JLabel lbId = new JLabel("Id:");
        lbId.setBounds(20, 120, 500, 20);
        txtId.setBounds(20, 140, 100, 20);
        txtId.setEditable(false);

        JLabel lbData = new JLabel("Data:");
        lbData.setBounds(20, 160, 500, 20);
        txtData.setBounds(20, 180, 100, 20);
        txtData.setEditable(false);
        
        JLabel lbProduto = new JLabel("Produto:");
        lbProduto.setBounds(20, 200, 500, 20);
        cbProduto.setBounds(20, 220, 700, 20);
        
        JLabel lbQuantidade = new JLabel("Quantidade Adquirida:");
        lbQuantidade.setBounds(20, 240, 500, 20);
        txtQuantidade.setBounds(20, 260, 200, 20);
        
        JLabel lbPreco = new JLabel("Preço por unidade:");
        lbPreco.setBounds(20, 280, 500, 20);
        txtPreco.setBounds(20, 300, 200, 20);
        
        JLabel lbTotal = new JLabel("Total Estoque:");
        lbTotal.setBounds(20, 320, 500, 20);
        txtTotal.setBounds(20, 340, 200, 20);
        txtTotal.setEditable(false);
        
        JLabel lbComprasCadastrados = new JLabel("Compras Cadastradas:");
        lbComprasCadastrados.setBounds(20, 360, 500, 20);
        gridView = new GridView();
        gridView.getModel().addColumn("Id"); 
        gridView.getModel().addColumn("Data"); 
        gridView.getModel().addColumn("Preco"); 
        gridView.getModel().addColumn("Quantidade"); 
        gridView.getModel().addColumn("Total"); 
        gridView.getModel().addColumn("Produto"); 
        gridView.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = gridView.getTable().getSelectedRow();
                txtId.setText(gridView.getTable().getModel().getValueAt(row, 0).toString());
                txtData.setText(gridView.getTable().getModel().getValueAt(row, 1).toString());
                txtPreco.setText(gridView.getTable().getModel().getValueAt(row, 2).toString());
                txtQuantidade.setText(gridView.getTable().getModel().getValueAt(row, 3).toString());
                txtTotal.setText(gridView.getTable().getModel().getValueAt(row, 4).toString());
                String produtoGrid = gridView.getTable().getModel().getValueAt(row, 5).toString();
                for(int i = 0;i < cbProduto.getItemCount();i++){
                    if(cbProduto.getModel().getElementAt(i).toString().contains(produtoGrid)){
                        cbProduto.getModel().setSelectedItem(cbProduto.getModel().getElementAt(i).toString());
                    }
                }
               
            }
        });
        gridView.getjScrollPane().setBounds(20, 380, 700, 200);

        panel.add(lbId);
        panel.add(lbData);
        panel.add(lbProduto);
        panel.add(lbQuantidade);
        panel.add(lbPreco);
        panel.add(lbTotal);
        panel.add(lbComprasCadastrados);
        panel.add(txtId);
        panel.add(txtData);
        panel.add(cbProduto);
        panel.add(txtQuantidade);
        panel.add(txtPreco);
        panel.add(txtTotal);
        panel.add(gridView.getjScrollPane());
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setBounds(0, 0, (int)PrincipalSize.getWidth(), (int)PrincipalSize.getHeight());
        frame.setVisible(true);
        
    }
    public void inicializa(){
        ControllerCompra controllerCompra = new ControllerCompra();
        controllerCompra.carregarCombo(this);
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
                ControllerCompra controllerCompra = new ControllerCompra();
                controllerCompra.incluir(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.ALTERAR.getNomeOperacao())){
                ControllerCompra controllerCompra = new ControllerCompra();
                controllerCompra.alterar(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.EXCLUIR.getNomeOperacao())){
                ControllerCompra controllerCompra = new ControllerCompra();
                controllerCompra.excluir(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.CONSULTAR.getNomeOperacao())){
                ControllerCompra controllerCompra = new ControllerCompra();
                controllerCompra.consultar(this);
            }
         
        }
        else if(evento.getSource().equals(botaoFechar)){
            this.getFrame().dispose();
        }
    }
    public void limparCampos(){
        txtId.setText("");
        txtPreco.setText("");
        txtData.setText("");
        txtQuantidade.setText("");
        txtTotal.setText("");
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
