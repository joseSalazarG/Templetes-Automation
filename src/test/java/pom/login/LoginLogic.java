package pom.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pom.baseUrl.UrlConstant;
import steps.Hooks;
import static org.junit.Assert.*;

public class LoginLogic extends Hooks {

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