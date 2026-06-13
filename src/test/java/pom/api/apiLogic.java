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
    private Response responseRegistroMedico;
    private Response responsePatchMedico;
    private Response responseDeleteMedico;
    private Response responseDescuentoMedico;
    public static final String DOCTOR_ID_PRUEBA = "5d859e70-f82b-4416-8bbb-8f2ee183fc99";
    public static final String NUEVA_ESPECIALIDAD_PRUEBA = "Cardiología Pediátrica";
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

    public void endpointRegistrarMedico() {
        String step = "Ejecutando request para registrar un nuevo medico";
        log.info(step);
        
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);
        
        // Usamos el token guardado estaticamente en esta clase tras el login del admin
        request.header("Authorization", apiLogic.authorization); 

        // Armamos el mapa con el cuerpo exacto que me pasaste
        Map<String, String> jsonMedico = new HashMap<>();
        jsonMedico.put("nombres", "Gobert");
        jsonMedico.put("apellidos", "González");
        jsonMedico.put("correo", "gobert@example.com");
        jsonMedico.put("telefono", "+584142345679");
        jsonMedico.put("numero_colegiatura", "67890");
        jsonMedico.put("especialidad", "Pediatría2");
        request.body(jsonMedico);

        // Enviamos la petición por POST y guardamos la respuesta
        responseRegistroMedico = request.post("/api/v1/admin/doctors/");
    }

    public void validoRegistroMedicoExitoso() {
        String step = "Validando que el registro del medico haya sido exitoso";
        log.info(step);

        int statusCode = responseRegistroMedico.getStatusCode();

        // Evaluamos e imprimimos el resultado en consola para depuración
        if (statusCode == 201 || statusCode == 200) {
            log.info("El medico se registro con exito. Codigo de estado: " + statusCode);
            log.info("Respuesta del servidor: " + responseRegistroMedico.getBody().asString());
        } else {
            log.error("Error al registrar el medico. Código de estado: " + statusCode);
            log.error("Respuesta del servidor: " + responseRegistroMedico.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 201, statusCode);
    }

    public void endpointActualizarMedicoParcial() {
        String step = "Ejecutando request PATCH para médico ID: " + DOCTOR_ID_PRUEBA + " -> Especialidad: " + NUEVA_ESPECIALIDAD_PRUEBA;
        log.info(step);
        
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);
        
        request.header("Authorization", apiLogic.authorization); 

        // Creamos el cuerpo de la petición usando la constante interna
        Map<String, String> jsonPatch = new HashMap<>();
        jsonPatch.put("especialidad", NUEVA_ESPECIALIDAD_PRUEBA);
        request.body(jsonPatch);

        // Enviamos la petición PATCH usando las variables internas
        responsePatchMedico = request.patch("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
    }

    public void validoActualizacionMedicaExitosa() {
        String step = "Validando que la actualización parcial del médico haya sido exitosa";
        log.info(step);

        int statusCode = responsePatchMedico.getStatusCode();

        if (statusCode == 200 || statusCode == 204) {
            log.info("El médico se actualizó con éxito. Código de estado: " + statusCode);
            log.info("Respuesta del servidor: " + responsePatchMedico.getBody().asString());
        } else {
            log.error("Error al actualizar el médico. Código de estado: " + statusCode);
            log.error("Respuesta del servidor: " + responsePatchMedico.getBody().asString());
        }

        assertEquals("El código de estado no fue el esperado", 200, statusCode);
    }

    public void endpointEliminarMedico() {
        String step = "Ejecutando request DELETE para el borrado lógico del médico ID: " + DOCTOR_ID_PRUEBA;
        log.info(step);
        
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);
        request.header("Authorization", apiLogic.authorization); 
        responseDeleteMedico = request.delete("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
    }

    public void validoEliminacionMedicaExitosa() {
        String step = "Validando que el borrado lógico del médico haya sido exitoso";
        log.info(step);

        int statusCode = responseDeleteMedico.getStatusCode();
        if (statusCode == 200 || statusCode == 204) {
            log.info("El médico fue deshabilitado exitosamente (soft-delete). Código de estado: " + statusCode);
            log.info("Respuesta del servidor: " + responseDeleteMedico.getBody().asString());
        } else {
            log.error("Error al eliminar el médico. Código de estado: " + statusCode);
            log.error("Respuesta del servidor: " + responseDeleteMedico.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 204, statusCode);
    }

    public void endpointGenerarDescuentoMedico() {
        String step = "Ejecutando request POST para generar descuento al médico ID: " + DOCTOR_ID_PRUEBA;
        log.info(step);
        
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        request.contentType(ContentType.JSON);
        request.header("Authorization", apiLogic.authorization); 
        Map<String, Object> jsonDiscount = new HashMap<>();
        jsonDiscount.put("porcentaje", 15);
        request.body(jsonDiscount);
        responseDescuentoMedico = request.post("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA + "/discount");
    }

    public void validoGeneracionDescuentoExitosa() {
        String step = "Validando que la generación del código de descuento haya sido exitosa";
        log.info(step);

        int statusCode = responseDescuentoMedico.getStatusCode();

        if (statusCode == 200 || statusCode == 201) {
            log.info("El código de descuento se generó con éxito. Código de estado: " + statusCode);
            log.info("Respuesta del servidor: " + responseDescuentoMedico.getBody().asString());
        } else {
            log.error("Error al generar el descuento. Código de estado: " + statusCode);
            log.error("Respuesta del servidor: " + responseDescuentoMedico.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 201, statusCode);
    }

}