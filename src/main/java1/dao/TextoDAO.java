package dao;

import model.Texto;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TextoDAO extends DAO {	
	public TextoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Texto texto) {
	    boolean status = false;
	    try {
	        String sql = "INSERT INTO texto (id, conteudo, titulo, dataPublicacao, favorito) "
	                   + "VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement st = conexao.prepareStatement(sql);
	        texto.setId(getMaxId() + 1);
	        st.setInt(1, texto.getId());  // Use setInt para o campo ID
	        st.setString(2, texto.getConteudo());
	        st.setString(3, texto.getTitulo());
	        st.setDate(4, texto.getDataPublicacao());
			st.setBoolean(5, texto.getFavorito());
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

        String query = "SELECT MAX(id) FROM texto";

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

	
	public Texto get(int id) {
		Texto texto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM texto WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 texto = new Texto(rs.getInt("id"), rs.getString("conteudo"), rs.getString("titulo"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return texto;
	}
	
	
	public List<Texto> get() {
		return get("");
	}

	
	public List<Texto> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Texto> getOrderByTitulo() {
		return get("titulo");		
	}
	
	
	private List<Texto> get(String orderBy) {
		List<Texto> textos= new ArrayList<Texto>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM texto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Texto p = new Texto(rs.getInt("id"), rs.getString("conteudo"), rs.getString("titulo"));
	            textos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return textos;
	}
	
	
	public boolean update(Texto texto) {
		boolean status = false;
		try {  
			String sql = "UPDATE texto SET conteudo = '" + texto.getConteudo() + "', "
					   + "titulo = " + texto.getTitulo() + ", " 
					   + "dataPublicacao = ?"
					   + "WHERE id = " + texto.getId();
					   
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setDate(1, Date.valueOf(LocalDateTime.now().toLocalDate()));
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
			st.executeUpdate("DELETE FROM texto WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}