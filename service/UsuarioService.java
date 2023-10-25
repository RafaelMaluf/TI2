package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.io.IOException;

public class UsuarioService {
	
	
		UsuarioDAO dao = new UsuarioDAO(); 
		dao.conectar();
		
		public Object add(Request request, Response response){
			String nome = request.queryParams("nome");
			int idade = Integer.parseInt(request.queryParams("idade"));
			String email = request.queryParams("email");
			String senha = request.queryParams("senha");
			
			int id = UsuarioDAO.getMaxId() + 1;

			Usuario usuario = new Usuario(id,nome,idade,email,senha);

			if(UsuarioDAO.add(login) == true) {
			System.out.println("Inserção com sucesso -> " + login.toString());
			}

			response.status(201);
			return id;
		}

		public Object get(Request request, Response response){
			
			int id = Integer.parseInt(request.params(":id"));

			Login login = (login) LoginDAO.get(id);

			if(login != null) {
			System.out.println("Inserção com sucesso -> " + login.toString());
			return "<login>\n" +
				   "\t<id>" + login.getId() + "</id>\n" +
				   "\t<nome>" + login.getNome() + "</nome>\n" +
				   "\t<idade>" + login.getIdade() + "</idade>\n" +
				   "\t<email>" + login.getEmail() + "</email>\n" +
				   "\t<senha>" + login.getSenha() + "</senha>\n" +
				   "</login>\n";
			}
			else
			response.status(404);
			return "login " + id + " não encontrado";
		}
		
		
		
		
	
}
