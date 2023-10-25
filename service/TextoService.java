package service;

import dao.TextoDAO;
import model.Texto;

import java.io.IOException;
import java.time.LocalDate;

public class TextoService {
	
	
		TextoDAO dao = new TextoDAO(); 
		dao.conectar();
		
		public Object add(Request request, Response response){
			String conteudo = request.queryParams("conteudo");
			String titulo = request.queryParams("titulo");
			LocalDate dataPublicacao = LocalDate.parse(request.queryParams("dataPublicacao"));
			
			int id = textoDAO.getMaxId() + 1;

			Texto texto = new Texto(id,conteudo,titulo,dataPublicacao);

			if(textoDAO.add(texto) == true) {
			System.out.println("Inserção com sucesso -> " + texto.toString());
			}

			response.status(201);
			return id;
		}

		public Object get(Request request, Response response){
			
			int id = Integer.parseInt(request.params(":id"));

			Texto texto = (Texto) textoDAO.get(id);

			if(texto != null) {
			
			return "<texto>\n" +
				   "\t<id>" + texto.getId() + "</id>\n" +
				   "\t<conteudo>" + texto.getConteudo() + "</conteudo>\n" +
				   "\t<titulo>" + texto.getTitulo() + "</titulo>\n" +
				   "\t<dataPublicacao>" + texto.getdataPublicacao() + "</dataPublicacao>\n" +
				   "</texto>\n";
			}
			else
			response.status(404);
			return "texto " + id + " não encontrado";
		}
		
		
		
		
	
}
