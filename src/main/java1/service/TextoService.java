package service;


import dao.TextoDAO;

import model.Texto;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextoService {

    private TextoDAO TextoDAO = new TextoDAO();

    public TextoService() {
        setupRoutes();
    }

    private void setupRoutes() {
        Spark.staticFileLocation("/public");
        Spark.port(8080);

        Spark.get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Texto> allTexto = TextoDAO.getAll();
            model.put("textoList", allTexto);
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "templates/index.vm")
            );
        });

        Spark.get("/texto/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params(":id"));
            Texto texto = TextoDAO.get(id);
            model.put("texto", texto);
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "templates/detail.vm")
            );
        });

        Spark.post("/pesquisa", (request, response) -> {
            String name = request.queryParams("name");
            if (name != null && !name.isEmpty()) {
                Pesquisa newPesquisa = new Pesquisa(name);
                pesquisaDAO.insert(newPesquisa);
            }
            response.redirect("/");
            return null;
        });

        Spark.post("/pesquisa/:id/update", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            Pesquisa pesquisa = pesquisaDAO.get(id);
            if (pesquisa != null) {
                String newName = request.queryParams("newName");
                if (newName != null && !newName.isEmpty()) {
                    pesquisa.setName(newName);
                    pesquisaDAO.update(pesquisa);
                }
            }
            response.redirect("/");
            return null;
        });

        Spark.get("/pesquisa/:id/delete", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            Pesquisa pesquisa = pesquisaDAO.get(id);
            if (pesquisa != null) {
                pesquisaDAO.delete(id);
            }
            response.redirect("/");
            return null;
        });
    }

    public static void main(String[] args) {
        new PesquisaService();
    }
}
