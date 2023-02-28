/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.dao;

import br.senai.sp.model.entity.Produto;
import br.senai.sp.model.service.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ProdutoDao {
    
    private Connection connection;
  
    public void inserir(Produto produto){
        
        this.connection = new Conexao().getConnection();
        String sql = "INSERT INTO PRODUTO (NOME, ID_MARCA, ID_DEPT) VALUES (?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setString(1, produto.getNome());
            stmt.setLong(2, produto.getIdMarca());
            stmt.setLong(3, produto.getIdDept());
            stmt.execute();
            stmt.close();	
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public void alterar(Produto produto){
        
        this.connection = new Conexao().getConnection();
        String sql = "UPDATE PRODUTO SET NOME=?, ID_MARCA=?, ID_DEPT=? WHERE ID=?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setString(1, produto.getNome());
            stmt.setLong(2, produto.getIdMarca());
            stmt.setLong(3, produto.getIdDept());
            stmt.setLong(4, produto.getId());
            stmt.execute();
            stmt.close();
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public void excluir(Produto produto){
        this.connection = new Conexao().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID=?");
            stmt.setLong(1, produto.getId());
            stmt.execute();
            stmt.close();
            this.connection.close();
        }
	catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
    }
    public Produto consultar(long id){
        
        this.connection = new Conexao().getConnection();
        Produto produto = new Produto();
        try{
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM PRODUTO WHERE ID = ?"); 
            ptmt.setString(1,Long.toString(id));
            ResultSet resultSet = ptmt.executeQuery();
                
            resultSet.next();  
            produto.setId(resultSet.getLong("ID"));
            produto.setNome(resultSet.getString("NOME"));
            produto.setIdMarca(resultSet.getLong("ID_MARCA"));
            produto.setIdDept(resultSet.getLong("ID_DEPT"));
            this.connection.close();
            return produto;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
    public List<Produto> consultar(){
        
        this.connection = new Conexao().getConnection();
        try{
            List<Produto> produtos = new ArrayList<Produto>();
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM PRODUTO;");
            ResultSet resultSet = ptmt.executeQuery();
            
            while(resultSet.next()){
            
                Produto produto = new Produto();
                produto.setId(resultSet.getLong("ID"));
                produto.setNome(resultSet.getString("NOME"));
                produto.setIdMarca(resultSet.getLong("ID_MARCA"));
                produto.setIdDept(resultSet.getLong("ID_DEPT"));
                produtos.add(produto);
                    
            }
            this.connection.close();
            return produtos;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
    
}
