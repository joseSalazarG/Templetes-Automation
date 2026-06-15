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

    private static final Logger log = LogManager.getLogger(apiLogic.class);
    public static int statusFlag = 0;
    public static String authorization = ""; // ESTO DEBE IR VACIO PARA QUE SE ASIGNE EL TOKEN CORRECTAMENTE
    private Response response;
    Map<String, String> jsonBody = new HashMap<>();
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

    // LISTA MEDICOS

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

    // REGISTRAR

    public void solicitoRegistrarNuevoMedicoConDatosValidos() {
        String step = "Solicito el registro de un nuevo médico con datos válidos";
        log.info(step);

        Map<String, String> jsonMedico = new HashMap<>();
        jsonMedico.put("nombres", "Gobert");
        jsonMedico.put("apellidos", "González");
        jsonMedico.put("correo", "gobert@example.com");
        jsonMedico.put("telefono", "+584142345679");
        jsonMedico.put("numero_colegiatura", "67890");
        jsonMedico.put("especialidad", "Pediatría2");

        response = baseRequest().body(jsonMedico).post("/api/v1/admin/doctors/");
    }

    public void validoRegistrarNuevoMedicoAPIExitosamente() {
        String step = "Valido registrar un nuevo médico desde la API exitosamente";
        log.info(step);

        int statusCode = response.getStatusCode();

        assertTrue("El código de estado no fue el esperado "+statusCode, statusCode == 201);
    }

    // ACTUALIZAR ESPECIALIDAD

    public void actualizarEspecialidadMedicoSeleccionado() {
        String step = "Actualizo la especialidad del médico seleccionado";  
        log.info(step);

        //TODO: consulta primero los datos actuales del medico
        //TODO: puedes tener que si una lista de dos o tres especialidades para alternar entre ellas en cada ejecución
        
        jsonBody.clear();
        jsonBody.put("especialidad", NUEVA_ESPECIALIDAD_PRUEBA);
        baseRequest().body(jsonBody);

        response = baseRequest().patch("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
    }

    public void validarActualizacionMedicoExitosa() {
        String step = "Valido que la actualización del médico fue exitosa";
        log.info(step);

        int statusCode = response.getStatusCode();
        //TODO: en vez de validar solo el código de estado, valida que la especialidad efectivamente se haya modificado
        //TODO: haciendo una comprobacion de antes y despues 

        assertEquals("El código de estado no fue el esperado", 200, statusCode);
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
}