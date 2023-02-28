/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.controller;

import br.senai.sp.model.dao.CompraDao;
import br.senai.sp.model.dao.DepartamentoDao;
import br.senai.sp.model.dao.MarcaDao;
import br.senai.sp.model.dao.ProdutoDao;
import br.senai.sp.model.entity.Compra;
import br.senai.sp.model.entity.Produto;
import br.senai.sp.model.service.CommonService;
import br.senai.sp.view.chid.CompraChild;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ControllerCompra implements IController{

    private CompraChild compraChild;
    
    @Override
    public void incluir(Object view) {
        
        compraChild = (CompraChild) view;
        CompraDao compraDao = new CompraDao();
        Compra compra = new Compra();
        compra.setPreco(Double.parseDouble(compraChild.getTxtPreco().getText()));
        Calendar date = Calendar.getInstance();
        compra.setData(date);
        compra.setQuantidade(Integer.parseInt(compraChild.getTxtQuantidade().getText()));
        compra.setTotal(compra.getQuantidade());
        compra.setIdProduto(this.recuperaIdNaComboProduto(compraChild));
        compraDao.inserir(compra);
        
    }

    @Override
    public void alterar(Object view) {
        
        compraChild = (CompraChild) view;
        CompraDao compraDao = new CompraDao();
        Compra compra = new Compra();
        compra.setId(Long.parseLong(compraChild.getTxtId().getText()));
        compra.setPreco(Double.parseDouble(compraChild.getTxtPreco().getText()));
        compra.setQuantidade(Integer.parseInt(compraChild.getTxtQuantidade().getText()));
        compra.setTotal(compra.getQuantidade());
        compra.setIdProduto(this.recuperaIdNaComboProduto(compraChild));
        compraDao.alterar(compra);
        
    }

    @Override
    public void excluir(Object view) {
        
        compraChild = (CompraChild) view;
        CompraDao compraDao = new CompraDao();
        Compra compra = new Compra();
        compra.setId(Long.parseLong(compraChild.getTxtId().getText()));
        compraDao.excluir(compra); 
        
    }

    @Override
    public void consultar(Object view) {
       
        compraChild = (CompraChild) view;
        CommonService commonService = new CommonService();
        CompraDao compraDao = new CompraDao();
        ProdutoDao produtoDao = new ProdutoDao();
        List<Compra> compras = compraDao.consultar();
        compraChild.getGridView().clearGrid();
        compras.forEach((c) -> {
                compraChild.getGridView().getModel().addRow(new Object[]{
                c.getId(), 
                commonService.getStringData(c.getData()),
                c.getPreco(),
                c.getQuantidade(),
                c.getTotal(),
                produtoDao.consultar(c.getIdProduto()).getId() + " | " +  
                produtoDao.consultar(c.getIdProduto()).getNome()
                });
        });
        
    }
    
    public void carregarCombo(Object view){
        
        compraChild = (CompraChild) view;
        ProdutoDao produtoDao = new ProdutoDao();
        List<Produto> produtos = produtoDao.consultar();
        MarcaDao marcaDao = new MarcaDao();
        DepartamentoDao departamentoDao = new DepartamentoDao();
        produtos.forEach((p) -> {
            compraChild.getCbProduto().addItem(
                    String.valueOf(p.getId()) + " | " + 
                    p.getNome() + " | " + 
                    marcaDao.consultar(p.getIdMarca()).getNome() + " | " + 
                    marcaDao.consultar(p.getIdMarca()).getEmpresa() + " | " + 
                    departamentoDao.consultar(p.getIdDept()).getNome()
            );
        });
    }
    private Long recuperaIdNaComboProduto(CompraChild compraChild){
        String strIdProduto = compraChild.getCbProduto().getModel().getSelectedItem().toString();
        String[] arrProduto = strIdProduto.split(" | ");
        return Long.parseLong(arrProduto[0]);
    }
    
}
