package model;

import java.io.Serializable;
import java.text.ParseException;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String email;
	private String senha;
	
	public Usuario() {
		id = -1;
		nome = "nome";
		email = "email";
		senha = "senha";
		
	}
	
	public Usuario(int id, String nome, String email, String senha) throws ParseException {
        
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	 // getters
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	//setters
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
		
		
	public void setEmail(String email) {
		this.email = email;
	}
		
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return " Id: " + id + " Nome: "+ nome	+" Email: " + email +" Senha: " + senha;
	}
	
	
}

