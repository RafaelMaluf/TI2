package service;

import java.util.Scanner;
import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import dao.TextoDAO;
import model.Texto;
import spark.Request;
import spark.Response;


public class TextoService {
    
	private TextoDAO textoDAO = new TextoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_TITULO = 2;
	private final int FORM_ORDERBY_CONTEUDO = 3;
	
	public TextoService() {
		makeForm();
	}

	//private Connection conexao;
	
	public void makeForm() {
		makeForm(FORM_INSERT, new Texto(), FORM_ORDERBY_TITULO);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Texto(), orderBy);
	}

	
	public void makeForm(int tipo, Texto texto, int orderBy) {
		String nomeArquivo = "lista.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
	
		String lista = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		lista += "\n<tr>\n" +
        		"\t<td><a href=\"/produto/lista/" + FORM_ORDERBY_TITULO + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/produto/lista/" + FORM_ORDERBY_CONTEUDO + "\"><b>Categoria</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"</tr>\n";
		
		List<Texto> textos;
		if (orderBy == FORM_ORDERBY_TITULO) {		textos = textoDAO.getOrderByTitulo();
		} else if (orderBy == FORM_ORDERBY_CONTEUDO) {			textos = textoDAO.getOrderByConteudo();
		} else {											textos = textoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Texto p : textos) {
			bgcolor = (i++ % 2 == 0) ? "#fff3f4" : "#ffffff";
			lista += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getConteudo() + "</td>\n" +
            		  "\t<td>" + p.getTitulo() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/" + p.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n";
		}
		lista += "</table>";		
		form = form.replaceFirst("<LISTAR-PRODUTO>", lista);				
	}
	
	public Object insert(Request request, Response response) {

		String conteudo = request.queryParams("conteudo");
		String titulo = request.queryParams("titulo");

		String resp = "";
		
		Texto texto = new Texto(-1, conteudo, titulo);
		
		if(textoDAO.insert(texto) == true) {
            resp = "Texto (" + titulo + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Texto (" + titulo + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Texto texto = (Texto) textoDAO.get(id);
		
		if (texto != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, texto, FORM_ORDERBY_TITULO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Produto " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Texto texto = (Texto) textoDAO.get(id);
		
		if (texto != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, texto, FORM_ORDERBY_TITULO);
        } else {
            response.status(404); // 404 Not found
            String resp = "Produto " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Texto texto = textoDAO.get(id);
        String resp = "";       

        if (texto != null) {
        	texto.setConteudo(request.queryParams("conteudo"));
        	texto.setTitulo(request.queryParams("titulo"));
        	texto.setDataPublicacao(Date.valueOf(LocalDateTime.now().toLocalDate()));
        	
        	textoDAO.update(texto);
        	response.status(200); // success
            resp = "texto (ID " + texto.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "texto (ID \" + produto.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Texto texto = textoDAO.get(id);
        String resp = "";       

        if (texto != null) {
        	textoDAO.delete(id);
            response.status(200); // success
            resp = "texto (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Produto (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

    public Object getTextoPorTitulo(Request request, Response response) {
        String tituloPesquisa = request.queryParams("titulo"); // Recupera o parâmetro de consulta "nome"
        String list = "";

        List<Texto> textos = textoDAO.getProdutoPorTitulo(tituloPesquisa);

        list += "\n<tr>\n" +
                "\t<td><a href=\"/produto/listar/" + FORM_ORDERBY_TITULO + "\"><b>Nome</b></a></td>\n" +
                "\t<td><a href=\"/produto/listar/" + FORM_ORDERBY_CONTEUDO + "\"><b>Categoria</b></a></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
                "</tr>\n";
        int i = 0;
        String bgcolor = "";
        for (Texto p : textos) {
            bgcolor = (i++ % 2 == 0) ? "#fff3f4" : "#ffffff";
            list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
                      "\t<td>" + p.getConteudo() + "</td>\n" +
                      "\t<td>" + p.getTitulo() + "</td>\n" +
                      "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/" + p.getId() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n";
        }
        list += "</table>";
         return form = form.replaceFirst("<LISTAR-PRODUTO>", list);
    }
}