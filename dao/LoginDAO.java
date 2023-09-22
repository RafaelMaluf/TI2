package dao;

import java.sql.*;




import model.Login;


public class LoginDAO {
	private static Connection conexao;
	
	public LoginDAO() {
		conexao = null;
	}
	
	public static boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "skippuc.postgres.database.azure.com";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "urlsite" + serverName + ":" + porta + "/" + mydatabase;
		String username = "skippgolpes@skippuc";
		String password = "btd6@btd6";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o Postgres!");
		} catch (ClassNotFoundException e) {
			System.out.println("Conexão NÃO efetuada com o Postgres -- Driver não encontrado --");
		} catch (SQLException e) {
			System.out.println("Conexão NÃO efetuada com o Postgres -- "+ e.getMessage());
			
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e){
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean add (Login login){
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO login (id, nome, idade, email, senha) "
						+ "VALUES {" +login.getId()	+", " +	login.getNome()	
						+ ", " + login.getIdade() + ", "+ login.getEmail()
					 	+ ", " + login.getSenha() +"};";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
	return status;
	}
	
	public Login[] get(int id) {
		Login[] logins = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM login";
			ResultSet rs = st.executeQuery(sql);

			if(rs.next()) {
				rs.last();
				logins = new Login[rs.getRow()];
				rs.beforeFirst();

				for(int i = 0; rs.next(); i++) {
					logins[i] = new Login(rs.getInt("id"), rs.getString("nome"),
					rs.getInt("idade"), rs.getString("email"), rs.getString("senha"));
				}
				st.close();
			}
			 
		}  catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		
		return logins;
	}
	}