package app;

import java.text.ParseException;


import static spark.Spark.*;

import spark.Request;
import spark.Response;
import dao.UsuarioDAO;
import service.UsuarioService;
import service.TextoService;
import service.ComentarioService;

public class Aplicacao {
		private static UsuarioDAO usuarioDAO = new UsuarioDAO();
		private static UsuarioService usuarioService = new UsuarioService();
		private static TextoService textoService = new TextoService();
		private static ComentarioService comentarioService = new ComentarioService();
		
		public static void main(String[] args) throws ParseException {
			
			port(5432);
			staticFiles.location("/public");
			
			

	        post("/pesquisa/update/:id", (request, response) -> usuarioService.update(request, response));
			
			
			
		}
		
}