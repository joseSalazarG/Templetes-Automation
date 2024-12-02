package pom.settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pom.baseUrl.UrlConstant;
import pom.login.LoginLogic;
import steps.Hooks;

import static org.junit.Assert.*;

public class SettingsLogic extends Hooks{

    SettingsPage settingsPage = new SettingsPage();

    private static final Logger log = LogManager.getLogger(LoginLogic.class);

    public void IngresoConfiguracion(){
        String step = "Ingreso a configuracion";
        log.info(step);

        waitForElementToBeInvisible(settingsPage.getLoading());
        elementIsDisplayed(settingsPage.getImg());
        clickElementJs(settingsPage.getBtnMenuPrincipal());
        waitForElementToBeVisible(settingsPage.getLbCC200());
        elementIsDisplayed(settingsPage.getBtnConfig());
        clickElement(settingsPage.getBtnConfig());
        waitForElementToBeInvisible(settingsPage.getLoading());
    }

    public void actualizoNombrePerfil2(String nombreActual, String nombreNuevo) {
        String step = "Actualizo el nombre de perfil " + nombreActual + " a " + nombreNuevo;
        log.info(step);

        clickElementJs(settingsPage.getBtnOpcionInfoPerfil());

        String nombre = textFromElementAttribute(settingsPage.getInputNombre(), "value");

        if(nombre.contains(nombreNuevo)){
            
            clearWithBackspace(settingsPage.getInputNombre());
            
            write(settingsPage.getInputNombre(), nombreActual);
        } else {
            
            clearWithBackspace(settingsPage.getInputNombre());
            
            write(settingsPage.getInputNombre(), nombreNuevo);
        }

        clickElementJs(settingsPage.getBtnActualizaNombre());

        aux = nombreNuevo;
        auxTwo = nombreActual;
    }

    public void validarCambiarNombre() {
        String step = "Valido que se cambio el nombre";
        log.info(step);

        boolean alerta = elementIsDisplayed(settingsPage.getAlertaCambioNombre());

        assertTrue("No se visualizo el alerta", alerta);

        clickElementJs(settingsPage.getBtnMenuPrincipal());

        String nombre = textFromElement(settingsPage.getLbMiPerfil());

        boolean nombrePerfil;

        if(nombre.contains(aux)){
            nombrePerfil = elementIsDisplayed(String.format(settingsPage.getLbNombrePerfil() , aux));
        } else {
            nombrePerfil = elementIsDisplayed(String.format(settingsPage.getLbNombrePerfil() , auxTwo));
        }

        assertTrue("No se ve el nombre en la burbuja de perfil", nombrePerfil);

        navigateToUrl(UrlConstant.DEV);
        waitForElementToBeInvisible(settingsPage.getLoading());
        elementIsDisplayed(settingsPage.getImg());
        clickElementJs(settingsPage.getBtnMenuPrincipal());
        waitForElementToBeVisible(settingsPage.getLbCC200());
        elementIsDisplayed(settingsPage.getBtnConfig());
        clickElement(settingsPage.getBtnConfig());
        waitForElementToBeInvisible(settingsPage.getLoading());
    }

    public void actualizoNombrePerfilNumeros(String numeros) {
        String step = "Actualizo el nombre de perfil con numeros " + numeros;
        log.info(step);

        clickElementJs(settingsPage.getBtnOpcionInfoPerfil());
        write(settingsPage.getInputNombre(), numeros);
    }

    public void validoNoPuedeCambiarNombre() {
        String step = "Valido que no se pueda cambiar el nombre";
        log.info(step);

        String mensaje = textFromElement(settingsPage.getLblCaracteresEspeciales());

        assertEquals("No se visualiza el mensaje que no acepta caracteres especiales",
                "No puede contener caracteres especiales", mensaje);
    }

    public void actualizoEmailPerfil(String email) {
        String step = "Actualizo el email de perfil a " + email;
        log.info(step);

        clickElementJs(settingsPage.getBtnOpcionEmail());

        String inputEmail = textFromElementAttribute(settingsPage.getInputEmail(), "value");


        clearWithBackspace(settingsPage.getInputEmail());


        write(settingsPage.getInputEmail(), email);
        clickElementJs(settingsPage.getBtnConfirmarEmail());

        aux = email;
    }

    public void validarCambiarEmail() {
        String step = "Valido que se cambio el email";
        log.info(step);

        boolean alerta = elementIsDisplayed(settingsPage.getAlertaCambioEmail());
        String valorDelAtributo = textFromElementAttribute(settingsPage.getInputEmail(), "value");
        boolean cambio = valorDelAtributo.equals(aux);

        assertTrue("No se visualizo el alerta", alerta);
        assertTrue("Se no cambio el email", cambio);

        clickElementActions(settingsPage.getBtnVolverEmail());
    }

    public void actualizoEmailNoValido(String email) {
        String step = "Actualizo el email con uno no valido " + email;
        log.info(step);

        clickElementJs(settingsPage.getBtnOpcionEmail());
        write(settingsPage.getInputEmail(), email);
    }

    public void validoNoPuedeCambiarEmail() {
        String step = "Valido que no se pueda cambiar el email";
        log.info(step);

        String mensaje = textFromElement(settingsPage.getLblAdvEmailSinEstructura());

        assertEquals("No se visualiza el mensaje digite un correo valido",
                "Digite un correo válido", mensaje);
    }

    public void validoEmailExiste() {
        String step = "Valido que el email ya existe";
        log.info(step);

        String alerta = textFromElement(settingsPage.getLblAdvEmailExiste());

        assertEquals("No se visualiza el alerta", "El correo ya existe", alerta);
    }

    public void actualizoFotoPerfil() {
        String step = "Actualizo la foto de perfil";
        log.info(step);

        elementIsDisplayed(settingsPage.getBtnOpcionFotoPerfil());
        clickElementJs(settingsPage.getBtnOpcionFotoPerfil());
        waitForElementToBeInvisible(settingsPage.getLoading());

        waitForElementSrcToChangeFromTo(settingsPage.getBtnAddFotoPerfil(),
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        aux = textFromElementAttribute(settingsPage.getBtnAddFotoPerfil(), "src");
        System.err.println(aux);

        if(aux.contains("png")) {
            uploadFileClassName("src/test/resources/files/1.jpg", "file_input_img");
        } else {
            uploadFileClassName("src/test/resources/files/ven.png", "file_input_img");
        }

        clickElementJs(settingsPage.getBtnActualizaFotoPerfil());
        waitForElementToBeInvisible(settingsPage.getLoading());
        clickElementJs(settingsPage.getBtnMenuPrincipal());
        clickElement(settingsPage.getBtnInicio());
        waitForElementToBeInvisible(settingsPage.getLoading());
        elementIsDisplayed(settingsPage.getImg());
        clickElementJs(settingsPage.getBtnMenuPrincipal());
        waitForElementToBeVisible(settingsPage.getLbCC200());
        clickElement(settingsPage.getBtnConfig());
        clickElementJs(settingsPage.getBtnOpcionFotoPerfil());
        elementIsDisplayed(settingsPage.getBtnAddFotoPerfil());

        String atributo = textFromElementAttribute(settingsPage.getBtnAddFotoPerfil(), "src");
        System.err.println("--> "+ atributo);

        waitForElementSrcToChangeFromTo(settingsPage.getBtnAddFotoPerfil(),
                atributo,"jpeg", "png");

        auxTwo = textFromElementAttribute(settingsPage.getBtnAddFotoPerfil(), "src");
        System.err.println(auxTwo);
    }

    public void validoCambioFotoPerfil() {
        String step = "Valido que se cambio la foto de perfil";
        log.info(step);

        assertNotEquals("No se cambio la foto de perfil", aux, auxTwo);
    }
}