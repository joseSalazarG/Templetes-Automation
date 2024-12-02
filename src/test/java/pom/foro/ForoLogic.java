package pom.foro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Hooks;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ForoLogic extends Hooks {

    ForoPage foroPage = new ForoPage();
    private static final Logger log = LogManager.getLogger(ForoLogic.class);

    public void ingresoConferencia() {
        String step = "Ingreso a conferencias";
        log.info(step);

        clickElement(foroPage.getBtnAbrirMenu());
        clickElement(foroPage.getBtnConferencias());
    }

    public void validoEstoyConferencia() {
        String step = "Valido que estoy en la conferencia";
        log.info(step);

        String foro = textFromElement(foroPage.getForoOpinion());

        assertEquals("No se visualiza el video.","Foro de opinión" , foro);
    }

    public void filtro(String filtro) {
        String step = "Filtro por " + filtro;
        log.info(step);

        waitForElementToBeInvisible(foroPage.getLoading());
        clickElement(foroPage.getBtnFiltro());
        clickElement(foroPage.getBtnCiencia());
    }

    public void validoAplicoFiltro() {
        String step = "Valido que se aplico el filtro";
        log.info(step);

        String filtroCiencia = textFromElement("//span[contains(@class, 'MuiListItemText-primary') and contains(text(), 'Las vitaminas que podrían retrasar el envejecimiento y estabilizar la presión arterial')]");

        assertEquals("Los elementos no son iguales",
                "Las vitaminas que podrían retrasar el envejecimiento y estabilizar la presión arterial", filtroCiencia);
    }
}