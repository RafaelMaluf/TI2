package app;

import java.text.ParseException;


import dao.UsuarioDAO;
import service.UsuarioService;



public class Aplicacao {
		
		private static UsuarioService usuarioService = new UsuarioService();
		
		public static void main(String[] args) throws ParseException {
			port(5432);
			
			staticFiles.location("/public");
			
			
			
			dao.close();
			
		}
		
}