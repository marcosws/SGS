/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.dao;

import br.senai.sp.model.entity.Compra;
import br.senai.sp.model.service.Mensagens;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CompraDao {
    
    private Connection connection;
    
    public void inserir(Compra compra){
        
        this.connection = new Conexao().getConnection();
        String sql = "INSERT INTO COMPRA (PRECO, DT, QUANTIDADE, TOTAL, ID_PRODUTO) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setDouble(1, compra.getPreco());
            stmt.setDate(2, new Date(compra.getData().getTimeInMillis()));
            stmt.setInt(3, compra.getQuantidade());
            stmt.setInt(4, compra.getTotal());
            stmt.setLong(5, compra.getIdProduto());
            stmt.execute();
            stmt.close();	
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public void alterar(Compra compra){
        
        this.connection = new Conexao().getConnection();
        String sql = "UPDATE COMPRA SET PRECO=?, QUANTIDADE=?, TOTAL=? ,ID_PRODUTO=?  WHERE ID=?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setDouble(1, compra.getPreco());
            stmt.setInt(2, compra.getQuantidade());
            stmt.setInt(3, compra.getTotal());
            stmt.setLong(4, compra.getIdProduto());
            stmt.setLong(5, compra.getId());
            stmt.execute();
            stmt.close();	
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public void excluir(Compra compra){
        
        this.connection = new Conexao().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM COMPRA WHERE ID=?");
            stmt.setLong(1, compra.getId());
            stmt.execute();
            stmt.close();
            this.connection.close();
        }
	catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public Compra consultar(long id){
        
        this.connection = new Conexao().getConnection();
        Compra compra = new Compra();
        try{
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM COMPRA WHERE ID=?"); 
            ptmt.setLong(1,id);
            ResultSet resultSet = ptmt.executeQuery();
            resultSet.next();
            compra.setId(resultSet.getLong("ID"));
            compra.setPreco(resultSet.getDouble("PRECO"));
            Calendar data = Calendar.getInstance();
            data.setTime(resultSet.getDate("DT"));
            compra.setData(data);
            compra.setQuantidade(resultSet.getInt("QUANTIDADE"));
            compra.setTotal(resultSet.getInt("TOTAL"));
            compra.setIdProduto(resultSet.getLong("ID_PRODUTO"));
           
            
            resultSet.close();
            this.connection.close();
            return compra;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
          
    }
    public List<Compra> consultar(){
        
        this.connection = new Conexao().getConnection();
        try{
            List<Compra> compras = new ArrayList<Compra>();
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM COMPRA;");
            ResultSet resultSet = ptmt.executeQuery();
              
            while(resultSet.next()){
                    
                Compra compra = new Compra();  
                compra.setId(resultSet.getLong("ID"));
                compra.setPreco(resultSet.getDouble("PRECO"));
                Calendar data = Calendar.getInstance();
                data.setTime(resultSet.getDate("DT"));
                compra.setData(data);
                compra.setQuantidade(resultSet.getInt("QUANTIDADE"));
                compra.setTotal(resultSet.getInt("TOTAL"));
                compra.setIdProduto(resultSet.getLong("ID_PRODUTO"));
                compras.add(compra);
                    
            }
            this.connection.close();
            return compras;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
        
    }
    
}
