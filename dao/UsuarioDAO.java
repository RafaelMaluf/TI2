package dao;

import model.Usuario;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO {	
	public UsuarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Usuario usuario) {
	    boolean status = false;
	    try {
	        String sql = "INSERT INTO usuario (id, nome, idade, email, Senha) "
	                   + "VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        usuario.setId(getMaxId() + 1);
				
	        st.setInt(1, usuario.getId());  // Use setInt para o campo ID
	        st.setString(2, usuario.getNome());
			st.setDate(3, usuario.getIdade());
	        st.setString(3, usuario.getEmail());
	        st.setString(4, usuario.getSenha());
	        st.executeUpdate();
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}
	
	public int getMaxId() {
        int maxId = -1; // Valor padrão caso não haja registros na tabela

        String query = "SELECT MAX(id) FROM usuario";

        try (PreparedStatement statement = conexao.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                maxId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Trate ou registre exceções, se necessário
        }

        return maxId;
    }

	
	public Usuario get(int id) {
		Usuario usuario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), 
				 					   rs.getDate("idade"), rs.getString("email"),
									   rs.getString("senha"));
	                				   
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Usuario> get() {
		return get("");
	}

	
	public List<Usuario> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Usuario> getOrderByDescricao() {
		return get("descricao");		
	}
	
	
	public List<Usuario> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Usuario> get(String orderBy) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Usuario p = new Usuario(rs.getInt("id"), rs.getString("nome"), 
	        							rs.getDate("idade"), rs.getString("email"),
	        							rs.getString("senha"));
	            usuarios.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET nome = '" + usuario.getNome() + "', "
					   + "idade = " + usuario.getIdade() + ", " 
					   + "email = " + usuario.getEmail() + ","
					   + "senha = " + usuario.getSenha() + ","
					   + " WHERE id = " + usuario.getId();

			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
