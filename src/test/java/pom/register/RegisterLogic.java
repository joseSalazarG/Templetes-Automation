package pom.register;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import steps.Hooks;
import static org.junit.Assert.*;

public class RegisterLogic extends Hooks {

    RegisterPage registerPage = new RegisterPage();
    private static final Logger log = LogManager.getLogger(RegisterLogic.class);

    public void registroElNombreYLaCorreo(String nombre, String correo) {
        String step = "Registro el usuario " + nombre + " y la correo " + correo;
        log.info(step);

        write(registerPage.getInputNombre(), nombre);
        write(registerPage.getInputCorreo(), correo);

        clickElement(registerPage.getButtonSignup());
    }

    public void validoSeVisualizaElErrorCorreoYaExiste() {
        String step = "Valido se visualiza el error correo ya existe";
        log.info(step);

        assertTrue("No se visualiza el mensaje de error", elementIsDisplayed(registerPage.getLbErrorCorreoExistente()));
    }
}
