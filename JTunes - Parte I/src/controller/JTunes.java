package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.Funcionario;


public class JTunes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html"); //Define o tipo dos dados
		
		String arquivo = request.getParameter( "/home/vanderson/" ); 
		
		File file = new File(arquivo);
	   	
	    DAO dao = new DAO();
		dao.insertFile(file);
		
		
		
	}
	

	
	

}
