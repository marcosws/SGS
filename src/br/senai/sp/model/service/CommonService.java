/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.model.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 *
 * @author Marcos
 */
public class CommonService {
    
    public String getStringData(Calendar data){
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy"); 
        return s.format(data.getTimeInMillis());
    }
    public String getNameDataBase(){
        
        String userPath = System.getProperty("user.dir");
        String partialPath = "/db/";
        Properties properties = new Properties();
	FileInputStream file = null;
	try {
            file = new FileInputStream(userPath + partialPath + "db.properties");
	}
        catch(FileNotFoundException e) {
	    e.printStackTrace();
        }
	try{
	    properties.load(file);
	} 
        catch(IOException e){
            e.printStackTrace();
	}
	return properties.getProperty("database.file");
    }
    
}
