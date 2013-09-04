package model;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

public class DAO {
	
	// a conexão com o banco de dados
	private Connection connection;
	
	
	public DAO() 
	{
		this.connection = new ConnectionFactory().getConnection();
	
	}
	
	//ADICIONA CONTATO NO BANCO DE DADOS
	public void adicionaContato(Contato contato) {String sql = "insert into Contatos " 
	//+ contato.getNome() + contato.getEmail() + contato.getEndereco() + " values (?,?,?,?)";
	+ "(nome,email,endereco,telefone,empresa,mensagem)" + "values (?,?,?,?,?,?)"; 
	
		try 
		{
			//Preparando para a Inserção de Dados
			Connection con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
					
			// seta os valores
			stmt.setString(1,contato.getNome());
			stmt.setString(2,contato.getEmail());
			stmt.setString(3,contato.getEndereco());
			stmt.setString(4,contato.getTelefone());
			stmt.setString(5,contato.getEmpresa());
			stmt.setString(6,contato.getMensagem());
			
			System.out.println("Dados Gravados com Sucesso!");
			
			//Executa no Banco de Dados
			stmt.execute();
			//Fecha a Conexão
			stmt.close();
			
		
		} 
			
		catch (SQLException e) 
			{
				throw new RuntimeException(e);
			}
		
	}

	//PESQUISA CONTATO NO BANCO DE DADOS
	public void pesquisaContato(Contato contato) 
	{
				try 
				{
					//Preparando para a Inserção de Dados
					Connection con = new ConnectionFactory().getConnection();
					PreparedStatement stmt = con.prepareStatement("select * from Contatos");
					
					//Executa no Banco de Dados
					ResultSet rs = stmt.executeQuery();
					
					//Enquanto...
					while (rs.next()) 
					{
						String nome = rs.getString("nome");
						String email = rs.getString("email");
						String endereco = rs.getString("endereco");
						System.out.println(nome + " :: " + email + "::" + endereco);						
					}
					
					rs.close();
					stmt.close();
					con.close();
				} 
					
				catch (SQLException e) 
					{
						throw new RuntimeException(e);
					}
				
			}
	
	
	//REMOVE CONTATO PASSANDO O CÓDIGO OU ID
	public void removeContato(Contato contato) {
			
			try {
					
					PreparedStatement stmt = connection.prepareStatement("delete from Contatos where Cod=?");
					stmt.setLong(1, contato.getCod());
					stmt.execute();

					stmt.close();
				} 
			
				catch (SQLException e) 
				{
					
					throw new RuntimeException(e);
				}
	

	}

	
	
	//ADICIONA CONTATO NO BANCO DE DADOS
		public void adicionaUsuario(Usuario usuario) {String sql = "insert into Usuarios " 
		//+ contato.getNome() + contato.getEmail() + contato.getEndereco() + " values (?,?,?,?)";
		+ "(nome,senha,cpf)" + "values (?,?,?)"; 
		
			try 
			{
				//Preparando para a Inserção de Dados
				Connection con = new ConnectionFactory().getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				
						
				// seta os valores
				stmt.setString(1,usuario.getNome());
				stmt.setString(2,usuario.getSenha());
				stmt.setString(3,usuario.getCPF());
								
				System.out.println("Dados Gravados com Sucesso!");
				
				//Executa no Banco de Dados
				stmt.execute();
				//Fecha a Conexão
				stmt.close();
				
			
			} 
				
			catch (SQLException e) 
				{
					throw new RuntimeException(e);
				}
			
		}
	
	
	//PESQUISA USUÁRIO NO BANCO DE DADOS
	public void pesquisaUsuario(Usuario usuario) 
	{
				
		try 
		{
			//Preparando para a Inserção de Dados
			Connection con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from Usuarios");
			
			//Executa no Banco de Dados
			ResultSet rs = stmt.executeQuery();
			
			//Enquanto...
			if (rs.next()) 
			{
				String CPF = rs.getString(4);	
				
					if (usuario.getCPF().equals(CPF))
					{	
						String cod = rs.getString(1);
						String nome = rs.getString(2);
						String senha = rs.getString(3);
						
						JOptionPane.showMessageDialog(null, " Usuário Encontrado\n "   
								 							 + "\nCódigo: " + cod  
								 							 + "\nNome : " + nome
								 							 + "\nSenha: " + senha); 
						
						 
					}
					else
					{	
						JOptionPane.showMessageDialog(null, "Este CPF Não Consta No Sistema");
					}
						
					
			}
			
			rs.close();
			stmt.close();
			con.close();
			
			
		} 
			
		catch (SQLException e) 
			{
				throw new RuntimeException(e);
			}
		
				
				
	}
	
	
	
	
	//PESQUISA USUÁRIO NO BANCO DE DADOS
		public boolean Login(Usuario usuario) 
		{
						
					try 
					{
						//Preparando para a Inserção de Dados
						Connection con = new ConnectionFactory().getConnection();
						String sql = "select * from Usuarios where nome = ? and senha = ?";
						//String sql = "select from Usuarios where STRCMP (cpf, '" + usuario.getCPF() + "') = 0;";
						PreparedStatement ps;
						
						ps = con.prepareStatement(sql);
						
						//PreparedStatement stmt = con.prepareStatement("select * from Usuarios where nome = ? and senha = ?");			
						 ps.setString(1, usuario.getNome());  
				         ps.setString(2, usuario.getSenha());  
				        					
						boolean autenticado = false; 
						
						//Executa no Banco de Dados
						ResultSet rs = ps.executeQuery();
						
						
						if (rs.next())
						{
							String loginBanco = rs.getString("nome");  
			                String senhaBanco = rs.getString("senha");  
			                autenticado = true;  
							
						}
						
						
						rs.close();
						ps.close();
						//stmt.close();
						con.close();
						
						return autenticado;  
						

					} 
						
					catch (SQLException e) 
						{
							throw new RuntimeException(e);
						}
					
				}
		
	
	
	
	
	
	
	
	
	
			
	public void removeUsuario(Usuario usuario) {
		
			
		try 
		{
			//Preparando para a Inserção de Dados
			Connection con = new ConnectionFactory().getConnection();
			String sql = "delete from Usuarios where STRCMP (cpf, '" + usuario.getCPF() + "') = 0;";
			PreparedStatement ps;
			
			java.sql.Statement st = con.createStatement();
			st.execute(sql);
			
			con.commit();
			con.close();
			
		} 
		
		catch (SQLException e) 
			{
				throw new RuntimeException(e);
			}	
			

}

	
	
	
	
	
	
	
	
	
	//ADICIONA FUNCIONÁRIO NO BANCO DE DADOS	
	public void adicionaFuncionario(Funcionario funcionario) {String sql = "insert into Funcionarios " +"(nome,senha)" + " values (?,?)";
	try 
	{
		//Preparando para a Inserção de Dados
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = con.prepareStatement(sql);
		
		// seta os valores
		stmt.setString(1,funcionario.getNome());
		stmt.setString(2,funcionario.getSenha());
		
		System.out.println("Dados Gravados com Sucesso!");
		
		//Executa no Banco de Dados
		stmt.execute();
		//Fecha a Conexão
		stmt.close();

	} 
		
	catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		
}
	
	
		//PESQUISA FUNCIONÁRIO NO BANCO DE DADOS
		public boolean pesquisaFuncionario(Funcionario funcionario) 
		{
						
					try 
					{
						//Preparando para a Inserção de Dados
						Connection con = new ConnectionFactory().getConnection();
						String sql = "select * from Funcionarios where nome = ? and senha = ?";
						PreparedStatement ps;
						
						ps = con.prepareStatement(sql);
						
						//PreparedStatement stmt = con.prepareStatement("select * from Funcionarios where nome = ? and senha = ?");			
						 ps.setString(1, funcionario.getNome());  
				         ps.setString(2, funcionario.getSenha());  
						
						boolean autenticado = false; 
						
						//Executa no Banco de Dados
						ResultSet rs = ps.executeQuery();
						
						
						if (rs.next())
						{
							String loginBanco = rs.getString("nome");  
			                String senhaBanco = rs.getString("senha");  
			                autenticado = true;  
							
						}
						
						
						rs.close();
						ps.close();
						con.close();
						
						return autenticado;  
						
						
					} 
						
					catch (SQLException e) 
						{
							throw new RuntimeException(e);
						}
					
				}
}
		