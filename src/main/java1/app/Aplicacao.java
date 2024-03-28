package app;

import java.text.ParseException;


import static spark.Spark.*;
import service.UsuarioService;
import service.TextoService;
import service.ComentarioService;

public class Aplicacao {

		private static UsuarioService usuarioService = new UsuarioService();
		private static TextoService textoService = new TextoService();
		private static ComentarioService comentarioService = new ComentarioService();
		
		public static void main(String[] args) throws ParseException {
			
			port(6789);

			
			

	        post("/pesquisa/update/:id", (request, response) -> usuarioService.update(request, response));
			
			
			
		}
		
}