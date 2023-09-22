package dao;

import java.sql.*;
import java.time.LocalDateTime;
import model.Texto;


public class TextoDAO {
	private static Connection conexao;
	
	public TextoDAO() {
		conexao = null;
	}
	
	public static boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "skipp.database.windows.net";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "urlsite" + serverName + ":" + porta + "/" + mydatabase;
		String username = "skippadmin";
		String password = "Skipp@admin";
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
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	public boolean add (Texto texto){
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO texto (id,conteudo,titulo,dataPublicacao)"
						 + "VALUES {" +texto.getId()	+", " +	texto.getConteudo()	
						 + ", " + texto.getTitulo() + ", "+ texto.getDataPublicacao()
                         +"}";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
	return status;
	}
	
	public Texto[] get(int id) {
		Texto[] textos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM login";
			ResultSet rs = st.executeQuery(sql);

			if(rs.next()) {
				rs.last();
				textos = new Texto[rs.getRow()];
				rs.beforeFirst();

				for(int i = 0; rs.next(); i++) {
					Timestamp timestamp = rs.getTimestamp("dataPublicacao");
					LocalDateTime dataPublicacao = timestamp.toLocalDateTime();
					textos[i] = new Texto(rs.getInt("id"), rs.getString("conteudo"),
					rs.getString("titulo"),dataPublicacao);
				}
				st.close();
			}
			 
		}  catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		
		return textos;
	}
	}