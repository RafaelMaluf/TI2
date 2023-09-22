package model;

import java.io.Serializable;


public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private int idade;
	private String email;
	private String senha;
	
	public Login() {
		id = -1;
		nome = "nome";
		idade = -1;
		email = "email";
		senha = "senha";
		
	}
	
	public Login(int id, String nome, int idade, String email, String senha) {
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
	
	public int getIdade() {
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
		
	public void setIdade(int idade) {
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

