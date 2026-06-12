package pom.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiLogic {

    // Variables compartidas para las pruebas de API
    public static int statusFlag = 0;
    public static String authorization = "";
    
    // Variables globales para almacenar las respuestas HTTP de los escenarios
    private Response responseMedicos;
    private static final Logger log = LogManager.getLogger(apiLogic.class);

    // FUNCIONES PARA LA AUTOMATIZACION DE PRUEBAS DE LOS ENDPOINTS

    public void endpointLoginUsuario() {
        String step = "Ejecutando request para login de usuario cliente";
        log.info(step);
        
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);
        Map<String, String> jsonCredenciales = new HashMap<>();
        jsonCredenciales.put("email", "jose@testing.com");
        jsonCredenciales.put("password", "perencejo");
        request.body(jsonCredenciales);

        Response response = request.post("/auth/login");
        int statusCode = response.getStatusCode();
        String dataString = null;

        if (statusCode == 200) {
            log.info("La solicitud fue exitosa. Código de estado: " + statusCode);
            String token = response.jsonPath().getString("access_token");
            // CORREGIDO: Guardamos en la variable de esta clase
            apiLogic.authorization = "Bearer " + token; 
            log.info("Token recibido: " + token);
        }  else {
            log.error("La solicitud fallo. Codigo de estado: " + statusCode);
            dataString = response.getBody().asString();
            log.error("Respuesta del servidor: " + dataString);
        }
        statusFlag = statusCode;
    }

    public void validoLoginExitosoEndpoint() {
        String step = "Valido que el login es exitoso";
        log.info(step);

        assertEquals("El código de estado no es 200", 200, statusFlag);
    }

    public void endpointLoginAdmin() {
        String step = "Ejecutando request para login de usuario administrador";
        log.info(step);
        
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);
        Map<String, String> jsonCredenciales = new HashMap<>();
        jsonCredenciales.put("email", "soyadmin@admin.com");
        jsonCredenciales.put("password", "perencejo");
        request.body(jsonCredenciales);
        Response response = request.post("/auth/login");
        int statusCode = response.getStatusCode();
        String dataString = null;

        if (statusCode == 200) {
            log.info("La solicitud fue exitosa. Código de estado: " + statusCode);
            String token = response.jsonPath().getString("access_token");
            // CORREGIDO: Guardamos en la variable de esta clase
            apiLogic.authorization = "Bearer " + token;
            log.info("Token recibido: " + token);
        }  else {
            log.error("La solicitud falló. Código de estado: " + statusCode);
            dataString = response.getBody().asString();
            log.error("Respuesta del servidor: " + dataString);
        }
        statusFlag = statusCode;
    }

    public void endpointListarMedicos() {
        String step = "Ejecutando request para listar médicos activos (Super-Admin)";
        log.info(step);
        
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);
        
        // CORREGIDO: Leemos el token desde esta misma clase
        request.header("Authorization", apiLogic.authorization); 

        // Guardamos el resultado en la variable global
        responseMedicos = request.get("/api/v1/admin/doctors/");
    }

    public void validoListadoMedicosActivos() {
        String step = "Validando que el sistema muestre el listado de médicos activos";
        log.info(step);

        int statusCode = responseMedicos.getStatusCode();

        if (statusCode == 200) {
            log.info("La solicitud fue exitosa. Código de estado: " + statusCode);
            String dataString = responseMedicos.getBody().asString();
            log.info("Lista de médicos recibida: " + dataString);
        } else {
            log.error("La solicitud fallo. Código de estado: " + statusCode);
            log.error("Respuesta del servidor: " + responseMedicos.getBody().asString());
        }

        // Validacion interna para asegurar el exito del test
        org.junit.Assert.assertEquals("El código de estado no fue 200", 200, statusCode);
    }
}