import com.google.gson.Gson;
import freemarker.template.Template;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Spark;
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
        port(4568);

        //select student // by Kiana
        Spark.get("/estudiante", (request, response) -> {
            HttpResponse<String> res = Unirest.get("http://localhost:4567/rest/estudiantes/{matricula}")
                    .header("accept", "application/json")
                    .queryString("apiKey", "123")
                    .routeParam("matricula", request.queryParams("matricula"))
                    .asString();
            // procesar el json y asignarselo a un hMap
            Estudiante estudiante = new Gson().fromJson(res.getBody(), (Type) Estudiante.class);
            Map<String, Object> est = new HashMap<>();
            StringWriter wEstudiante = new StringWriter();
            est.put("estudiante",estudiante);

            //Call template
            Template tempEstudiante = configuration.getTemplate("views/estudiante.ftl");
            tempEstudiante.process(est,wEstudiante);
            return  wEstudiante;
        });

        //Create student
        Spark.get("/formulario", (request, response) -> {

            //Testing el error del servidor
            HttpResponse<String> respuesta = Unirest.get("http://localhost:4567/rest/estudiantes/")
                    .header("accept", "application/json")
                    .queryString("apiKey","123")
                    .asString();

            //Testing done
            Template tempForm = configuration.getTemplate("views/formulario.ftl");
            StringWriter wForm = new StringWriter();
            tempForm.process(null,wForm);
            return  wForm;
        });

        // listar //by Kiana
        Spark.get("/crud", (request, response) -> {
            //call el server de camacho por el json
            HttpResponse<String> respuesta = Unirest.get("http://localhost:4567/rest/estudiantes/")
                    .header("accept", "application/json")
                    .queryString("apiKey","123")
                    .asString();
            //process el json y asignarselo a un hMap
            Estudiante[] estudianteList = new Gson().fromJson(respuesta.getBody(),(Type) Estudiante[].class);
            Map<String, Object> mapList = new HashMap<>();
            mapList.put("lista", estudianteList);

            //Call la template
            Template temp = configuration.getTemplate("views/listar.ftl");
            StringWriter writer = new StringWriter();
            temp.process(mapList, writer);
            return writer;
        });

        Spark.post("/created", (request, response) -> {
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
