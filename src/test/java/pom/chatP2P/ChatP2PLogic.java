package pom.chatP2P;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import steps.Hooks;

import static org.junit.Assert.*;

public class ChatP2PLogic extends Hooks{

    ChatP2PPage chatP2PPage = new ChatP2PPage();

    private static final Logger log = LogManager.getLogger(ChatP2PLogic.class);

    public void buscoChat(String nombre) {
        String step = "Busco el chat " + nombre;
        log.info(step);

        write(chatP2PPage.getInputBuscarGrupo(), nombre);

        String chat = String.format(chatP2PPage.getCheckBoxChat(), nombre);

        clickElement(chat);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        auxTwo = nombre;
    }

    public void eliminoChatCreado() {
        String step = "Elimino el chat";
        log.info(step);

        waitForElementSrcToChangeFromTo("//div[@class='profile-chat']//img",
                "https://test-cc200-web.netlify.app/assets/pro-test-5c181ae5.png",
                "jpeg", "png");

        clickElement(chatP2PPage.getBtn3PuntosChat());
        elementIsDisplayed(chatP2PPage.getBtnEliminarGrupo());
        clickElement(chatP2PPage.getBtnEliminarGrupo());
        elementIsDisplayed(chatP2PPage.getBtnConfirmarEliminarGrupo());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickElement(chatP2PPage.getBtnConfirmarEliminarGrupo());
        waitForElementToBeInvisible(chatP2PPage.getLoading());
    }

    public void validoEliminoChatLLamado() {
        String step = "Valido que se elimino el chat";
        log.info(step);

        refreshPage();
        elementEnabled(chatP2PPage.getBtnNuevogrupo());

        auxTwo = String.format(chatP2PPage.getBtnChatCreado(), aux);
        boolean chat = existsElement(auxTwo);

        assertFalse("Se visualiza un chat llamado " + aux, chat);
    }

    public void validoVisualizaChat() {
        String step = "Valido que se visualiza el chat";
        log.info(step);

        String vistaNombreChat = String.format(chatP2PPage.getNombreChat(), auxTwo);
        boolean nombreIsDisplayed = elementIsDisplayed(vistaNombreChat);

        assertTrue("No se encontro el grupo", nombreIsDisplayed);
    }

    public void actualizoTemaChat() {
        String step = "Actualizo el tema del chat";
        log.info(step);

        waitForElementToBeVisible(chatP2PPage.getBtn3PuntosChat());
        clickElementActions(chatP2PPage.getBtn3PuntosChat());
        clickElementActions(chatP2PPage.getBtnConfiguracion());

        elementIsDisplayed(chatP2PPage.getColorTema());
        aux = textFromElementAttribute(chatP2PPage.getColorTema(), "style");

        clickElementActions(chatP2PPage.getBtnTema());

        if(aux.contains("19, 188, 127")) {
            elementIsDisplayed(chatP2PPage.getBtnRojo());
            clickElementActions(chatP2PPage.getBtnRojo());
        } else {
            elementIsDisplayed(chatP2PPage.getBtnVerde());
            clickElement(chatP2PPage.getBtnVerde());
        }

        try {
            Thread.sleep(650);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForElementToBeVisible(chatP2PPage.getBtn3PuntosChat());
        clickElementActions(chatP2PPage.getBtn3PuntosChat());
        clickElementActions(chatP2PPage.getBtnConfiguracion());

        auxTwo = textFromElementAttribute(chatP2PPage.getColorTema(), "style");
    }

    public void validoActualizoTemaChat() {
        String step = "Valido que se actualizo el tema del chat";
        log.info(step);

        assertNotEquals("El tema no se cambio", aux, auxTwo);
    }

    public void validoAbrirInformacionChat() {
        String step = "Valido abrir informacion del chat";
        log.info(step);

        waitForElementToBeVisible(chatP2PPage.getBtn3PuntosChat());
        clickElement(chatP2PPage.getBtn3PuntosChat());
        elementEnabled(chatP2PPage.getBtnInformacion());
        clickElement(chatP2PPage.getBtnInformacion());

        boolean nombreGrupoDisplayed = elementEnabled(chatP2PPage.getNombreEnInformacion());

        assertTrue("No se muestra el nombre del grupo", nombreGrupoDisplayed);
    }

    public void cambioAlias(String nuevoAlia) {
        String step = "Cambio el alias a " + nuevoAlia;
        log.info(step);

        elementIsDisplayed(chatP2PPage.getBtn3PuntosChat());
        clickElement(chatP2PPage.getBtn3PuntosChat());
        waitForElementToBeVisible(chatP2PPage.getBtnConfiguracion());
        clickElement(chatP2PPage.getBtnConfiguracion());
        clickElement(chatP2PPage.getBtnAlias());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        write(chatP2PPage.getInputCambiarNombre(), nuevoAlia);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickElement(chatP2PPage.getBtnGuardar());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String alias = String.format(chatP2PPage.getLblNuevoAlias(), nuevoAlia);

        waitForElementToBeVisible(alias);

        aux = nuevoAlia;
    }

    public void validoVisualizaNuevoAlias() {
        String step = "Valido que se visualiza el nuevo alias";
        log.info(step);

        String botonChat = String.format(chatP2PPage.getBtnChatCreado(), aux);
        boolean chat = elementIsDisplayed(botonChat);

        assertTrue("No se encontro un chat llamado Nuevo Alias", chat);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}