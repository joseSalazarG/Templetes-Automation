package pom.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pom.baseUrl.UrlConstant;
import steps.Hooks;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginLogic extends Hooks {
    public static int statusFlag = 0;
    public static String authorization = "";

    LoginPage loginPage = new LoginPage();
    private static final Logger log = LogManager.getLogger(LoginLogic.class);

    public void navegoPaginaWeb() {
        String step = "Navego a la pagina web";
        log.info(step);

        navigateToUrl(UrlConstant.DEV);
    }

    public void navegoLoginCliente() {
        String step = "Navego a la pagina de login del cliente";
        log.info(step);

        clickElement(loginPage.getBtnLogin());
    }

    public void ingresoElUsuarioYLaContrasena(String usuario, String password) {
        String step = "Ingreso el usuario " + usuario + " y la contraseña " + password;
        log.info(step);

        write(loginPage.getInputEmail(), usuario);
        write(loginPage.getInputPassword(), password);
        clickElement(loginPage.getBtnSubmit());
    }

    public void validoLoginExitoso() {
        String step = "Valido que me logueo de forma exitosa";
        log.info(step);

        assertTrue("No se visualiza el botón de cerrar sesión", elementIsDisplayed(loginPage.getBtnLogOut()));
    }

    public void hagoClickEnElBotonDeLogin() {
        String step = "Hago click en el boton de login";
        log.info(step);

        clickElement(loginPage.getBtnLogin());
    }

    public void guardarCookies() {
        String step = "Guardo las cookies de la sesion";
        log.info(step);

        captureCookies();
    }

    public void cargar_cookies() {
        String step = "Cargo las cookies de la sesion";
        log.info(step);

        loadCookies();
        refreshPage();
    }

    public void hagoClickEnElBotonDeCerrarSesion() {
        String step = "Hago click en el boton de cerrar sesion";
        log.info(step);

        clickElement(loginPage.getBtnLogOut());
    }

    public void validoCierreSesionExitoso() {
        String step = "Valido que cierro sesion de forma exitosa";
        log.info(step);

        assertTrue("No se visualiza el botón de login", elementIsDisplayed(loginPage.getBtnLogin()));
    }

    public void hagoClickEnElCarrito() {
        String step = "Hago click en el carrito";
        log.info(step);

        clickElement(loginPage.getBtnCarrito());
    }

    public void validoElCarritoEstaVacio() {
        String step = "Valido que el carrito esta vacio";
        log.info(step);

        assertTrue("No se encuentra el mensaje 'Carrito vacio'", elementIsDisplayed(loginPage.getLbCarritoVacio()));
    }

    public void validarSeVisualizaUnMensajeDeErrorDeCredenciales() {
        String step = "Valido que se visualiza un mensaje de error de credenciales";
        log.info(step);

        assertTrue("No se visualiza el label de error", elementIsDisplayed(loginPage.getLbErrorCredenciales()));
    }



// FUNCIONES PARA LA AUTOMATIZACION DE PRUEBAS DE LOS ENDPOINTS






public void endpointLogin() {
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
            LoginLogic.authorization = "Bearer " + token;
            log.info("Token recibido: " + token);
        }  else {
            log.error("La solicitud falló. Código de estado: " + statusCode);
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

    public void endpointSuperAdmin(){
        String step = "Ejecutando request para login de usuario superadmin";
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
            LoginLogic.authorization = "Bearer " + token;
            log.info("Token recibido: " + token);
        }  else {
            log.error("La solicitud falló. Código de estado: " + statusCode);
            dataString = response.getBody().asString();
            log.error("Respuesta del servidor: " + dataString);
        }
        statusFlag = statusCode;
    }

    public void endpointObtenerInformacionSuperAdminConTokenCliente() {
        String step = "Ejecutando request para obtener información de Super Admin usando token de Cliente";
        log.info(step);

        RequestSpecification request = RestAssured.given();
        request.baseUri("https://apiecommerce-gdchbuc5dsemf0et.westus3-01.azurewebsites.net");
        
        String tokenLimpio = LoginLogic.authorization.replace("Bearer ", "");
        request.header("Authorization", "Bearer " + tokenLimpio);
        request.contentType(ContentType.JSON);

        Response response = request.get("/api/v1/admin/doctors/");
        int statusCode = response.getStatusCode();
        String dataString = response.getBody().asString();

        if (statusCode == 200) {
            log.info("La solicitud fue exitosa. Código de estado: " + statusCode);
            log.info("Respuesta: " + dataString);
        } else {
            log.warn("La solicitud falló. Código de estado: " + statusCode);
            log.info("Respuesta del servidor: " + dataString);
        }
        statusFlag = statusCode;
    }

    public void validoAccesoDenegadoPorRol() {
        String step = "Valido que el sistema deniegue el acceso por falta de permisos";
        log.info(step);

        assertEquals("El código de estado no indica acceso denegado por rol", 403, statusFlag);
    }
    
    
    /*
    public void validoMensajeError(String mensaje) {
        String step = "Valido ver el mensaje de error " + mensaje;
        log.info(step);

        String locator = String.format(loginPage.getErrorLogin(), mensaje);

        assertTrue("No se visualiza el mensaje de error esperado: " + mensaje, elementIsDisplayed(locator));
    }

    public void validoAmbosMensajesDeError() {
        String step = "Valido ver ambos mensajes de error";
        log.info(step);

        assertAll(
                () -> assertTrue("No se visualiza el mensaje de error para el campo usuario", elementIsDisplayed(loginPage.getErrorUsuario())),
                () -> assertTrue("No se visualiza el mensaje de error para el campo contraseña", elementIsDisplayed(loginPage.getErrorPassword()))
        );
    }
    */
}