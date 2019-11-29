import com.google.gson.Gson;
import freemarker.template.Template;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;
import static spark.Spark.get;

import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setClassForTemplateLoading(Main.class, "/");
        staticFiles.location("/public");
        port(5678);
        get("/index", (request, response) -> {

            Template template = configuration.getTemplate("views/listar.ftl");
            StringWriter writer = new StringWriter();
            template.process(null,writer);

            return  writer;
        });

        get("/crud", (request, response) -> {
            HttpResponse<String> res = Unirest.get("http://localhost:4567/rest/estudiantes/")
                    .header("accept", "application/json")
                    .queryString("apiKey", "123")
                    .asString();
            Estudiante[] estudiantes = new Gson().fromJson(res.getBody(), (Type) Estudiante[].class) ;
            Template template = configuration.getTemplate("views/listar.ftl");
            Map<String, Object> mapList = new HashMap<>();
            StringWriter writer = new StringWriter();
            mapList.put("lista",estudiantes);
            template.process(mapList,writer);
            return  writer;
        });

        get("/crud/:mat", (request, response) -> {
            HttpResponse<String> res = Unirest.get("http://localhost:4567/rest/estudiantes/")
                    .queryString("matricula", request.params("mat"))
                    .asString();
            System.out.println("La respuesta: "+res.getStatus());
            System.out.println("La salida: "+ res.getBody());
            return res.getBody();
        });

        //Create
        get("/create", (request, response) -> {

            Template template = configuration.getTemplate("views/formulario.ftl");
            StringWriter writer = new StringWriter();
            template.process(null,writer);

            return  writer;
        });
        post("/created", (request, response) -> {
            String nombre = request.queryParams("nombre");
            String correo = request.queryParams("correo");
            String carrera = request.queryParams("carrera");

            if(!(nombre.isEmpty() && correo.isEmpty() && carrera.isEmpty())){

                Estudiante estudiante = new Estudiante();
                estudiante.setNombre(nombre);
                estudiante.setCorreo(correo);
                estudiante.setCarrera(carrera);
                HttpResponse respuesta = Unirest.post("http://localhost:4567/rest/estudiantes/")
                        .header("Content-Type", "application/json")
                        .body(estudiante)
                        .asEmpty();
                response.redirect("/crud");
            }
            return null;
        });

    }}
