package dao;

import model.Comentario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO extends DAO {	
	public ComentarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public int getMaxId() {
        int maxId = -1; // Valor padrão caso não haja registros na tabela

        String query = "SELECT MAX(id) FROM comentario";

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

	
	public Comentario get(int id) {
		Comentario comentario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM comentario WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 comentario = new Comentario(rs.getInt("id"), rs.getInt("id_texto"), rs.getString("conteudo"));
	                				   
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return comentario;
	}
	
	
	public boolean insert(Comentario comentario) {
	    boolean status = false;
	    try {
	        String sql = "INSERT INTO comentario (id, nome, idade, email, Senha) "
	                   + "VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        comentario.setId(getMaxId() + 1);
				
	        st.setInt(1, comentario.getId());  // Use setInt para o campo ID
	        st.setInt(2, comentario.getId_Texto());
	        st.setString(3, comentario.getConteudo());
	        
	        st.executeUpdate();
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}
	
	public boolean update(Comentario comentario) {
		boolean status = false;
		try {  
			String sql = "UPDATE comentario SET conteudo = '" + comentario.getConteudo() + "', "
					   + " WHERE id = " + comentario.getId();

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
			st.executeUpdate("DELETE FROM comentario WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

		public List<Comentario> get() {
		return get("");
	}

	
	public List<Comentario> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Comentario> getOrderById_texto() {
		return get("id_texto");
	}
	
	
	private List<Comentario> get(String orderBy) {
		List<Comentario> comentarios = new ArrayList<Comentario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Comentario p = new Comentario(rs.getInt("id"), rs.getInt("id_Texto"),  rs.getString("descricao"));
	            comentarios.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return comentarios;
	}
}
