package pom.group;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import steps.ApiHooks;
import steps.Hooks;

import java.util.List;

import static org.junit.Assert.*;

public class GroupLogic extends Hooks{

    GroupPage groupPage = new GroupPage();
    ApiHooks apiHooks = new ApiHooks();

    private static final Logger log = LogManager.getLogger(GroupLogic.class);

    public void creoGrupo(String nombre) {
        String step = "Creo el grupo " + nombre;
        log.info(step);

        clickElement(groupPage.getBtnNuevogrupo2());
        clickElement(groupPage.getAbrirNuevoGrupo());
        write(groupPage.getIngresarNombreGrupo(), nombre);
        write(groupPage.getIngresarDescripcionGrupo(), "Algo interesante");
        clickElement(groupPage.getCheckBoxPrimContac());
       // clickElement(groupPage.getCheckBoxSegContac());
        elementIsDisplayed(groupPage.getLbMiembro());
        uploadFileClassName("src/test/resources/files/ven.png", "file_input_img");
        waitForElementSrcToChangeFromTo(groupPage.getBtnFoto(),
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");
        clickElement(groupPage.getBtnCreargrupo());
        elementIsDisplayed(groupPage.getInputMensaje());

        aux = nombre;
    }

    public void validoCreoGrupo() {
        String step = "Valido se creo el grupo";
        log.info(step);

        boolean inputMensajeDisplayed = elementIsDisplayed(groupPage.getInputMensaje());

        assertTrue("No se muestra ingresar mensaje", inputMensajeDisplayed);
    }

    public void eliminoGrupo(String phone) {
        String step = "Elimino el grupo con el numero " + phone;
        log.info(step);

        apiHooks.deleteGroup(aux, phone);
    }

    public void envioMensajeGrupo() {
        String step = "Envio un mensaje al grupo";
        log.info(step);

        write(groupPage.getInputEscribMensaje(), "Hola como estas?");
        clickElementActions(groupPage.getBtnEnviarMensaje2());
    }

    public void validoEnvioMensajeGrupo() {
        String step = "Valido que se envio el mensaje al grupo";
        log.info(step);

        boolean mensaje = elementIsDisplayed(groupPage.getLbMensajeEnviado());
        assertTrue("No se visualiza el menesaje enviado", mensaje);
    }

    public void veoInformacionGrupo() {
        String step = "Veo la informacion del grupo";
        log.info(step);

        waitForElementSrcToChangeFromTo("//div[@class='profile-chat']//img",
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        clickElement(groupPage.getBtn3PuntosChat());
        elementEnabled(groupPage.getBtnInformacion());
        clickElement(groupPage.getBtnInformacion());
    }

    public void validoPuedoVerInformacionGrupo() {
        String step = "Valido que puedo ver la informacion del grupo";
        log.info(step);

        String prueba = String.format(groupPage.getNombreEnInfo(), aux);
        boolean nombreGrupoDisplayed = elementEnabled(prueba);

        assertTrue("No se muestra el nombre del grupo", nombreGrupoDisplayed);
    }

    public void eliminoGrupoCreado() {
        String step = "Elimino el grupo";
        log.info(step);

        waitForElementSrcToChangeFromTo("//div[@class='profile-chat']//img",
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        clickElement(groupPage.getBtn3PuntosChat());
        elementIsDisplayed(groupPage.getBtnEliminarGrupo());
        clickElement(groupPage.getBtnEliminarGrupo());
        elementIsDisplayed(groupPage.getBtnConfirmarEliminarGrupo());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickElement(groupPage.getBtnConfirmarEliminarGrupo());
        waitForElementToBeInvisible(groupPage.getLoading());
    }

    public void validoEliminoChatLLamado() {
        String step = "Valido que se elimino el grupo";
        log.info(step);

        refreshPage();
        elementEnabled(groupPage.getBtnNuevogrupo());

        auxTwo = String.format(groupPage.getBtnChatCreado(), aux);
        boolean chat = existsElement(auxTwo);

        assertFalse("Se visualiza un chat llamado " + aux, chat);
    }

    public void buscoGrupo(String nombre) {
        String step = "Busco el grupo " + nombre;
        log.info(step);

        write(groupPage.getInputBuscarGrupo(), nombre);

        String chat = String.format(groupPage.getCheckBoxChat(), nombre);

        clickElement(chat);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        auxTwo = nombre;
    }

    public void validoVisualizaGrupo() {
        String step = "Valido que se visualiza el grupo";
        log.info(step);

        String vistaNombreChat = String.format(groupPage.getNombreChat(), auxTwo);
        boolean nombreIsDisplayed = elementIsDisplayed(vistaNombreChat);

        assertTrue("No se encontro el grupo", nombreIsDisplayed);
    }

    public void veoMiembrosGrupo() {
        String step = "Veo los miembros del grupo";
        log.info(step);

        waitForElementSrcToChangeFromTo("//div[@class='profile-chat']//img",
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        clickElement(groupPage.getBtn3PuntosChat());
        clickElement(groupPage.getBtnConfiguracion());
        clickElement(groupPage.getBtnMiembros());
    }

    public void validoAbrirMiembrosGrupo() {
        String step = "Valido abrir miembros del grupo";
        log.info(step);

        boolean lblMiembrosDelGrupo = elementIsDisplayed(groupPage.getLblMiembrosDelGrupo());
        boolean miembroIsDisplayed = elementIsDisplayed(groupPage.getMiembroGrupo());

        assertTrue("No se muestra la etiqueta miembros del grupoo" , lblMiembrosDelGrupo);
        assertTrue("No se muestra miembro añadido al grupo" , miembroIsDisplayed);
    }

    public void cambiarNombreGrupo(String nuevoNombre) {
        String step = "Cambio el nombre a " + nuevoNombre;
        log.info(step);

        waitForElementSrcToChangeFromTo("//div[@class='profile-chat']//img",
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        clickElement(groupPage.getBtn3PuntosChat());
        clickElement(groupPage.getBtnConfiguracion());
        clickElement(groupPage.getBtnNombre());

        try {
            Thread.sleep(650);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        write(groupPage.getInputCambiarNombre(), nuevoNombre);
        clickElement(groupPage.getBtnGuardar());

        aux = nuevoNombre;
    }

    public void validoCambioNombreGrupo() {
        String step = "Valido que se cambio el nombre del grupo";
        log.info(step);

        refreshPage();

        String botonChat = String.format(groupPage.getBtnChatCreado(), aux);
        boolean chat = elementIsDisplayed(botonChat);

        assertTrue("No se encontro el grupo", chat);

        clickElement(botonChat);
    }

    public void actualizoFotoPerfilGrupo() {
        String step = "Actualizo la foto del grupo";
        log.info(step);

        waitForElementSrcToChangeFromTo(groupPage.getBtnNuevaFotoGrupo(),
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        auxTwo = textFromElementAttribute(groupPage.getBtnNuevaFotoGrupo(), "src");

        clickElement(groupPage.getBtn3PuntosChat());
        clickElement(groupPage.getBtnConfiguracion());
        clickElement(groupPage.getBtnGrupoFoto());

        uploadFileClassName("src/test/resources/files/1.jpg", "file_input_img");

        clickElement(groupPage.getBtnActFotoGrupo());
        waitForElementToBeInvisible(groupPage.getLoading());
        waitForElementSrcToChangeFromTo(groupPage.getBtnNuevaFotoGrupo(),
                auxTwo, "jpeg", "png");

        auxThree = textFromElementAttribute(groupPage.getBtnNuevaFotoGrupo(), "src");
    }

    public void validoActualizoFotoGrupo() {
        String step = "Valido que se actualizo la fotol del grupo";
        log.info(step);

        assertNotEquals("No se actualizo la foto del grupo", aux, auxThree);
    }

    public void envioMensaje() {
        String step = "Envio un mensaje";
        log.info(step);

        write(groupPage.getInputEscribMensaje(), "Hola como estas?");
        clickElementActions(groupPage.getBtnEnviarMensaje2());
    }

    public void validoEnvioMensaje() {
        String step = "Valido que se envio el mensaje";
        log.info(step);

        boolean mensaje = elementIsDisplayed(groupPage.getLbMensajeEnviado());
        assertTrue("No se visualiza el menesaje enviado", mensaje);
    }

    public void envioEmoji() {
        String step = "Envio un emoji";
        log.info(step);

        write(groupPage.getInputEscribMensaje(), "Hola como estas?");
        clickElementActions(groupPage.getBtnEnviarMensaje());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickElementActions(groupPage.getBtnEmoji());
        clickElementActions(groupPage.getBtnEmojiSonrisa());
        clickElementActions(groupPage.getBtnEnviarMensaje());
    }

    public void validoEnvioEmoji() {
        String step = "Valido que se envio el emoji";
        log.info(step);

        String emoji = textFromElement(groupPage.getLbEmojiSonrisa());
        assertTrue("No se visualiza el emoji enviado", emoji.contains("😀"));
    }

    public void envioArchivoTipo(String archivo) {
        String step = "Envio un archivo de tipo " + archivo;
        log.info(step);

        switch (archivo) {
            case "foto" -> {
                uploadFileXPath("src/test/resources/files/1.jpg", "//input[@type='file']");
                System.out.println("Foto subida con éxito.");
            }
            case "pdf" -> {
                uploadFileXPath("src/test/resources/files/ven.pdf", "//input[@type='file']");
                System.out.println("PDF subido con éxito.");
            }
            default -> System.out.println("Tipo de archivo no soportado. Por favor, suba un archivo 'foto' o 'pdf'.");
        }

        auxTwo = textFromElementAttribute(groupPage.getInputEscribMensaje(), "value");
        System.err.println(auxTwo);
        clickElementActions(groupPage.getBtnEnviarMensaje());
    }

    public void validoEnvioFotoChat() {
        String step = "Valido que se envio la foto por chat";
        log.info(step);

        auxTwo = textFromElementAttribute(groupPage.getLbArchivoEnviadoImg(), "alt");
        System.err.println(auxTwo);

        assertTrue(auxTwo.contains("1.jpg"));
    }

    public void validoEnvioDocumentoChat() {
        String step = "Valido que se envio un documento por chat";
        log.info(step);

        auxTwo = textFromElement(groupPage.getLbArchivoEnviadoPdf());
        System.err.println(auxTwo);

        assertTrue(auxTwo.contains("ven.pdf"));
    }

    public void eliminoParticipante() {
        String step = "Elimino un participante";
        log.info(step);

        waitForElementSrcToChangeFromTo(groupPage.getBtnNuevaFotoGrupo(),
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        clickElement(groupPage.getBtn3PuntosChat());
        clickElement(groupPage.getBtnConfiguracion());
        elementIsDisplayed(groupPage.getBtnMiembro());
        clickElement(groupPage.getBtnMiembro());

        auxTwo = textFromElement(groupPage.getLbGuardarMiemb());

        clickElementActions(groupPage.getBtnEliminarMiemb());

        acceptAlert();
        acceptAlert();

        elementEnabled(groupPage.getBtn3PuntosChat());
        clickElement(groupPage.getBtn3PuntosChat());
        clickElement(groupPage.getBtnConfiguracion());
        elementIsDisplayed(groupPage.getBtnMiembro());
        clickElement(groupPage.getBtnMiembro());
    }

    public void validoEliminoParticipante() {
        String step = "Valido que se elimino el participante";
        log.info(step);

        List<String> list = readFromElements(groupPage.getLbGuardarMiemb());
        boolean participante = list.stream().anyMatch(s -> s.contains(auxTwo));

        assertFalse("La lista contiene al participante elimnado", participante);
    }

    public void creoGrupoSinNombre() {
        String step = "Creo un grupo sin nombre";
        log.info(step);

        clickElement(groupPage.getBtnNuevogrupo2());
        clickElement(groupPage.getAbrirNuevoGrupo());
        write(groupPage.getIngresarDescripcionGrupo(), "Algo interesante");
        clickElement(groupPage.getCheckBoxPrimContac());
        clickElement(groupPage.getCheckBoxSegContac());
        elementIsDisplayed(groupPage.getLbMiembro());

        uploadFileClassName("src/test/resources/files/ven.png", "file_input_img");

        waitForElementSrcToChangeFromTo(groupPage.getBtnFoto(),
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");
        clickElement(groupPage.getBtnCreargrupo());
    }

    public void validoVisualizaMensaje(String mensaje) {
        String step = "Valido que se visualiza el mensaje " + mensaje;
        log.info(step);

        String sinNombre = textFromElement(groupPage.getLbSinNombreGrupo());

        assertEquals("Los elementos no son iguales", sinNombre, mensaje);
    }

    public void creoGrupoSinParticipantes() {
        String step = "Creo un grupo sin participantes";
        log.info(step);

        clickElement(groupPage.getBtnNuevogrupo2());
        clickElement(groupPage.getAbrirNuevoGrupo());
        write(groupPage.getIngresarNombreGrupo(), "Sin participante");
        write(groupPage.getIngresarDescripcionGrupo(), "Algo interesante");

        uploadFileClassName("src/test/resources/files/ven.png", "file_input_img");

        waitForElementSrcToChangeFromTo(groupPage.getBtnFoto(),
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");
    }

    public void validoNoPuedoCrearGrupo() {
        String step = "Valido que no puedo crear el grupo";
        log.info(step);

        boolean crearBoton = isElementEnabledByCss(groupPage.getBtnCrearGrupoInhab());

        assertFalse("El boton esta habilitado", crearBoton);
    }
}