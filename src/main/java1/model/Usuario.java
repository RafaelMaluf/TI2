package model;

import java.io.Serializable;
import java.sql.Date;




public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private Date idade;
	private String email;
	private String senha;
	
	public Usuario() {
		id = -1;
		nome = "nome";
		idade = Date.valueOf("9999-99-99");
		email = "email";
		senha = "senha";
		
	}
	
	public Usuario(int id, String nome, Date idade, String email, String senha) {
		setId(id);
		setNome(nome);
		setIdade(idade);
		setEmail(email);
		setSenha(senha);
	}
	
	 // getters
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Date getIdade() {
		return idade;
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
		
	public void setIdade(Date idade) {
		this.idade = idade;
	}
		
	public void setEmail(String email) {
		this.email = email;
	}
		
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return " Id: " + id + " Nome: "+ nome	+ "  Idade: " + idade +" Email: " + email +" Senha: " + senha;
	}
	
	
}

