package model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.sql.Date;


public class Texto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String conteudo;
	private String titulo;
	private boolean favorito;
	private Date dataPublicacao;
	
	public Texto() {
		id = -1;
		conteudo = "conteudo";
		titulo = "titulo";
		
	}
	
	public Texto(int id, String conteudo, String titulo) {
		setId(id);
		setConteudo(conteudo);
		setTitulo(titulo);
		Date data = Date.valueOf(LocalDateTime.now().toLocalDate());
		setDataPublicacao(data);
		setFavorito(false);
	}
	
	 // getters
	
	public int getId() {
		return id;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public Date getDataPublicacao() {
		return dataPublicacao;
	}
	
	public boolean getFavorito() {
		return favorito;
	}
	//setters
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
		
	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	
	@Override
	public String toString() {
		return " Id: " + id + " conteudo: "+ conteudo + " titulo: " + titulo +" data de publicacao: " + dataPublicacao;
	}
	
	
}

