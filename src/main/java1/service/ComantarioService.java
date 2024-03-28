package service;

import dao.ComentarioDAO;

import model.Comentario;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

class ComentarioService {

    private ComentarioDAO usuarioDAO = new ComentarioDAO();
    private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_CATEGORIA = 3;

    public ComentarioService() {
    makeForm();
    }
    
    public void makeForm() {
        
    }

}
