package model;

import java.io.Serializable;


public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int id_Texto;
	private String conteudo;
	
	public Comentario() {
        id = 0;
		id_Texto = -1;
		conteudo = "conteudo";
		
	}
	
	public Comentario(int id, int id_Texto, String conteudo) {
		setId(id);
        setId_Texto(id_Texto);
		setConteudo(conteudo);
		
	}
	
	 // getters
	public int getId() {
		return id;
	}

	public int getId_Texto() {
		return id_Texto;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	
	//setters
	
	public void setId(int id) {
		this.id = id;
	}
	
    public void setId_Texto(int id) {
		this.id_Texto = id;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
	@Override
	public String toString() {
		return " Id: " + id + " Id_Texto: " + id_Texto + " conteudo: " + conteudo;
	}
	
	
}


