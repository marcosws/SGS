/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.chid;

import br.senai.sp.controller.ControllerMarca;
import br.senai.sp.view.common.GridView;
import br.senai.sp.view.common.Operacao;
import br.senai.sp.view.mdi.PrincipalSize;
import br.senai.sp.view.common.SgsConstantes;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Marcos
 */
public class MarcaChild extends GenericChild {
    
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtEmpresa;
    private String operacao;
    public GridView gridView;
    
    public GridView getGridView() {
        return gridView;
    }
   
    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtEmpresa() {
        return txtEmpresa;
    }

    public void setTxtEmpresa(JTextField txtEmpresa) {
        this.txtEmpresa = txtEmpresa;
    }
    
    public MarcaChild(){
        
        super("Marca","Cadastro de Marca");
        
        txtId = new JTextField();
        txtNome = new JTextField();
        txtEmpresa = new JTextField();
        
        JLabel lbId = new JLabel("Id:");
        lbId.setBounds(20, 120, 500, 20);
        txtId.setBounds(20, 140, 100, 20);
        txtId.setEditable(false);

        JLabel lbNome = new JLabel("Nome:");
        lbNome.setBounds(20, 160, 500, 20);
        txtNome.setBounds(20, 180, 500, 20);
        
        JLabel lbEmpresa = new JLabel("Empresa:");
        lbEmpresa.setBounds(20, 200, 500, 20);
        txtEmpresa.setBounds(20, 220 ,500, 20);
        
        JLabel lbMarcasCadastradas = new JLabel("Marcas Cadastradas:");
        lbMarcasCadastradas.setBounds(20, 240, 500, 20);
        gridView = new GridView();
        gridView.getModel().addColumn("Id");
        gridView.getModel().addColumn("Nome"); 
        gridView.getModel().addColumn("Empresa"); 
        gridView.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = gridView.getTable().getSelectedRow();
                txtId.setText(gridView.getTable().getModel().getValueAt(row, 0).toString());
                txtNome.setText(gridView.getTable().getModel().getValueAt(row, 1).toString());
                txtEmpresa.setText(gridView.getTable().getModel().getValueAt(row, 2).toString());
            }
        });
        gridView.getjScrollPane().setBounds(20, 260, 700, 200);
        
        panel.add(lbId);
        panel.add(lbNome);
        panel.add(lbEmpresa);
        panel.add(txtId);
        panel.add(txtNome);
        panel.add(txtEmpresa);
        panel.add(lbMarcasCadastradas);
        panel.add(gridView.getjScrollPane());
       
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setBounds(0, 0, (int)PrincipalSize.getWidth(), (int)PrincipalSize.getHeight());
        frame.setVisible(true);

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
                ControllerMarca controllerMarca = new ControllerMarca();
                controllerMarca.incluir(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.ALTERAR.getNomeOperacao())){
                ControllerMarca controllerMarca = new ControllerMarca();
                controllerMarca.alterar(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.EXCLUIR.getNomeOperacao())){
                ControllerMarca controllerMarca = new ControllerMarca();
                controllerMarca.excluir(this);
                this.limparCampos();
            }
            else if(operacao.equals(Operacao.CONSULTAR.getNomeOperacao())){
                ControllerMarca controllerMarca = new ControllerMarca();
                controllerMarca.consultar(this);
            }
         
        }
        else if(evento.getSource().equals(botaoFechar)){
            this.getFrame().dispose();
        }
      
    }
    public void limparCampos(){
        txtId.setText("");
        txtNome.setText("");
        txtEmpresa.setText("");
    }
    
}
