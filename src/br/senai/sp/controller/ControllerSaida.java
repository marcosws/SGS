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
import br.senai.sp.model.dao.SaidaDao;
import br.senai.sp.model.entity.Compra;
import br.senai.sp.model.entity.Produto;
import br.senai.sp.model.entity.Saida;
import br.senai.sp.model.service.CommonService;
import br.senai.sp.model.service.SaidaService;
import br.senai.sp.view.chid.SaidaChild;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ControllerSaida implements IController{

    private SaidaChild saidaChild;
    
    @Override
    public void incluir(Object view) {
        
        saidaChild = (SaidaChild) view;
        SaidaDao saidaDao = new SaidaDao();
        Saida saida = new Saida();
        SaidaService saidaService = new SaidaService();
        CompraDao compraDao = new CompraDao();
        Compra compra = new Compra();
        compra = compraDao.consultar(this.recuperaIdNaComboCompra(saidaChild));
        
        if(saidaService.verificaEstoque(compra.getTotal(),Integer.parseInt(saidaChild.getTxtQuantidade().getText()))){
            Calendar data = Calendar.getInstance();
            saida.setData(data);
            saida.setQuantidade(Integer.parseInt(saidaChild.getTxtQuantidade().getText()));
            saida.setIdCompra(this.recuperaIdNaComboCompra(saidaChild));
            saida.setIdProduto(compra.getIdProduto());
            saidaDao.inserir(saida);
            compra.setTotal(saidaService.atualizaEstoque(compra.getTotal(), Integer.parseInt(saidaChild.getTxtQuantidade().getText())));
            compraDao.alterar(compra);
        }
        
    }

    @Override
    public void alterar(Object view) {
        
        saidaChild = (SaidaChild) view;
        SaidaDao saidaDao = new SaidaDao();
        Saida saida = new Saida();
        SaidaService saidaService = new SaidaService();
        CompraDao compraDao = new CompraDao();
        Compra compra = new Compra();
        compra = compraDao.consultar(this.recuperaIdNaComboCompra(saidaChild));
        
        if(saidaService.verificaAlteracaoEstoque(compra.getQuantidade(),Integer.parseInt(saidaChild.getTxtQuantidade().getText()))){
            
            saida.setId(Long.parseLong(saidaChild.getTxtId().getText()));
            saida.setQuantidade(Integer.parseInt(saidaChild.getTxtQuantidade().getText()));
            saida.setIdCompra(this.recuperaIdNaComboCompra(saidaChild));
            saida.setIdProduto(compra.getIdProduto());
            saidaDao.alterar(saida);
            compra.setTotal(saidaService.atualizaEstoque(compra.getQuantidade(), Integer.parseInt(saidaChild.getTxtQuantidade().getText())));
            compraDao.alterar(compra);
        }
        
    }

    @Override
    public void excluir(Object view) {
        
        SaidaService saidaService = new SaidaService();
        if(saidaService.validaExclusao()){
            
            saidaChild = (SaidaChild) view;
            SaidaDao saidaDao = new SaidaDao();
            Saida saida = new Saida();
            CompraDao compraDao = new CompraDao();
            Compra compra = new Compra();
            saida = saidaDao.consultar(Long.parseLong(saidaChild.getTxtId().getText()));
            saidaDao.excluir(saida);
            compra = compraDao.consultar(saida.getIdCompra());
            compra.setTotal(compra.getQuantidade());
            compraDao.alterar(compra);
        }
        
    }

    @Override
    public void consultar(Object view) {
        
        saidaChild = (SaidaChild) view;
        CommonService commonService = new CommonService();
        SaidaDao saidaDao = new SaidaDao();
        CompraDao compraDao = new CompraDao();
        ProdutoDao produtoDao = new ProdutoDao();
        List<Saida> saidas = saidaDao.consultar();
        saidaChild.getGridView().clearGrid();
        saidas.forEach((s) -> {
                saidaChild.getGridView().getModel().addRow(new Object[]{
                s.getId(), 
                commonService.getStringData(s.getData()),
                s.getQuantidade(),
                compraDao.consultar(s.getIdCompra()).getId() + " | Preço: " +  
                compraDao.consultar(s.getIdCompra()).getPreco(),
                produtoDao.consultar(s.getIdProduto()).getNome()
                });
        });
        
        
    }
    public void carregarCombo(Object view){
        
        saidaChild = (SaidaChild) view;
        CommonService commonService = new CommonService();
        CompraDao compraDao = new CompraDao();
        ProdutoDao produtoDao = new ProdutoDao();
        MarcaDao marcaDao = new MarcaDao();
        DepartamentoDao departamentoDao = new DepartamentoDao();
        List<Compra> compras = compraDao.consultar();
        
        compras.forEach((c) -> {
            saidaChild.getCbCompra().addItem(
                    String.valueOf(
                    c.getId()) + " | Preço: " + 
                    c.getPreco() + " | " + 
                    commonService.getStringData(c.getData()) + " | Quantidade: " + 
                    c.getQuantidade() + " | Estoque: " + 
                    c.getTotal() + " | " + 
                    produtoDao.consultar(c.getIdProduto()).getNome() + " | " + 
                    marcaDao.consultar(produtoDao.consultar(c.getIdProduto()).getIdMarca()).getNome() + " | " + 
                    marcaDao.consultar(produtoDao.consultar(c.getIdProduto()).getIdMarca()).getEmpresa() + " | " + 
                    departamentoDao.consultar(produtoDao.consultar(c.getIdProduto()).getIdDept()).getNome()
            );
        });
    }
    private Long recuperaIdNaComboCompra(SaidaChild saidaChild){
        String strIdCompra = saidaChild.getCbCompra().getModel().getSelectedItem().toString();
        String[] arrCompra =  strIdCompra.split(" | ");
        return Long.parseLong(arrCompra[0]);
    }
}
