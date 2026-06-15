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
    public static String comandaId = "";
    
    // Variables globales para almacenar las respuestas HTTP de los escenarios
    private Response responseMedicos;
    private Response responseComanda;
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

    public void procesarOrdenMedica() {
        String step = "Ejecutando request para procesar una orden médica";
        log.info(step);

        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("order_type", "pre_order");
        requestBody.put("origin", "medication_tracker");
        requestBody.put("recipe_id", "REC-001");
        requestBody.put("doctor_info", "Dr. Juan Pérez - MPPS 12345");
        requestBody.put("patient_info", "Paciente: María López - CI: 12345678");

        java.util.List<Map<String, Object>> itemsList = new java.util.ArrayList<>();
        Map<String, Object> item = new HashMap<>();
        item.put("sku", "MED-001");
        item.put("quantity", 2);
        item.put("presentation", "tabletas");
        itemsList.add(item);

        requestBody.put("items", itemsList);
        requestBody.put("priority", "normal");

        requestBody.put("idempotency_key", "a1b2c3d4e5f6");

        request.body(requestBody);

        responseComanda = request.post("/comanda/");
        statusFlag = responseComanda.getStatusCode();
    }

    public void validarCreacionComanda() {
        String step = "Validando que la comanda se haya creado de forma exitosa";
        log.info(step);

        int statusCode = responseComanda.getStatusCode();

        if (statusCode == 200) {
            log.info("La comanda fue procesada con éxito. Código de estado: " + statusCode);

            apiLogic.comandaId = responseComanda.jsonPath().getString("comanda_id");
            log.info("ID de Comanda registrado para seguimiento: " + apiLogic.comandaId);

            String statusResponse = responseComanda.jsonPath().getString("status");
            org.junit.Assert.assertEquals("El estado en el body no es 'success'", "success", statusResponse);

        } else {
            log.error("Error al procesar la comanda. Código de estado: " + statusCode);
            log.error("Respuesta del servidor: " + responseComanda.getBody().asString());
        }

        org.junit.Assert.assertEquals("El código de estado esperado no es 200", 200, statusCode);
    }

    public void consultarEstadoComanda(String idComanda) {
        String step = "Ejecutando request para consultar el estado de la comanda: " + idComanda;
        log.info(step);

        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);

        responseComanda = request.get("/comanda/" + idComanda + "/status"); 
        statusFlag = responseComanda.getStatusCode();
    }  

    public void validarCodigoRespuesta(int statusCodeEsperado) {
        String step = "Validando código de respuesta HTTP esperado: " + statusCodeEsperado;
        log.info(step);

        if (statusFlag == statusCodeEsperado) {
            log.info("Validación exitosa. Código de estado recibido: " + statusFlag);
            log.info("Body recibido: " + responseComanda.getBody().asString());
    } else {

        log.error("Validación fallida. Se esperaba " + statusCodeEsperado + " pero se recibió: " + statusFlag);
        log.error("Respuesta del servidor: " + responseComanda.getBody().asString());
    }

    org.junit.Assert.assertEquals("El código de estado de la respuesta no es el esperado", statusCodeEsperado, statusFlag);
    }
}