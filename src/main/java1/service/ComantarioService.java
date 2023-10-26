package service;

import dao.ComentarioDAO;

import model.Comentario;
import spark.ModelAndView;
import spark.Spark;
import java.util.HashMap;
import java.util.Map;

import spark.template.velocity.VelocityTemplateEngine;

public class ComentarioService {

    private ComentarioDAO usuarioDAO = new ComentarioDAO();

    public ComentarioService() {
    setupRoutes();
    }
    
    private void setupRoutes() {
    	
    	Spark.port(5432);
        Spark.staticFileLocation("/public");
        

        Spark.get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Comentario[] allUsuario = ComentarioDAO.getComentarios();
            model.put("usuarioList", allUsuario);
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "templates/index.vm")
            );
        });

        Spark.get("/usuario/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params(":id"));
            Usuario usuario = usuarioDAO.get(id);
            model.put("usuario", usuario);
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "templates/detail.vm")
            );
        });

        Spark.post("/usuario", (request, response) -> {
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String senha = request.queryParams("senha");
            if (name != null && !name.isEmpty()) {
                Usuario newUsuario = new Usuario(1,name,email,senha);
                usuarioDAO.insert(newUsuario);
            }
            response.redirect("/");
            return null;
        });

        Spark.post("/pesquisa/:id/update", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            Usuario usuario = usuarioDAO.get(id);
            if (usuario != null) {
                String novaSenha = request.queryParams("novasenha");
                if (novaSenha != null && !novaSenha.isEmpty()) {
                	usuario.setSenha(novaSenha);
                	usuarioDAO.update(usuario);
                }
            }
            response.redirect("/");
            return null;
        });

        Spark.get("/pesquisa/:id/delete", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            Usuario usuario = usuarioDAO.get(id);
            if (usuario != null) {
            	usuarioDAO.delete(id);
            }
            response.redirect("/");
            return null;
        });
    }

    public static void main(String[] args) {
        new UsuarioService();
    }
}
