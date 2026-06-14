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

    public static int statusFlag = 0;
    public static String authorization = " "; // ESTO DEBE IR VACIO PARA QUE SE ASIGNE EL TOKEN CORRECTAMENTE EN CADA LOGIN
    private Response response;
    private static final Logger log = LogManager.getLogger(apiLogic.class);

    // Constantes de prueba
    public static final String DOCTOR_ID_PRUEBA = "552946a9-1735-44b8-812e-27631c5eb5af"; // AQUI SE PEGA LA ID
    public static final String NUEVA_ESPECIALIDAD_PRUEBA = "Cardiología Pediátrica";
    private static final String BASE_URL = "https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net";

    private RequestSpecification baseRequest() {
        RequestSpecification request = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
        
        if (!authorization.isEmpty()) {
            request.header("Authorization", authorization);
        }
        return request;
    }

    // --- ENDPOINTS ---

    // LOGINS
    public void endpointLoginUsuario() {
        log.info("Ejecutando request para login de usuario cliente");
        
        Map<String, String> jsonCredenciales = new HashMap<>();
        jsonCredenciales.put("email", "jose@testing.com");
        jsonCredenciales.put("password", "perencejo");

        response = baseRequest().body(jsonCredenciales).post("/auth/login");
        statusFlag = response.getStatusCode();

        if (statusFlag == 200) {
            String token = response.jsonPath().getString("access_token");
            authorization = "Bearer " + token; 
            log.info("Token recibido exitosamente.");
        } else {
            log.error("Login de usuario falló. Código: " + statusFlag + ". Respuesta: " + response.getBody().asString());
        }
    }

    public void endpointLoginAdmin() {
        log.info("Ejecutando request para login de usuario administrador");
        
        Map<String, String> jsonCredenciales = new HashMap<>();
        jsonCredenciales.put("email", "soyadmin@admin.com");
        jsonCredenciales.put("password", "perencejo");

        response = baseRequest().body(jsonCredenciales).post("/auth/login");
        statusFlag = response.getStatusCode();

        if (statusFlag == 200) {
            String token = response.jsonPath().getString("access_token");
            authorization = "Bearer " + token;
            log.info("Token de administrador recibido exitosamente.");
        } else {
            log.error("Login de administrador falló. Código: " + statusFlag + ". Respuesta: " + response.getBody().asString());
        }
    }

    public void validoLoginExitosoEndpoint() {
        log.info("Valido que el login es exitoso");
        assertEquals("El código de estado no es 200", 200, statusFlag);
    }

    // LISTA MEDICOS

    public void endpointListarMedicos() {
        log.info("Ejecutando request para listar médicos activos (Super-Admin)");
        response = baseRequest().get("/api/v1/admin/doctors/");
    }

    public void validoListadoMedicosActivos() {
        log.info("Validando que el sistema muestre el listado de médicos activos");
        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            log.info("Lista de médicos recibida: " + response.getBody().asString());
        } else {
            log.error("Error al listar médicos. Código: " + statusCode + ". Respuesta: " + response.getBody().asString());
        }
        assertEquals("El código de estado no fue 200", 200, statusCode);
    }

    // REGISTRAR

    public void endpointRegistrarMedico() {
        log.info("Ejecutando request para registrar un nuevo medico");
        
        Map<String, String> jsonMedico = new HashMap<>();
        jsonMedico.put("nombres", "Gobert");
        jsonMedico.put("apellidos", "González");
        jsonMedico.put("correo", "gobert@example.com");
        jsonMedico.put("telefono", "+584142345679");
        jsonMedico.put("numero_colegiatura", "67890");
        jsonMedico.put("especialidad", "Pediatría2");

        response = baseRequest().body(jsonMedico).post("/api/v1/admin/doctors/");
    }

    public void validoRegistroMedicoExitoso() {
        log.info("Validando que el registro del medico haya sido exitoso");
        int statusCode = response.getStatusCode();

        if (statusCode == 201 || statusCode == 200) {
            log.info("El medico se registro con exito. Respuesta: " + response.getBody().asString());
        } else {
            log.error("Error al registrar el medico. Código: " + statusCode + ". Respuesta: " + response.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 201, statusCode);
    }

    // ACTUALIZAR ESPECIALIDAD

    public void endpointActualizarMedico() {
        log.info("Ejecutando request PATCH para médico ID: " + DOCTOR_ID_PRUEBA + " -> Especialidad: " + NUEVA_ESPECIALIDAD_PRUEBA);
        
        Map<String, String> jsonPatch = new HashMap<>();
        jsonPatch.put("especialidad", NUEVA_ESPECIALIDAD_PRUEBA);

        response = baseRequest().body(jsonPatch).patch("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
    }

    public void validoActualizacionMedicaExitosa() {
        log.info("Validando que la actualización parcial del médico haya sido exitosa");
        int statusCode = response.getStatusCode();

        if (statusCode == 200 || statusCode == 204) {
            log.info("El médico se actualizó con éxito. Respuesta: " + response.getBody().asString());
        } else {
            log.error("Error al actualizar el médico. Código: " + statusCode + ". Respuesta: " + response.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 200, statusCode);
    }

    // ELIMINAR

    public void endpointEliminarMedico() {
        log.info("Ejecutando request DELETE para el borrado lógico del médico ID: " + DOCTOR_ID_PRUEBA);
        response = baseRequest().delete("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
    }

    public void validoEliminacionMedicaExitosa() {
        log.info("Validando que el borrado lógico del médico haya sido exitoso");
        int statusCode = response.getStatusCode();

        if (statusCode == 200 || statusCode == 204) {
            log.info("El médico fue deshabilitado exitosamente (soft-delete).");
        } else {
            log.error("Error al eliminar el médico. Código: " + statusCode + ". Respuesta: " + response.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 204, statusCode);
    }

    // DESCUENTOS

    public void endpointGenerarDescuentoMedico() {
        log.info("Ejecutando request POST para generar descuento al médico ID: " + DOCTOR_ID_PRUEBA);
        
        Map<String, Object> jsonDiscount = new HashMap<>();
        jsonDiscount.put("porcentaje", 15);

        response = baseRequest().body(jsonDiscount).post("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA + "/discount");
    }

    public void validoGeneracionDescuentoExitosa() {
        log.info("Validando que la generación del código de descuento haya sido exitosa");
        int statusCode = response.getStatusCode();

        if (statusCode == 200 || statusCode == 201) {
            log.info("El código de descuento se generó con éxito. Respuesta: " + response.getBody().asString());
        } else {
            log.error("Error al generar el descuento. Código: " + statusCode + ". Respuesta: " + response.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 201, statusCode);
    }
}