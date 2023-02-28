/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.mdi;

import br.senai.sp.view.chid.CompraChild;
import br.senai.sp.view.chid.DepartamentoChild;
import br.senai.sp.view.chid.EstoqueChild;
import br.senai.sp.view.chid.MarcaChild;
import br.senai.sp.view.chid.SaidaChild;
import br.senai.sp.view.chid.ProdutoChild;
import br.senai.sp.view.common.SgsConstantes;
import br.senai.sp.view.common.StatusBar;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class PrincipalMDI extends JFrame implements ActionListener {

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public JMenuBar getMenuSistema() {
        return menuSistema;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    private final JDesktopPane jDesktopPane;
    private final JMenuBar menuSistema;
    private final StatusBar statusBar;
    
    private final JMenu cadastroMenu;
    private final JMenu operacaoMenu;
    private final JMenu consultaMenu;
    private final JMenu sistemaMenu;
    private final JMenu ajudaMenu;
    
    private final JMenuItem marcaMenuItem;
    private final JMenuItem departamentoMenuItem;
    private final JMenuItem produtoMenuItem;
    
    private final JMenuItem compraMenuItem;
    private final JMenuItem saidaMenuItem;
    
    private final JMenuItem estoqueMenuItem;
    private final JMenuItem informacoesMenuItem;
    private final JMenuItem sairMenuItem;
    private final JMenuItem ajudaMenuItem;
    private final JMenuItem sobreMenuItem;
    
    
    public PrincipalMDI() {
        
        this.statusBar = new StatusBar();
        this.menuSistema = new JMenuBar();
        this.jDesktopPane = new JDesktopPane();
       
        cadastroMenu = new JMenu("Cadastro");
        operacaoMenu = new JMenu("Operação");
        consultaMenu = new JMenu("Consulta");
        sistemaMenu = new JMenu("Sistema");
        ajudaMenu = new JMenu("Ajuda");
        
        marcaMenuItem = new JMenuItem("Marca");
        departamentoMenuItem = new JMenuItem("Departamento");
        produtoMenuItem = new JMenuItem("Produto");
        saidaMenuItem = new JMenuItem("Saida");
        compraMenuItem = new JMenuItem("Compra");
        estoqueMenuItem  = new JMenuItem("Estoque");
        informacoesMenuItem = new JMenuItem("Informações");
        sairMenuItem = new JMenuItem("Sair");
        ajudaMenuItem = new JMenuItem("Ajuda");
        sobreMenuItem = new JMenuItem("Sobre");

        cadastroMenu.add(marcaMenuItem);
        cadastroMenu.add(departamentoMenuItem);
        cadastroMenu.addSeparator();
        cadastroMenu.add(produtoMenuItem);
        
        operacaoMenu.add(compraMenuItem);
        operacaoMenu.add(saidaMenuItem);
       
        consultaMenu.add(estoqueMenuItem);
        
        sistemaMenu.add(informacoesMenuItem);
        sistemaMenu.addSeparator();
        sistemaMenu.add(sairMenuItem);
        
        ajudaMenu.add(ajudaMenuItem);
        ajudaMenu.add(sobreMenuItem);
        
        menuSistema.add(cadastroMenu);
        menuSistema.add(operacaoMenu);
        menuSistema.add(consultaMenu);
        menuSistema.add(sistemaMenu);
        menuSistema.add(ajudaMenu);
        
    }
    public void inicializa(){
        this.carregaIcone();
        this.adicionaActionListener();
    }
    private void carregaIcone(){
       ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\img\\sgs16.png");
       this.setIconImage(icon.getImage());
    }
    private String systemInfo(){
	String msg = "Informações do Sistema\n\n" + 
	"\nUsuario do Computador: " + System.getProperty("user.name") +
	"\nDiretório Aplicação: " + System.getProperty("user.dir") + "   " +
	"\n\nSistema Operacional:" +
	"\n  - Nome: " + System.getProperty("os.name") + 
	"\n  - Arquitetura: " + System.getProperty("os.arch") + 
	"\n  - Versão: " + System.getProperty("os.version") +
	"\n\nJava JRE:" +
	"\n  - Diretório: " + System.getProperty("java.home") + 
	"\n  - Versão: " + System.getProperty("java.version") +
	"\n  - Fornecedor: " + System.getProperty("java.vendor") +
	"\n  - URL: " + System.getProperty("java.vendor.url");
	return msg;
    }
    private void adicionaActionListener() {
        
       marcaMenuItem.addActionListener(this);
       departamentoMenuItem.addActionListener(this);
       produtoMenuItem.addActionListener(this);
       
       compraMenuItem.addActionListener(this);
       saidaMenuItem.addActionListener(this);
       
       estoqueMenuItem.addActionListener(this);
       
       informacoesMenuItem.addActionListener(this);
       sairMenuItem.addActionListener(this);
       
       ajudaMenuItem.addActionListener(this);
       sobreMenuItem.addActionListener(this);
       
       
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource().equals(marcaMenuItem)){
            MarcaChild marcaChild = new MarcaChild();
            marcaChild.adicionaActionListener();
            jDesktopPane.add(marcaChild.getFrame());
        }
        else if(evento.getSource().equals(departamentoMenuItem)){
            DepartamentoChild departamentoChild = new DepartamentoChild();
            departamentoChild.adicionaActionListener();
            jDesktopPane.add(departamentoChild.getFrame());
        }
        else if(evento.getSource().equals(produtoMenuItem)){
            ProdutoChild produtoChild = new ProdutoChild();
            produtoChild.adicionaActionListener();
            produtoChild.inicializa();
            jDesktopPane.add(produtoChild.getFrame());
        }
        else if(evento.getSource().equals(compraMenuItem)){
            CompraChild compraChild = new CompraChild();
            compraChild.adicionaActionListener();
            compraChild.inicializa();
            jDesktopPane.add(compraChild.getFrame());
        }
        else if(evento.getSource().equals(saidaMenuItem)){
            SaidaChild saidaChild = new SaidaChild();
            saidaChild.adicionaActionListener();
            saidaChild.inicializa();
            jDesktopPane.add(saidaChild.getFrame());
        }
        else if(evento.getSource().equals(estoqueMenuItem)){
            EstoqueChild estoqueChild = new EstoqueChild();
            estoqueChild.adicionaActionListener();
            estoqueChild.inicializa();
            jDesktopPane.add(estoqueChild.getFrame());
        }
        else if(evento.getSource().equals(informacoesMenuItem)){
            int option = JOptionPane.INFORMATION_MESSAGE + JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(null, this.systemInfo(), "Informações do Sistema", option);
        }
        else if(evento.getSource().equals(sairMenuItem)){
            System.exit(0);
        }
        else if(evento.getSource().equals(ajudaMenuItem)){
            try {
                Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/help/sgs_help.pdf"));
            }
            catch(Exception ex){
                int option = JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION;
                JOptionPane.showMessageDialog(null,"Erro ao executar o arquivo de ajuda.\n" + ex.getMessage(), "Erro",option);
            }
        }
        else if(evento.getSource().equals(sobreMenuItem)){
            int option = JOptionPane.INFORMATION_MESSAGE + JOptionPane.OK_OPTION;
            JOptionPane.showMessageDialog(null, SgsConstantes.NOME_SISTEMA +  "\nVersão: " + SgsConstantes.VERSAO, "Sobre", option);
        }
    }
   
}
