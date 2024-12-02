package pom.conferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import steps.Hooks;

import static org.junit.Assert.*;

public class ConferencesLogic extends Hooks{

    ConferencesPage conferencesPage = new ConferencesPage();
    private static final Logger log = LogManager.getLogger(ConferencesLogic.class);

    public void ingresoConferencia() {
        String step = "Ingreso a conferencias";
        log.info(step);

        clickElement(conferencesPage.getBtnAbrirMenu());
        clickElement(conferencesPage.getBtnConferencias());
    }

    public void validoEstoyConferencia() {
        String step = "Valido que estoy en la conferencia";
        log.info(step);

        boolean video = elementIsDisplayed(conferencesPage.getVideo());

        assertTrue("No se visualiza el video.", video);
    }
}