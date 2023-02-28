/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.dao;

import br.senai.sp.model.entity.Saida;
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
public class SaidaDao {

    private Connection connection;
    
    public void inserir(Saida saida){
        
        this.connection = new Conexao().getConnection();
        String sql = "INSERT INTO SAIDA (DT, QUANTIDADE, ID_COMPRA, ID_PRODUTO) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setDate(1, new Date(saida.getData().getTimeInMillis()));
            stmt.setInt(2, saida.getQuantidade());
            stmt.setLong(3, saida.getIdCompra());
            stmt.setLong(4, saida.getIdProduto());
            stmt.execute();
            stmt.close();	
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public void alterar(Saida saida){
        
        this.connection = new Conexao().getConnection();
        String sql = "UPDATE SAIDA SET QUANTIDADE=?, ID_COMPRA=?, ID_PRODUTO=? WHERE ID=?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setInt(1, saida.getQuantidade());
            stmt.setLong(2, saida.getIdCompra());
            stmt.setLong(3, saida.getIdProduto());
            stmt.setLong(4, saida.getId());
            stmt.execute();
            stmt.close();	
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
    }
    public void excluir(Saida saida){
                this.connection = new Conexao().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM SAIDA WHERE ID=?");
            stmt.setLong(1, saida.getId());
            stmt.execute();
            stmt.close();
            this.connection.close();
        }
	catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
    }
    public Saida consultar(long id){
        
        this.connection = new Conexao().getConnection();
        Saida saida = new Saida();
        try{
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM SAIDA WHERE ID=?"); 
            ptmt.setLong(1,id);
            ResultSet resultSet = ptmt.executeQuery();
            resultSet.next();
            saida.setId(resultSet.getLong("ID"));
            Calendar data = Calendar.getInstance();
            data.setTime(resultSet.getDate("DT"));
            saida.setData(data);
            saida.setQuantidade(resultSet.getInt("QUANTIDADE"));
            saida.setIdCompra(resultSet.getLong("ID_COMPRA"));
            saida.setIdProduto(resultSet.getLong("ID_PRODUTO"));
            
            resultSet.close();
            this.connection.close();
            return saida;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
        
    }
    public List<Saida> consultar(){
        
        this.connection = new Conexao().getConnection();
        try{
            List<Saida> saidas = new ArrayList<Saida>();
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM SAIDA;");
            ResultSet resultSet = ptmt.executeQuery();
              
            while(resultSet.next()){
                    
                Saida saida = new Saida();  
                saida.setId(resultSet.getLong("ID"));
                Calendar data = Calendar.getInstance();
                data.setTime(resultSet.getDate("DT"));
                saida.setData(data);
                saida.setIdCompra(resultSet.getLong("ID_COMPRA"));
                saida.setQuantidade(resultSet.getInt("QUANTIDADE"));
                saida.setIdProduto(resultSet.getLong("ID_PRODUTO"));
                saidas.add(saida);
                    
            }
            this.connection.close();
            return saidas;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
        
    }
    
}
