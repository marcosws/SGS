/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.dao;

import br.senai.sp.model.service.CommonService;
import br.senai.sp.model.service.Mensagens;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.sqlite.SQLiteConfig;

/**
 * @author Marcos
 */
public class Conexao {
    
    private Properties connectionProperties = new Properties();
    
    public Connection getConnection(){
        
        CommonService commonService = new CommonService();
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        connectionProperties = config.toProperties();
        String path = System.getProperty("user.dir");
        String partialPath = "/db/";
        //path = path.replace("\\", "/");
        String strConexao = "jdbc:sqlite:" + path + partialPath + commonService.getNameDataBase();
        
        try{	
            return DriverManager.getConnection(strConexao,connectionProperties);	
	}
	catch(SQLException e){
            Mensagens mensagens = new Mensagens();
            mensagens.apresentaMensagem(e);
        }
        return null;
    }
}
