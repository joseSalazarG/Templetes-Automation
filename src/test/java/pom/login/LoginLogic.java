package pom.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pom.baseUrl.UrlConstant;
import steps.Hooks;
import static org.junit.jupiter.api.Assertions.assertAll;

import static org.junit.Assert.*;

public class LoginLogic extends Hooks {

    LoginPage loginPage = new LoginPage();
    private static final Logger log = LogManager.getLogger(LoginLogic.class);

    public void navegoPaginaWeb() {
        String step = "Navego a la pagina web";
        log.info(step);

        navigateToUrl(UrlConstant.DEV);
    }

    public void ingresoCorreoYContrasena(String email, String password) {
        String step = "Ingreso el correo" + email +" y la contraseña " + password;
        log.info(step);

        write(loginPage.getInputLogin(), email);
        write(loginPage.getInputPassword(), password);
        clickElement(loginPage.getBtnSubmit());
    }

    public void validoLoginExitoso() {
        String step = "Valido que me logueo de forma exitosa";
        log.info(step);

        assertTrue("No se visualiza el mensaje de bienvenida", existsElement(loginPage.getLbBienvenido()));
    }

    public void validoCerrarSesion() {
        String step = "Valido cerrar sesion";
        log.info(step);

        clickElement(loginPage.getBtnPerfil());
        clickElement(loginPage.getBtnCerrarSesion());

        assertTrue("No cerro sesion", existsElement(loginPage.getInputLogin()));
    }
}