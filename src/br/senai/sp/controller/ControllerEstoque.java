/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.controller;

import br.senai.sp.model.dao.DepartamentoDao;
import br.senai.sp.model.dao.EstoqueDao;
import br.senai.sp.model.entity.Departamento;
import br.senai.sp.model.entity.Estoque;
import br.senai.sp.model.service.CommonService;
import br.senai.sp.view.chid.EstoqueChild;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ControllerEstoque {
    
    private EstoqueChild estoqueChild;
    
    public void consultar(Object view){
        
        estoqueChild = (EstoqueChild) view;
        CommonService commonService = new CommonService();
        EstoqueDao estoqueDao = new EstoqueDao();
        List<Estoque> estoques = estoqueDao.consultar(estoqueChild.getCbDepartamento().getModel().getSelectedItem().toString());
        estoqueChild.getGridView().clearGrid();
        estoques.forEach((e) -> {
            estoqueChild.getGridView().getModel().addRow(new Object[]{
                e.getDepartamento(), 
                e.getEmpresa(),
                e.getMarca(),
                e.getProduto(),
                e.getPreco(),
                commonService.getStringData(e.getData()),
                e.getQuantidade(),
                e.getEstoque()
            });
        });
    }
    public void carregarComboDepartamento(Object view){
        
        estoqueChild = (EstoqueChild) view;
        DepartamentoDao departamentoDao = new DepartamentoDao();
        List<Departamento> departamentos = departamentoDao.consultar();
        departamentos.forEach((d) -> {
            estoqueChild.getCbDepartamento().addItem(d.getNome());
        });
    }
    
}
