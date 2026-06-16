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
    public static String comandaId = "";
    // Variables globales para almacenar las respuestas HTTP de los escenarios
    private static final Logger log = LogManager.getLogger(apiLogic.class);
    public static int statusFlag = 0;
    public static String authorization = ""; // ESTO DEBE IR VACIO PARA QUE SE ASIGNE EL TOKEN CORRECTAMENTE
    private Response response;
    Map<String, String> jsonBody = new HashMap<>();
    // Constantes de prueba
    public static final String DOCTOR_ID_PRUEBA = "552946a9-1735-44b8-812e-27631c5eb5af"; // AQUI SE PEGA LA ID
    public static final String NUEVA_ESPECIALIDAD_PRUEBA = "Cardiología Pediátrica";
    private static final String BASE_URL = "https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net";

    // FUNCIONES PARA LA AUTOMATIZACION DE PRUEBAS DE LOS ENDPOINTS
    private RequestSpecification baseRequest() {
        RequestSpecification request = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
        
        if (!authorization.isEmpty()) {
            request.header("Authorization", authorization);
        }
        return request;
    }
    
    public void credencialesUsuarioCliente() {
        String step = "Dadas las credenciales de un usuario cliente";
        log.info(step);
        
        jsonBody.clear();
        jsonBody.put("email", "jose@testing.com");
        jsonBody.put("password", "perencejo");

        response = baseRequest().body(jsonBody).post("/auth/login");
        statusFlag = response.getStatusCode();

        if (statusFlag == 200) {
            String token = response.jsonPath().getString("access_token");
            authorization = "Bearer " + token; 
        } else {
            throw new RuntimeException("Autenticación de cliente falló. Código: " + statusFlag + ". Respuesta: " + response.getBody().asString());
        }
    }

    public void credencialesSuperAdmin() {
        String step = "Dadas las credenciales de un administrador";
        log.info(step);

        jsonBody.clear();
        jsonBody.put("email", "soyadmin@admin.com");
        jsonBody.put("password", "perencejo");

        response = baseRequest().body(jsonBody).post("/auth/login");
        statusFlag = response.getStatusCode();

        if (statusFlag == 200) {
            String token = response.jsonPath().getString("access_token");
            authorization = "Bearer " + token;
        } else {
            throw new RuntimeException("Autenticación de super-administrador falló. Código: " + statusFlag + ". Respuesta: " + response.getBody().asString());
        }
    }

    public void validoAuntenticarmeExitosamente() {
        String step = "Valido que la autenticación fue exitosa";
        log.info(step);

        // postdata: nunca se va a ejecutar si la autenticación falla, porque se lanza una excepción
        assertEquals("El código de estado no es 200", 200, statusFlag);
    }

    public void solicitarListaMedicosActivos() {
        String step = "Solicito la lista de todos los médicos activos";
        log.info(step);
        
        response = baseRequest().get("/api/v1/admin/doctors/");
    }

    public void validoVerUnicamenteMedicosActivos() {
        String step = "Valido ver unicamente a los medicos activos";
        log.info(step);

        int statusCode = response.getStatusCode();

        //TODO: en vez de validar solo el codigo de estado, valida que la lista solo tenga médicos activos 
        //TODO: no se, iterala, recorrela o como sea

        assertEquals("El código de estado no fue 200", 200, statusCode);
    }

    public void procesarOrdenMedica() {
        String step = "Ejecutando request para procesar una orden médica";
        log.info(step);

        RequestSpecification request = baseRequest();
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

        response = request.post("/comanda/");
        statusFlag = response.getStatusCode();
    }

    public void validarCreacionComanda() {
        String step = "Validando que la comanda se haya creado de forma exitosa";
        log.info(step);

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            apiLogic.comandaId = response.jsonPath().getString("comanda_id");
            String statusResponse = response.jsonPath().getString("status");
            assertEquals("El estado en el body no es 'success'", "success", statusResponse);
        }

        assertEquals("El código de estado esperado no es 200", 200, statusCode);
    }

    public void consultarEstadoComanda(String idComanda) {
        String step = "Ejecutando request para consultar el estado de la comanda: " + idComanda;
        log.info(step);

        RequestSpecification request = baseRequest();

        response = request.get("/comanda/" + idComanda + "/status"); 
        statusFlag = response.getStatusCode();
    }  

    public void validarCodigoRespuesta(int statusCodeEsperado) {
        String step = "Validando código de respuesta HTTP esperado: " + statusCodeEsperado;
        log.info(step);

        assertEquals("El código de estado de la respuesta no es el esperado\n" + response.getBody().asString(), statusCodeEsperado, statusFlag);
    }
}