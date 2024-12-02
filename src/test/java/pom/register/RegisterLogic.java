package pom.register;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import steps.Hooks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterLogic extends Hooks {

    RegisterPage registerPage = new RegisterPage();
    private static final Logger log = LogManager.getLogger(RegisterLogic.class);

    public void ingresoRegistro1x10() {
        String step = "Ingreso a registro 1x10";
        log.info(step);

        clickElement(registerPage.getBtnAbrirMenu());
        clickElement(registerPage.getBtnConferencias());
        waitForElementToBeInvisible(registerPage.getLoading());
    }

    public void validoRegistro() {
        String step = "Valido que estoy en el registro";
        log.info(step);

        String registrar = textFromElement(registerPage.getLbRegistrar());

        assertEquals("Los elementos no son iguales", "Registrar 1x10", registrar);
    }

    public void registroCedula(String cedula) {
        String step = "Registro la cedula " + cedula;
        log.info(step);

        clickElementActions(registerPage.getCboxTipoDeDocumento());
        clickElement(registerPage.getCboxOpcionV());
        write(registerPage.getInputCedula(), cedula);
        clickElement(registerPage.getBtnValidar());
    }

    public void registroTelefono(String telefono) {
        String step = "Registro un telefono " + telefono;
        log.info(step);

        write(registerPage.getInputTelefono(), telefono);
        clickElement(registerPage.getBtnContinuar());
    }

    public void validoVeaMensaje(String mensaje) {
        String step = "Valido que se vea el mensaje " + mensaje;
        log.info(step);

        String validar = textFromElement(String.format(registerPage.getLbMensaje(), mensaje));

        assertEquals("Los elementos no son iguales", mensaje, validar);
    }

    public void eliminoCedula(String cedula) {
        String step = "Elimino la cedula " + cedula;
        log.info(step);

        String eliminar = String.format(registerPage.getBtnEliminar(), cedula);

        scroll(eliminar);
        clickElement(eliminar);
    }

    public void registroTelefonoSinIngresarlo() {
        String step = "Registro un telefono sin ingresarlo";
        log.info(step);

        clickElementActions(registerPage.getCboxTipoDeDocumento());
        clickElement(registerPage.getCboxOpcionV());
        write(registerPage.getInputCedula(), "20036259");
        clickElement(registerPage.getBtnValidar());
        clickElement(registerPage.getBtnContinuar());
    }

    public void registroCedulaSinIngresarTipoDocDni() {
        String step = "Registro la cedula sin ingresar el tipo de documento y dni";
        log.info(step);

        clickElement(registerPage.getBtnValidar());
    }

    public void alcanzoLimiteRegistro() {
        String step = "Alcanzo el limite de registro";
        log.info(step);

        clickElementActions(registerPage.getCboxTipoDeDocumento());
        clickElement(registerPage.getCboxOpcionV());
        write(registerPage.getInputCedula(), "28031558");
        clickElement(registerPage.getBtnValidar());
        write(registerPage.getInputTelefono(), "656565656");
        clickElement(registerPage.getBtnContinuar());
        clickElement(registerPage.getInputCedula());
        write(registerPage.getInputCedula(), "28031559");
        clickElement(registerPage.getBtnValidar());
        write(registerPage.getInputTelefono(), "656565222");
        clickElement(registerPage.getBtnContinuar());
    }

    public void descargoPdf() {
        String step = "Descargo el pdf";
        log.info(step);

        String ventanaActual = getCurrentWindowHandle();

        clickElement(registerPage.getBtnPdf());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switchToDifferentWindow(ventanaActual);
    }

    public void validoVisualizaArchivoPdf() {
        String step = "Valido que se visualiza el archivo pdf";
        log.info(step);

        String currentUrl = getUrlFromPage();
        boolean url = currentUrl.contains("66a3e71b52387abbfc3fd0f0");

        assertTrue("No se visualiza la pagina para descargar el PDF", url);
    }
}