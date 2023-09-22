package app;

import dao.LoginDAO;

import service.TextoService;
import service.LoginService;

public class Aplicacao {
		
		private static LoginService loginService = new LoginService();
		
		public static void main(String[] args) {
			
			add("/login", (request, response) -> loginService.add(request, response));
			get("/login", (request, response) -> loginService.get(request, response));
			add("/texto", (request, response) -> textoService.add(request, response));
			get("/texto", (request, response) -> textoService.get(request, response));
		}
		
}