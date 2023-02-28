/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.controller;

import br.senai.sp.model.dao.MarcaDao;
import br.senai.sp.model.entity.Marca;
import br.senai.sp.view.chid.MarcaChild;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Marcos
 */
public class ControllerMarca implements IController{
    
    private MarcaChild marcaChild;

    @Override
    public void incluir(Object view) {
        marcaChild = (MarcaChild) view;
        MarcaDao marcaDao = new MarcaDao();
        Marca marca = new Marca();
        marca.setNome(marcaChild.getTxtNome().getText());
        marca.setEmpresa(marcaChild.getTxtEmpresa().getText());
        marcaDao.inserir(marca);
    }

    @Override
    public void alterar(Object view) {
        marcaChild = (MarcaChild) view;
        MarcaDao marcaDao = new MarcaDao();
        Marca marca = new Marca();
        marca.setId(Long.parseLong(marcaChild.getTxtId().getText()));
        marca.setNome(marcaChild.getTxtNome().getText());
        marca.setEmpresa(marcaChild.getTxtEmpresa().getText());
        marcaDao.alterar(marca);
    }

    @Override
    public void excluir(Object view) {
        
        marcaChild = (MarcaChild) view;
        MarcaDao marcaDao = new MarcaDao();
        Marca marca = new Marca();
        marca.setId(Long.parseLong(marcaChild.getTxtId().getText()));
        marcaDao.excluir(marca);
        
    }

    @Override
    public void consultar(Object view) {
       
        marcaChild = (MarcaChild) view;
        MarcaDao marcaDao = new MarcaDao();
        List<Marca> marcas = marcaDao.consultar();
        marcaChild.getGridView().clearGrid();
        marcas.forEach((m) -> {
            marcaChild.getGridView().getModel().addRow(new Object[]{m.getId(), m.getNome(),m.getEmpresa()});
        });

    }
    
}
