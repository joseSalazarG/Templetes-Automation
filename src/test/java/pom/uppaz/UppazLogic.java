package pom.uppaz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import steps.Hooks;

import static org.junit.Assert.*;

public class UppazLogic extends Hooks{

    UppazPage uppazPage = new UppazPage();
    private static final Logger log = LogManager.getLogger(UppazLogic.class);

    public void ingresarUPPAZ() {
        String step = "Ingreso a UPPAZ";
        log.info(step);

        clickElement(uppazPage.getBtnAbrirMenu());
        clickElement(uppazPage.getBtnUPPAZ());
    }

    public void agregarMiembro(String rol) {
        String step = "Agrego un miembro con cedula 11123456";
        log.info(step);

        clickElement(uppazPage.getBtnAgregarUPPAZ());
        clickElementActions(uppazPage.getComboboxTipoDocumento());
        clickElementActions(uppazPage.getComboboxOpcionV());
        write(uppazPage.getInputCedula(), "11123456");
        clickElementActions(uppazPage.getComboboxRol());

        switch (rol) {
            case "EQUIPO COORDINADOR DE COMUNIDAD" -> clickElementActions(uppazPage.getComboboxOpcionPorComunidad());
            case "MOTORIZADOS" -> clickElementActions(uppazPage.getComboboxOpcionMotorizados());
            case "EQUIPO DE CALLE" -> clickElementActions(uppazPage.getComboboxOpcionPorCalle());
        }

        clickElement(uppazPage.getBtnConsultarBase());

        try {
            waitForElementToBeVisible(uppazPage.getLblRespuestaAPI());
        } catch (TimeoutException e) {
            throw new TimeoutException("La API no respondio en el tiempo esperado", e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No se pudo encontrar el elemento con el localizador: " + uppazPage.getLblRespuestaAPI(), e);
        }

        write(uppazPage.getInputMail(), "usuario@ejemplo.net");

        //primero hago clic en el combo box para que se visualicen los generos
        clickElementActions(uppazPage.getComboboxGenero());

        //segundo almaceno en una variable de tipo webelement el xpath
        WebElement prueba = driver.findElement(By.xpath("//div[@class='react-select__menu css-1nmdiq5-menu']"));

        //tercero capturo el atributo del xpath en una variable de tipo string
        String prueba2 = prueba.getAttribute("innerHTML");

        //cuarto imprimo en consola para ver todo el elemento completo
        System.err.println(prueba2);

        //quinto como pude visualizar el elemento completo construyo el xpath y le hago clic
        clickElement("//div[@class='react-select__option css-10wo9uf-option' and @aria-disabled='false' and text()='F']");
        clickElementActions(uppazPage.getComboboxOpcionFemale());
        write(uppazPage.getInputTelefono(), "581234567");
        clickElement(uppazPage.getBtnSiguiente());
        try {
            waitForElementToBeVisible(uppazPage.getLblRespuestaAPI2());
        } catch (TimeoutException e) {
            throw new TimeoutException("La API no respondio en el tiempo esperado", e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No se pudo encontrar el elemento con el localizador: " + uppazPage.getLblRespuestaAPI2(), e);
        }
        write(uppazPage.getInputUBCH(), "Example");
        write(uppazPage.getInputComunidad(), "Example");
        write(uppazPage.getInputCalle(), "Example");
        clickElement(uppazPage.getBtnAgregar());
    }

    public void verListadoMiembros() {
        String step = "Ver el listado de miembros";
        log.info(step);

        clickElement(uppazPage.getBtnVerListado());
    }

    public void verListadoMiembrosDifusor() {
        String step = "Ver el listado de miembros desde un difusor";
        log.info(step);

        clickElement(uppazPage.getBtnVerListadoDifusor());
    }

    public void validarAgregarUPPAZ() {
        String step = "Valido se agrego el miembro UPPAZ";
        log.info(step);

        String txt = textFromElement(uppazPage.getLblAvisoAgregar());
        boolean result = txt.contains("Miembro Uppaz agregado con éxito.");

        assertTrue("No se agrego al miembro", result);

        clickElement(uppazPage.getBtnOk());
    }

    public void validoEstoyListadoMiembros() {
        String step = "Valido que estoy en el listado de miembros";
        log.info(step);

        boolean listadoMiembros = elementIsDisplayed(uppazPage.getInputBuscar());

        assertTrue("No estoy en el listado de miembros", listadoMiembros);
    }

    public void buscarMiembro(String nombre) {
        String step = "Busco al miembro de nombre " + nombre;
        log.info(step);

        aux = nombre;

        try {
            Thread.sleep(1000);
            write(uppazPage.getInputBuscar(), nombre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!existsElement(uppazPage.getBtnVerDetalles())) {
            clearWithBackspace(uppazPage.getInputBuscar());
        }
    }

    public void eliminarMiembro(String rol) {
        String step = "Elimino al miembro de cedula 11123456";
        log.info(step);

        clickElement(uppazPage.getBtnVerListado());
        write(uppazPage.getInputBuscar(), "Hernandez");
        clickElement(uppazPage.getBtnVerDetallesHernandez());
        clickElement(uppazPage.getBtn3Puntos());
        clickElement(uppazPage.getBtnEliminar());
        clickElementActions(uppazPage.getBtnOk());
    }

    public void validoVisualizaMiembroBuscado() {
        String step = "Valido que se visualiza el miembro buscado";
        log.info(step);

        String miembroBuscado = textFromElement("//div[text()='" + aux + "']");

        assertEquals("No se encontro el miembro buscado " + aux, "Jaimito Perez", miembroBuscado);
    }

    public void consultarCedula(String cedula) {
        String step = "Consulto la cedula " + cedula;
        log.info(step);

        clickElement(uppazPage.getBtnAgregarUPPAZ());
        clickElementActions(uppazPage.getComboboxTipoDocumento());
        clickElementActions(uppazPage.getComboboxOpcionV());
        write(uppazPage.getInputCedula(), cedula);
        clickElementActions(uppazPage.getComboboxRol());
        clickElementActions(uppazPage.getComboboxOpcionPorComunidad());
        clickElement(uppazPage.getBtnConsultarBase());
    }

    public void consultarTelefono() {
        String step = "Consulto el telefono +58555555";
        log.info(step);

        clickElement(uppazPage.getBtnAgregarUPPAZ());
        clickElementActions(uppazPage.getComboboxTipoDocumento());
        clickElementActions(uppazPage.getComboboxOpcionV());
        write(uppazPage.getInputCedula(), "11123456");
        clickElementActions(uppazPage.getComboboxRol());
        clickElementActions(uppazPage.getComboboxOpcionPorComunidad());
        clickElement(uppazPage.getBtnConsultarBase());

        try {
            waitForElementToBeVisible(uppazPage.getLblRespuestaAPI());
        } catch (TimeoutException e) {
            throw new TimeoutException("La API no respondio en el tiempo esperado", e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No se pudo encontrar el elemento con el localizador: " + uppazPage.getLblRespuestaAPI(), e);
        }

        write(uppazPage.getInputMail(), "usuario@ejemplo.net");
        clickElementActions(uppazPage.getComboboxGenero());

        WebElement prueba = driver.findElement(By.xpath("//div[@class='react-select__menu css-1nmdiq5-menu']"));
        String prueba2 = prueba.getAttribute("innerHTML");
        System.err.println(prueba2);

        clickElement("//div[@class='react-select__option css-10wo9uf-option' and @aria-disabled='false' and text()='F']");
        clickElementActions(uppazPage.getComboboxOpcionFemale());
        write(uppazPage.getInputTelefono(), "555555");
        clickElement(uppazPage.getBtnSiguiente());
        write(uppazPage.getInputUBCH(), "Example");
        write(uppazPage.getInputComunidad(), "Example");
        write(uppazPage.getInputCalle(), "Example");
        clickElement(uppazPage.getBtnAgregar());
    }

    public void validoNumeroTelefonoRegistrado() {
        String step = "Valido que el numero esta registrado";
        log.info(step);

        elementIsDisplayed(uppazPage.getLblTelefonoRegistrado());

        boolean label = existsElement(uppazPage.getLblTelefonoRegistrado());

        assertTrue("No se encontro el aviso <Telefono ya Registrado>", label);
    }

    public void validoCedulaRegistrada() {
        String step = "Valido que la cedula ya esta registrada";
        log.info(step);

        boolean label = elementIsDisplayed(uppazPage.getLblCedulaRegistrada());;

        assertTrue("No se encontro el aviso <Cedula ya Registrada>", label);
    }

    public void editarTelefono() {
        String step = "Edito el numero de telefono de un miembro";
        log.info(step);

        clickElement(uppazPage.getBtnVerDetalles());

        aux = textFromElement(uppazPage.getLblTelefono());

        clickElement(uppazPage.getBtn3Puntos());
        clickElement(uppazPage.getBtnEditar());

        clearWithBackspace(uppazPage.getInputTelefono());


        write(uppazPage.getInputTelefono(), aux.substring(3));
        clickElement(uppazPage.getBtnContinuar());
        clickElement(uppazPage.getBtnOk());
        clickElement(uppazPage.getBtnVerDetalles());
    }

    public void validoEditarTelefono() {
        String step = "Valido se modifico el telefono";
        log.info(step);

        boolean result = aux.equals(textFromElement(uppazPage.getLblTelefono()));

        assertFalse("No se edito el numero de telefono", result);
    }

    public void restaurarNumeroModificadoMiembro() {
        String step = "Restauro el numero modificado del miembro";
        log.info(step);

        clickElement(uppazPage.getBtn3Puntos());
        clickElement(uppazPage.getBtnEditar());


        clearWithBackspace(uppazPage.getInputTelefono());


        write(uppazPage.getInputTelefono(), "58321 654 9841");
        clickElement(uppazPage.getBtnContinuar());
        clickElement(uppazPage.getBtnOk());
        clickElement(uppazPage.getBtnVerDetalles());
        boolean result = aux.equals(textFromElement(uppazPage.getLblTelefono()));

        assertTrue("No se restauro el numero de telefono original", result);
    }
}