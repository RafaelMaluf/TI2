package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO extends DAO {	
	public UsuarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
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
	        	 usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
									   rs.getString("senha"));
	                				   
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public Usuario[] getUsuarios() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario";
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){  
	        	rs.next();
	        	usuarios = new Usuario[rs.getRow()];
	        	rs.beforeFirst();
	        	
	        	for(int i = 0; rs.next(); i++) {
	        		usuarios[i] = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
							   rs.getString("senha"));
	        	}
	                				   
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	public boolean insert(Usuario usuario) {
	    boolean status = false;
	    try {
	        String sql = "INSERT INTO usuario (id, nome, email, Senha) "
	                   + "VALUES (?, ?, ?, ?)";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        usuario.setId(getMaxId() + 1);
				
	        st.setInt(1, usuario.getId());  // Use setInt para o campo ID
	        st.setString(2, usuario.getNome());
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
	
	public boolean update(Usuario usuario) {
		boolean status = false;
		try {  
			String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

	        PreparedStatement st = conexao.prepareStatement(sql);
	        st.setString(1, usuario.getNome());   // Define o valor do primeiro marcador de posição como nome
	        st.setString(2, usuario.getEmail());  // Define o valor do segundo marcador de posição como email
	        st.setString(3, usuario.getSenha());  // Define o valor do terceiro marcador de posição como senha
	        st.setInt(4, usuario.getId());        // Define o valor do quarto marcador de posição como id

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
