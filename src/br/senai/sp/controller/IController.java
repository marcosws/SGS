/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.controller;

/**
 * @author Marcos
 */
public interface IController {
    
    void incluir(Object view);
    void alterar(Object view);
    void excluir(Object view);
    void consultar(Object view);
    
}
