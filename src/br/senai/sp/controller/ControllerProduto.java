/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.controller;

import br.senai.sp.model.dao.DepartamentoDao;
import br.senai.sp.model.dao.MarcaDao;
import br.senai.sp.model.dao.ProdutoDao;
import br.senai.sp.model.entity.Departamento;
import br.senai.sp.model.entity.Marca;
import br.senai.sp.model.entity.Produto;
import br.senai.sp.view.chid.ProdutoChild;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ControllerProduto implements IController{

    private ProdutoChild produtoChild;
    
    @Override
    public void incluir(Object view) {
        
        produtoChild = (ProdutoChild) view;
        long idMarca = this.recuperaIdMarca(produtoChild);
        long idDepartamento = this.recuperaIdDepartamento(produtoChild);
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = new Produto();
        produto.setNome(produtoChild.getTxtNome().getText());
        produto.setIdMarca(idMarca);
        produto.setIdDept(idDepartamento);
        produtoDao.inserir(produto);
    }

    @Override
    public void alterar(Object view) {
        
        produtoChild = (ProdutoChild) view;
        long idMarca = this.recuperaIdMarca(produtoChild);
        long idDepartamento = this.recuperaIdDepartamento(produtoChild);
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = new Produto();
        produto.setId(Long.parseLong(produtoChild.getTxtId().getText()));
        produto.setNome(produtoChild.getTxtNome().getText());
        produto.setIdMarca(idMarca);
        produto.setIdDept(idDepartamento);
        produtoDao.alterar(produto);
        
    }

    @Override
    public void excluir(Object view) {
        
        produtoChild = (ProdutoChild) view;
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = new Produto();
        produto.setId(Long.parseLong(produtoChild.getTxtId().getText()));
        produtoDao.excluir(produto); 
        
    }

    @Override
    public void consultar(Object view) {
        
        produtoChild = (ProdutoChild) view;
        ProdutoDao produtoDao = new ProdutoDao();
        MarcaDao marcaDao = new MarcaDao();
        DepartamentoDao departamentoDao = new DepartamentoDao();
        List<Produto> produtos = produtoDao.consultar();
        produtoChild.getGridView().clearGrid();
        produtos.forEach((p) -> {
            produtoChild.getGridView().getModel().addRow(new Object[]{
                p.getId(), 
                p.getNome(),
                marcaDao.consultar(p.getIdMarca()).getNome(),
                marcaDao.consultar(p.getIdMarca()).getEmpresa(),
                departamentoDao.consultar(p.getIdDept()).getNome()});
        });
        
    }
    public void carregarCombos(Object view){
        
        produtoChild = (ProdutoChild) view;
        MarcaDao marcaDao = new MarcaDao();
        DepartamentoDao departamentoDao = new DepartamentoDao();
        List<Marca> marcas = marcaDao.consultar();
        List<Departamento> departamentos = departamentoDao.consultar();
        marcas.forEach((m) -> {
            produtoChild.getCbMarca().addItem(m.getNome());
        });
        departamentos.forEach((d) -> {
            produtoChild.getCbDepartamento().addItem(d.getNome());
        });
    }
    private Long recuperaIdMarca(ProdutoChild produtoChild){
        
        MarcaDao marcaDao = new MarcaDao();
        String strMarca = produtoChild.getCbMarca().getModel().getSelectedItem().toString();
        List<Marca> marcas = marcaDao.consultar();
        for(Marca m: marcas){
            if(m.getNome().equals(strMarca)){
                return m.getId();
            }
        }
        return 0L;
    }
    private Long recuperaIdDepartamento(ProdutoChild produtoChild){
        
        DepartamentoDao departamentoDao = new DepartamentoDao();
        String strDepartamento = produtoChild.getCbDepartamento().getModel().getSelectedItem().toString();
        List<Departamento> departamentos = departamentoDao.consultar();
        for(Departamento d: departamentos){
            if(d.getNome().equals(strDepartamento)){
                return d.getId();
            }
        }
        return 0L;
    }
}
