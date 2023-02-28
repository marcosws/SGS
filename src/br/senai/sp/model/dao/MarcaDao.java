/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.dao;

import br.senai.sp.model.entity.Marca;
import br.senai.sp.model.service.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Marcos
 */
public class MarcaDao {
    
    private Connection connection;

    public void inserir(Marca marca){
        
        this.connection = new Conexao().getConnection();
        String sql = "INSERT INTO MARCA (NOME, EMPRESA) VALUES (?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setString(1, marca.getNome());
            stmt.setString(2, marca.getEmpresa());
            stmt.execute();
            stmt.close();	
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public void alterar(Marca marca){
        
        this.connection = new Conexao().getConnection();
        String sql = "UPDATE MARCA SET NOME=?, EMPRESA=? WHERE ID=?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setString(1, marca.getNome());
            stmt.setString(2, marca.getEmpresa());
            stmt.setLong(3, marca.getId());
            stmt.execute();
            stmt.close();	
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public void excluir(Marca marca){
        
        this.connection = new Conexao().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM MARCA WHERE ID=?");
            stmt.setLong(1, marca.getId());
            stmt.execute();
            stmt.close();
            this.connection.close();
        }
	catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
        
    }
    public Marca consultar(long id){
        
        this.connection = new Conexao().getConnection();
        Marca marca = new Marca();
        try{
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM MARCA WHERE ID=?"); 
            ptmt.setLong(1,id);
            ResultSet resultSet = ptmt.executeQuery();
            resultSet.next();
            marca.setId(resultSet.getLong("ID"));
            marca.setNome(resultSet.getString("NOME"));
            marca.setEmpresa(resultSet.getString("EMPRESA"));
            resultSet.close();
            this.connection.close();
            return marca;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
    public List<Marca> consultar(){
        
        this.connection = new Conexao().getConnection();
        try{
            List<Marca> marcas = new ArrayList<Marca>();
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM MARCA;");
            ResultSet resultSet = ptmt.executeQuery();
            
            while(resultSet.next()){
                
                Marca marca = new Marca();
                marca.setId(resultSet.getLong("ID"));
                marca.setNome(resultSet.getString("NOME"));
                marca.setEmpresa(resultSet.getString("EMPRESA"));
                marcas.add(marca);
                    
            }
            this.connection.close();
            return marcas;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
    
}
