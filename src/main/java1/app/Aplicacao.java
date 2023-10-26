package app;

import dao.UsuarioDAO;

import service.TextoService;
import service.UsuarioService;

public class Aplicacao {
		
		private static UsuarioService usuarioService = new UsuarioService();
		
		public static void main(String[] args) {
			
			add("/usuario", (request, response) -> usuarioService.add(request, response));
			get("/usuario", (request, response) -> usuarioService.get(request, response));
			add("/texto", (request, response) -> textoService.add(request, response));
			get("/texto", (request, response) -> textoService.get(request, response));
		}
		
}