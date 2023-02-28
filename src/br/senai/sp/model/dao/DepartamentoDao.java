/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.dao;

import br.senai.sp.model.entity.Departamento;
import br.senai.sp.model.service.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcos
 */
public class DepartamentoDao {
    
    private Connection connection;

    public void inserir(Departamento departamento){
        
        this.connection = new Conexao().getConnection();
        String sql = "INSERT INTO DEPARTAMENTO (NOME) VALUES (?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setString(1, departamento.getNome());
            stmt.execute();
            stmt.close();
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
    }
    public void alterar(Departamento departamento){
        
        this.connection = new Conexao().getConnection();
        String sql = "UPDATE DEPARTAMENTO SET NOME=? WHERE ID=?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);	
            stmt.setString(1, departamento.getNome());
            stmt.setLong(2, departamento.getId());
            stmt.execute();
            stmt.close();
            this.connection.close();
	}
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
    }
    public void excluir(Departamento departamento){
        
        this.connection = new Conexao().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM DEPARTAMENTO WHERE ID=?");
            stmt.setLong(1, departamento.getId());
            stmt.execute();
            stmt.close();
            this.connection.close();
        }
	catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
	}
    }
    public Departamento consultar(long id){
        
        this.connection = new Conexao().getConnection();
        Departamento departamento = new Departamento();
        try{
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE ID = ?"); 
            ptmt.setLong(1,id);
            ResultSet resultSet = ptmt.executeQuery();
            
            resultSet.next();  
            departamento.setId(resultSet.getLong("ID"));
            departamento.setNome(resultSet.getString("NOME"));
            this.connection.close();
            return departamento;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
    public List<Departamento> consultar(){
    
        this.connection = new Conexao().getConnection();
        try{
            List<Departamento> departamentos = new ArrayList<Departamento>();
            PreparedStatement ptmt = this.connection.prepareStatement("SELECT * FROM DEPARTAMENTO;");
            ResultSet resultSet = ptmt.executeQuery();
            
            
            while(resultSet.next()){
            
                Departamento departamento = new Departamento();
                departamento.setId(resultSet.getLong("ID"));
                departamento.setNome(resultSet.getString("NOME"));
                departamentos.add(departamento);
                    
            }
            this.connection.close();
            return departamentos;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
    
}
