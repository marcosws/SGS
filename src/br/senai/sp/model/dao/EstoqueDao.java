/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.dao;

import br.senai.sp.model.entity.Estoque;
import br.senai.sp.model.service.Mensagens;
import java.sql.Connection;
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
public class EstoqueDao {
    
    private Connection connection;
    
    public List<Estoque> consultar(String departamento){
        
        this.connection = new Conexao().getConnection();
        
        String sql = "SELECT D.NOME AS DEPARTAMENTO, M.EMPRESA, M.NOME AS MARCA, P.NOME AS PRODUTO, C.PRECO, C.DT, C.QUANTIDADE, C.TOTAL AS ESTOQUE\n" +
                     "FROM COMPRA AS C, PRODUTO AS P, MARCA AS M, DEPARTAMENTO AS D \n" +
                     "WHERE P.ID = C.ID_PRODUTO AND M.ID = P.ID_MARCA AND D.ID = P.ID_DEPT AND D.NOME = ?;";
        try{
            List<Estoque> estoques = new ArrayList<Estoque>();
            PreparedStatement ptmt = this.connection.prepareStatement(sql);
            ptmt.setString(1,departamento);
            ResultSet resultSet = ptmt.executeQuery();
              
            while(resultSet.next()){
                    
                Estoque estoque = new Estoque();
                estoque.setDepartamento(resultSet.getString("DEPARTAMENTO"));
                estoque.setEmpresa(resultSet.getString("EMPRESA"));
                estoque.setMarca(resultSet.getString("MARCA"));
                estoque.setProduto(resultSet.getString("PRODUTO"));
                estoque.setPreco(resultSet.getDouble("PRECO"));
                Calendar data = Calendar.getInstance();
                data.setTime(resultSet.getDate("DT"));
                estoque.setData(data);
                estoque.setQuantidade(resultSet.getInt("QUANTIDADE"));
                estoque.setEstoque(resultSet.getInt("ESTOQUE"));
                estoques.add(estoque);
                    
            }
            this.connection.close();
            return estoques;
        }
        catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
    
}
