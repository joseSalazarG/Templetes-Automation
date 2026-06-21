package pom.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;
import java.time.Instant;
import java.time.Duration;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiLogic {

    // Variables compartidas para las pruebas de API
    private static final Logger log = LogManager.getLogger(apiLogic.class);
    public static int statusFlag = 0;
    public static String authorization = ""; // ESTO DEBE IR VACIO PARA QUE SE ASIGNE EL TOKEN CORRECTAMENTE
    private Response response;
    Map<String, String> jsonBody = new HashMap<>();
    // Constantes de prueba
    public static final String DOCTOR_ID_PRUEBA = "ed6cb123-a59e-40fd-b537-27487e51c5bf"; // AQUI SE PEGA LA ID
    private String especialidadAnterior;
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

    private void checkResponseStatus(String mensajeError, int statusEsperado) {
        if (response.getStatusCode() != statusEsperado) {
            throw new RuntimeException(mensajeError + ". Esperado: " + statusEsperado + ". Obtenido: " + response.getStatusCode() + ". Respuesta: " + response.getBody().asString());
        }
    }

    // --- ENDPOINTS ---

    // LOGINS
    public void credencialesUsuarioCliente() {
        String step = "Dadas las credenciales de un usuario cliente";
        log.info(step);
        
        jsonBody.clear();
        jsonBody.put("email", "jose@testing.com");
        jsonBody.put("password", "perencejo");

        response = baseRequest().body(jsonBody).post("/auth/login");

        checkResponseStatus("Error en la autenticación del usuario cliente", 200);

        String token = response.jsonPath().getString("access_token");
        authorization = "Bearer " + token;
    }

    public void credencialesSuperAdmin() {
        String step = "Dadas las credenciales de un administrador";
        log.info(step);

        jsonBody.clear();
        jsonBody.put("email", "soyadmin@admin.com");
        jsonBody.put("password", "perencejo");

        response = baseRequest().body(jsonBody).post("/auth/login");

        checkResponseStatus("Error en la autenticación del usuario cliente", 200);
        
        String token = response.jsonPath().getString("access_token");
        authorization = "Bearer " + token;  
    }

    public void validoAuntenticarmeExitosamente() {
        String step = "Valido que la autenticación fue exitosa";
        log.info(step);

        // postdata: nunca se va a ejecutar si la autenticación falla, porque se lanza una excepción
        assertEquals("El código de estado no es 200", 200, statusFlag);
    }

    // LISTA MEDICOS

    public void solicitarListaMedicosActivos() {
        String step = "Solicito la lista de todos los médicos activos";
        log.info(step);
        
        response = baseRequest().get("/api/v1/admin/doctors/");

        checkResponseStatus("Error al obtener el listado de médicos activos", 200);
    }

    public void validoVerUnicamenteMedicosActivos() {
        log.info("Valido ver unicamente a los medicos activos");

        List<Map<String, Object>> listaDoctores = response.jsonPath().getList("");

        for (Map<String, Object> doctor : listaDoctores) {
             // si el estado del doctor no es activo
            Boolean status = (Boolean) doctor.get("activo");
            if (status != true) {
                throw new AssertionError("Se encontró un médico que no está activo en el listado: " + doctor.get("id") + " - " + doctor.get("nombres") + " " + doctor.get("apellidos"));
            } 
        }   

        assertTrue(true); // llegado a este punto no se encontraron doctores desactivados
    }

    // REGISTRAR

    public void solicitoRegistrarNuevoMedicoConDatosValidos() {
        String step = "Solicito el registro de un nuevo médico con datos válidos";
        log.info(step);

        // para evitarnos datos repetidos calculamos un numero en milisegundos
        Instant base = Instant.parse("2026-01-01T00:00:00Z");        
        String hora = String.valueOf(Duration.between(base, Instant.now()).toMillis());

        jsonBody.clear();
        jsonBody.put("nombres", "Test registro");
        jsonBody.put("apellidos", "Fabian Laura");
        jsonBody.put("correo", "gobert_" + hora + "@example.com");
        jsonBody.put("telefono", "+58" + 1234567890);
        jsonBody.put("numero_colegiatura", "12345678");
        jsonBody.put("especialidad", "Pediatría2");
        response = baseRequest().body(jsonBody).post("/api/v1/admin/doctors/");
        
        checkResponseStatus("Error al registrar el nuevo médico", 201);
        jsonBody.put("fecha_registro", response.jsonPath().getString("fecha_registro"));
    }

    public void validoMedicoRegistradoEnListado() {
        String step = "Valido que el medico registrado se encuentre en el listado";
        log.info(step);

        Map<String, String> registro = new HashMap<>();

        registro.put("nombres", response.jsonPath().getString("nombres"));
        registro.put("apellidos", response.jsonPath().getString("apellidos"));
        registro.put("correo", response.jsonPath().getString("correo"));
        registro.put("telefono", response.jsonPath().getString("telefono"));
        registro.put("numero_colegiatura", response.jsonPath().getString("numero_colegiatura"));
        registro.put("especialidad", response.jsonPath().getString("especialidad"));
        registro.put("fecha_registro", response.jsonPath().getString("fecha_registro"));

        assertEquals("El registro del nuevo médico no coincide con los datos enviados en la solicitud", jsonBody, registro);
    }

    public void solicitarListadoMedicosActivos() {
        String step = "Solicito el listado de médicos activos";
        log.info(step);

        response = baseRequest().get("/api/v1/admin/doctors/");        
        checkResponseStatus("Error al obtener el listado de médicos activos", 200);
    }

    // ACTUALIZAR ESPECIALIDAD

    public void actualizarEspecialidadMedicoSeleccionado() {
        log.info("Actualizo la especialidad del médico seleccionado");

        response = baseRequest().get("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
        checkResponseStatus("Error al obtener la información del médico", 200);

        especialidadAnterior = (String) response.jsonPath().getString("especialidad");
        jsonBody.clear();
        // alternamos la especialidad actual por la siguiente en la lista
        if (especialidadAnterior.equals("Cardiología Pediátrica")) {
            jsonBody.put("especialidad", "Neurología Adultos");
        } else {
            jsonBody.put("especialidad", "Cardiología Pediátrica");
        }
        // y mandamos a actualizar la especialidad
        response = baseRequest().body(jsonBody).patch("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
        checkResponseStatus("Error al actualizar la especialidad del médico", 200);
    }

    public void validarActualizacionMedicoExitosa() {
        log.info("Valido que la actualización del médico fue exitosa");

        String especialidadActual = response.jsonPath().getString("especialidad");

        assertNotEquals("La especialidad no se actualizó ", especialidadAnterior, especialidadActual);
    }

    // ELIMINAR

    public void solicitoElBorradoLogicoMedicoSeleccionado() {
        String step = "Solicito el borrado lógico del médico seleccionado";
        log.info(step);

        response = baseRequest().delete("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
    }

    public void validoDesactivarMedico() {
        String step = "Valido que el medico fue desactivado correctamente";
        log.info(step);
        
        int statusCode = response.getStatusCode();

        assertEquals("El código de estado no fue el esperado.\n" + response.getBody().asString(), 204, statusCode);
    }

    // DESCUENTOS

    public void endpointGenerarDescuentoMedico() {
        String step = "Ejecutando request POST para generar descuento al médico ID: " + DOCTOR_ID_PRUEBA;
        log.info(step);

        Map<String, Object> jsonDiscount = new HashMap<>();
        jsonDiscount.put("porcentaje", 15);

        response = baseRequest().body(jsonDiscount).post("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA + "/discount");
    }

    public void validoGeneracionDescuentoExitosa() {
        String step = "Validando que la generación del código de descuento haya sido exitosa";
        log.info(step);
        
        int statusCode = response.getStatusCode();

        if (statusCode == 200 || statusCode == 201) {
            log.info("El código de descuento se generó con éxito. Respuesta: " + response.getBody().asString());
        } else {
            log.error("Error al generar el descuento. Código: " + statusCode + ". Respuesta: " + response.getBody().asString());
        }
        assertEquals("El código de estado no fue el esperado", 201, statusCode);
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
