package pom.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
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
    private String nuevoMedicoEmail;
    Map<String, String> jsonBody = new HashMap<>();
    // Constantes de prueba
    public static final String DOCTOR_ID_PRUEBA = "552946a9-1735-44b8-812e-27631c5eb5af"; // AQUI SE PEGA LA ID
    public static final String NUEVA_ESPECIALIDAD_PRUEBA = "Cardiología Pediátrica";
    private static final String[] ESPECIALIDADES = {"Cardiología Pediátrica", "Neurología Adultos"};
    private String especialidadAnterior;
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
        log.info("Valido ver unicamente a los medicos activos");
        assertEquals(200, response.getStatusCode());

        for (Map<String, Object> d : response.jsonPath().getList(""))
            assertTrue("El medico " + d.get("correo") + " no esta activo",
                       Boolean.TRUE.equals(d.get("activo")));
    }

    // REGISTRAR

    public void solicitoRegistrarNuevoMedicoConDatosValidos() {
        String step = "Solicito el registro de un nuevo médico con datos válidos";
        log.info(step);

        Map<String, String> jsonMedico = new HashMap<>();
        jsonMedico.put("nombres", "Gobert");
        jsonMedico.put("apellidos", "González");
        jsonMedico.put("correo", "gobert1@example.com");
        jsonMedico.put("telefono", "+584142345679");
        jsonMedico.put("numero_colegiatura", "67890");
        jsonMedico.put("especialidad", "Pediatría2");

        response = baseRequest().body(jsonMedico).post("/api/v1/admin/doctors/");

        if (response.getStatusCode() == 201) {
            nuevoMedicoEmail = response.jsonPath().getString("correo");
            if (nuevoMedicoEmail == null) {
                nuevoMedicoEmail = "gobert1@example.com";
            }
            log.info("Médico registrado con email: {}", nuevoMedicoEmail);
        }
    }

    public void validoRegistrarNuevoMedicoAPIExitosamente() {
        String step = "Valido registrar un nuevo médico desde la API exitosamente";
        log.info(step);

        int statusCode = response.getStatusCode();

        assertTrue("El código de estado no fue el esperado "+statusCode, statusCode == 201);
    }

    private Response listResponse;

    public void buscarNuevoMedicoEnListadoActivos() {
        String step = "Busco el nuevo médico registrado en el listado de médicos activos";
        log.info(step);

        listResponse = baseRequest().get("/api/v1/admin/doctors/");
    }

    public void validoMedicoRegistradoEnListado() {
        String step = "Valido que el médico registrado se encuentre en el listado";
        log.info(step);

        int statusCodeRegistro = response.getStatusCode();
        assertEquals("El código de estado del registro no fue 201", 201, statusCodeRegistro);

        int statusCodeLista = listResponse.getStatusCode();
        assertEquals("El código de estado del listado no fue 200", 200, statusCodeLista);

        List<Map<String, Object>> doctores = listResponse.jsonPath().getList("");

        boolean encontrado = false;
        for (Map<String, Object> doctor : doctores) {
            String correo = (String) doctor.get("correo");
            if (nuevoMedicoEmail.equals(correo)) {
                encontrado = true;
                log.info("Médico encontrado en el listado: {}", doctor);
                break;
            }
        }

        assertTrue("El médico con email " + nuevoMedicoEmail + " no fue encontrado en el listado de médicos activos", encontrado);
    }

    // ACTUALIZAR ESPECIALIDAD

    public void actualizarEspecialidadMedicoSeleccionado() {
        log.info("Actualizo la especialidad del médico seleccionado");

        especialidadAnterior = baseRequest()
                .get("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA)
                .jsonPath().getString("especialidad");

        jsonBody.clear();
        jsonBody.put("especialidad",
                ESPECIALIDADES[0].equals(especialidadAnterior) ? ESPECIALIDADES[1] : ESPECIALIDADES[0]);

        response = baseRequest().body(jsonBody).patch("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA);
    }

    public void validarActualizacionMedicoExitosa() {
        log.info("Valido que la actualización del médico fue exitosa");
        assertEquals(200, response.getStatusCode());

        String especialidadActual = baseRequest()
                .get("/api/v1/admin/doctors/" + DOCTOR_ID_PRUEBA)
                .jsonPath().getString("especialidad");

        assertNotNull("Especialidad no debe ser nula", especialidadActual);
        assertNotEquals("La especialidad no se actualizó", especialidadAnterior, especialidadActual);
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