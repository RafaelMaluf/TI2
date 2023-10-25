package model;

import java.io.Serializable;


public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id_Comentario;
	private int id_Texto;
	private String conteudo;
	
	public Comentario() {
        id_Comentario = 0;
		id_Texto = -1;
		conteudo = "conteudo";
		
	}
	
	public Comentario(int id_Texto, String conteudo) {
		setId_Comentario(id_Comentario);
        setId_Texto(id_Texto);
		setConteudo(conteudo);
		
	}
	
	 // getters
	public int getId_Comentario() {
		return id_Comentario;
	}

	public int getId_Texto() {
		return id_Texto;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	
	//setters
	
	public void setId_Comentario(int id) {
		this.id_Comentario = id;
	}
	
    public void setId_Texto(int id) {
		this.id_Texto = id;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
	@Override
	public String toString() {
		return " Id_Comentario: " + id_Comentario + " Id_Texto: " + id_Texto + " conteudo: " + conteudo;
	}
	
	
}


