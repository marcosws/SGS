/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.controller;

import br.senai.sp.model.dao.DepartamentoDao;
import br.senai.sp.model.entity.Departamento;
import br.senai.sp.view.chid.DepartamentoChild;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ControllerDepartamento implements IController{

    private DepartamentoChild departamentoChild;
    
    @Override
    public void incluir(Object view) {
        departamentoChild = (DepartamentoChild) view;
        DepartamentoDao departamentoDao = new DepartamentoDao();
        Departamento departamento = new Departamento();
        departamento.setNome(departamentoChild.getTxtNome().getText());
        departamentoDao.inserir(departamento);
    }

    @Override
    public void alterar(Object view) {
        departamentoChild = (DepartamentoChild) view;
        DepartamentoDao departamentoDao = new DepartamentoDao();
        Departamento departamento = new Departamento(); 
        departamento.setId(Long.parseLong(departamentoChild.getTxtId().getText()));
        departamento.setNome(departamentoChild.getTxtNome().getText());
        departamentoDao.alterar(departamento);
    }

    @Override
    public void excluir(Object view) {
        
        departamentoChild = (DepartamentoChild) view;
        DepartamentoDao departamentoDao = new DepartamentoDao();
        Departamento departamento = new Departamento();
        departamento.setId(Long.parseLong(departamentoChild.getTxtId().getText()));
        departamentoDao.excluir(departamento);
    }

    @Override
    public void consultar(Object view) {
        
        departamentoChild = (DepartamentoChild) view;
        DepartamentoDao departamentoDao = new DepartamentoDao();
        List<Departamento> departamentos = departamentoDao.consultar();
        departamentoChild.getGridView().clearGrid();
        departamentos.forEach((d) -> {
            departamentoChild.getGridView().getModel().addRow(new Object[]{d.getId(), d.getNome()});
        });
       
    }
    
}
