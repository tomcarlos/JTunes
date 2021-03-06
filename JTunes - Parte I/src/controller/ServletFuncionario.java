package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import model.ConnectionFactory;
import model.DAO;
import model.Funcionario;


public class ServletFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html"); //Define o tipo dos dados
		
		String nome = request.getParameter( "usuario" ); 
		String pass = request.getParameter( "senha" ); 
		
		Funcionario func = new Funcionario();
		func.setNome(nome);
		func.setSenha(pass);
		
		DAO bd = new DAO();
		bd.pesquisaFuncionario(func);	
		
		boolean resposta = bd.pesquisaFuncionario(func);  
		  
		if (resposta == true)
		{  
		 JOptionPane.showMessageDialog(null, "Login Realizado com Sucesso. Você Será Redirecionado.");  
		 response.sendRedirect("cadastrar.html");
		  
		}
		else
		{  
		 JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique Login e/ou Senha"); 
		 response.sendRedirect("admin.html");
		 
		}
		
		
		
		

	}
	
	
	
	
	
	


}
