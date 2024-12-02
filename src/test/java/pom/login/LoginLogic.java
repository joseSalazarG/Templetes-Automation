package pom.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import pom.baseUrl.UrlConstant;
import steps.Hooks;

import java.util.List;

import static org.junit.Assert.*;

public class LoginLogic extends Hooks {

    LoginPage loginPage = new LoginPage();
    private static final Logger log = LogManager.getLogger(LoginLogic.class);

    public void navegoPaginaWebCC200() {
        String step = "Navego a la pagina web de CC200";
        log.info(step);

        navigateToUrl(UrlConstant.DEV);
    }

    public void ingresoCodigoAreaNumeroTelefono(String area, String numero) {
        String step = "Ingreso el codigo de area " + area + " y el numero de telefono " + numero;
        log.info(step);

        clickElement(loginPage.getBtnPais());
        write(loginPage.getInputPais(), area);
        clickElement(loginPage.getBtnUnitedStates());
        write(loginPage.getInputTelefono(), numero);
        clickElement(loginPage.getBtnEnviarSMS());
    }

   public void ingresoCodigoOtp(String otp) {
        String step = "Ingreso el codigo de OTP " + otp;
        log.info(step);

        write(loginPage.getInputCodigo(), otp);
        clickElement(loginPage.getBtnIniciarSesion());
    }

    public void validoLogueoFormaExitosa() {
        String step = "Valido que me logueo de forma exitosa";
        log.info(step);

        assertTrue("No me pude loguear de forma exitosa",
                elementIsDisplayed(loginPage.getBtnNuevoGrupo()));
    }

    public void validoVisualiceMensajeError(String mensaje) {
        String step = "Valido que se visualiza el mensaje de error " + mensaje;
        log.info(step);

        switch (mensaje) {
            case "Este número de teléfono no está registrado como usuario" -> {

                assertEquals("No se visualiza el mensaje de Este número de teléfono no está registrado como usuario", mensaje,
                        textFromElement(loginPage.getLbTlfNoRegistrado())); }

            case "Seleccione su país e introduzca un número de teléfono válido para continuar" -> {

                assertEquals("No se visualiza el mensaje de Introduzca un número de teléfono y seleccione su país para continuar", mensaje,
                        textFromElement(loginPage.getPopUpMensaje())); }

        }
    }

    public void validoVisualiceMensajeErrorOTP(String mensaje) {
        String step = "Valido que se visualice el mensaje de error de OTP " + mensaje;
        log.info(step);

        assertEquals("No se visualiza el mensaje de codigo SMS invalido", mensaje,
                textFromElement(loginPage.getLbMensaje()));
    }

    public void cierroSesion() {
        String step = "Cierro la sesion";
        log.info(step);

        elementIsDisplayed(loginPage.getImg());
        clickElement(loginPage.getBtnPerfil());
        elementIsDisplayed(loginPage.getLbCC200Web());
        elementIsDisplayed(loginPage.getBtnCerraSesion());
        clickElement(loginPage.getBtnCerraSesion());
    }

    public void validoCerroSesion() {
        String step = "Valido que se cerro la sesion";
        log.info(step);

        assertTrue("No me cerro la sesion",
                elementIsDisplayed(loginPage.getBtnIniciarSesion()));
    }
}