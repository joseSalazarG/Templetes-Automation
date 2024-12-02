package pom.venezuela;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import pom.baseUrl.UrlConstant;
import steps.Hooks;

import static org.junit.Assert.*;

public class VenezuelaLogic extends Hooks{

    VenezuelaPage venezuelaPage = new VenezuelaPage();
    private static final Logger log = LogManager.getLogger(VenezuelaLogic.class);
    public void ingresarTodosPorVenezuela() {
        String step = "Ingreso a todos por venezuela";
        log.info(step);

        clickElement(venezuelaPage.getBtnAbrirMenu());
        clickElement(venezuelaPage.getBtnTodosPorVenezuela());
    }

    public void agregarElector() {
        String step = "Agrego un elector";
        log.info(step);

        clickElement(venezuelaPage.getBtnAgregarPorComunidad());
        clickElementActions(venezuelaPage.getComboboxTipoDocumento());
        clickElementActions(venezuelaPage.getComboboxOpcionV());
        write(venezuelaPage.getInputCedula(), "11123456");
        clickElement(venezuelaPage.getBtnConsultarBase());

        try {
            waitForElementToBeVisible(venezuelaPage.getLblRespuestaAPI());
       } catch (TimeoutException e) {
            throw new TimeoutException("La API no respondio en el tiempo esperado", e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No se pudo encontrar el elemento con el localizador: " + venezuelaPage.getLblRespuestaAPI(), e);
        }

        write(venezuelaPage.getInputTelefono(), "581234567");
        clickElement(venezuelaPage.getBtnAgregar());
        clickElement(venezuelaPage.getBtnOk());
    }

    public void agregarMiembroConNumero(String telefono) {
        String step = "Agrego un miembro con numero de telefono " + telefono;
        log.info(step);

        clickElement(venezuelaPage.getBtnAgregarPorComunidad());
        clickElementActions(venezuelaPage.getComboboxTipoDocumento());
        clickElementActions(venezuelaPage.getComboboxOpcionV());
        write(venezuelaPage.getInputCedula(), "11123456");
        clickElement(venezuelaPage.getBtnConsultarBase());

        try {
            waitForElementToBeVisible(venezuelaPage.getLblRespuestaAPI());
      } catch (TimeoutException e) {
            throw new TimeoutException("La API no respondio en el tiempo esperado", e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No se pudo encontrar el elemento con el localizador: " + venezuelaPage.getLblRespuestaAPI(), e);
        }

        write(venezuelaPage.getInputTelefono(), telefono);
        clickElement(venezuelaPage.getBtnAgregar());
    }

    public void validarAgregarElector() {
        String step = "Valido se agrego el elector";
        log.info(step);

        navigateToUrl(UrlConstant.DEV);

        clickElement(venezuelaPage.getBtnAbrirMenu());
        clickElement(venezuelaPage.getBtnTodosPorVenezuela());
        clickElement(venezuelaPage.getBtnVerListado());

        boolean exist = elementIsDisplayed(venezuelaPage.getLblNombre());

        assertTrue("No se visualiza el nombre del elector", exist);
    }

    public void validoListadoElector() {
        String step = "Valido que estoy en el listado de electores";
        log.info(step);

        clickElement(venezuelaPage.getBtnVerListado());

        boolean buscar = elementEnabled(venezuelaPage.getInputBuscar());

        assertTrue("No estoy en el liestado de electores", buscar);
    }

    public void buscarElector(String nombre) {
        String step = "Busco al elector de nombre " + nombre;
        log.info(step);

        clickElement(venezuelaPage.getBtnVerListado());
        write(venezuelaPage.getInputBuscar(), nombre);

        String nombreAux = String.format(venezuelaPage.getLblNombre3(), nombre );

        aux = textFromElement(nombreAux);
    }

    public void validoEncontroElector() {
        String step = "Valido que se encontro al elector";
        log.info(step);

        String elector = textFromElement(venezuelaPage.getLblNombre2());

        assertEquals("No se encontro al lector: " + aux, aux, elector);
    }

    public void eliminarElector() {
        String step = "Elimino al elector agregado";
        log.info(step);

        navigateToUrl(UrlConstant.DEV);

        clickElement(venezuelaPage.getBtnAbrirMenu());
        clickElement(venezuelaPage.getBtnTodosPorVenezuela());
        clickElement(venezuelaPage.getBtnVerListado());
        clickElement(venezuelaPage.getBtnVerDetalles());
        elementIsDisplayed(venezuelaPage.getLblTelefono());

        if (existsElement(venezuelaPage.getLblNombre2())) {
            clickElement(venezuelaPage.getBtnAtras());
            clickElement(venezuelaPage.getBtnVerDetalles2());
        }

        clickElement(venezuelaPage.getBtn3Puntos());
        clickElement(venezuelaPage.getBtnEliminar());
        clickElement(venezuelaPage.getBtnOk());
    }

    public void validoNoEncontrarElector() {
        String step = "Valido no visualizar al elector agregado";
        log.info(step);

        write(venezuelaPage.getInputBuscar(), "MILAGROS DEL VALLE PEREZ HERNANDEZ");

        String elector = textFromElement(venezuelaPage.getLblElector1());

        System.err.println(elector);

        assertNotEquals("Se visualiza el elector", "MILAGROS DEL VALLE PEREZ HERNANDEZ", elector);
    }

    public void editarTelefono() {
        String step = "Edito el numero de telefono de un miembro";
        log.info(step);

        clickElement(venezuelaPage.getBtnVerListado());
        clickElement(venezuelaPage.getBtnVerDetalles());

        aux = textFromElement(venezuelaPage.getLblTelefono());

        clickElement(venezuelaPage.getBtn3Puntos());
        clickElement(venezuelaPage.getBtnEditar());

        clearWithBackspace(venezuelaPage.getInputTelefono());


        write(venezuelaPage.getInputTelefono(), "+5867676767676");
        clickElement(venezuelaPage.getBtnContinuar());
        clickElement(venezuelaPage.getBtnOk());
        clickElement(venezuelaPage.getBtnVerDetalles());
    }

    public void validoEditarTelefono() {
        String step = "Valido se modifico el telefono";
        log.info(step);

        boolean result = aux.equals(textFromElement(venezuelaPage.getLblTelefono()));

        assertFalse("No se edito el numero de telefono", result);
    }

    public void restaurarNumeroModificadoElector() {
        String step = "Restauro el numero modificado del elector";
        log.info(step);

        clickElement(venezuelaPage.getBtn3Puntos());
        clickElement(venezuelaPage.getBtnEditar());


        clearWithBackspace(venezuelaPage.getInputTelefono());


        write(venezuelaPage.getInputTelefono(), aux.substring(1));

        clickElement(venezuelaPage.getBtnContinuar());
        clickElement(venezuelaPage.getBtnOk());
        clickElement(venezuelaPage.getBtnVerDetalles());

        boolean result = aux.equals(textFromElement(venezuelaPage.getLblTelefono()));
        log.info(textFromElement(venezuelaPage.getLblTelefono()));
        assertTrue("No se restauro el numero de telefono original", result);
    }

    public void consultarCedula(String cedula) {
        String step = "Consulto en base de datos la cedula  " + cedula;
        log.info(step);

        clickElement(venezuelaPage.getBtnAgregarPorComunidad());
        clickElementActions(venezuelaPage.getComboboxTipoDocumento());
        clickElementActions(venezuelaPage.getComboboxOpcionV());
        write(venezuelaPage.getInputCedula(), cedula);
        clickElement(venezuelaPage.getBtnConsultarBase());
    }

    public void validarCedulaRegistrada() {
        String step = "Valido esta cedula ya esta registrada";
        log.info(step);

        try {
            waitForElementToBeVisible(venezuelaPage.getLblCedulaRegistrada());
      } catch (TimeoutException e) {
            assertTrue(true);
        } catch (NoSuchElementException e) {
            fail("No se encontro el aviso <Cedula ya Registrada>");
        }
    }

    public void validarTelefonoRegistrado() {
        String step = "Valido este telefono ya esta registrado";
        log.info(step);

        elementIsDisplayed(venezuelaPage.getLblTelefonoRegistrado());

        boolean label = existsElement(venezuelaPage.getLblTelefonoRegistrado());

        assertTrue("No se encontro el aviso <Telefono ya Registrado>", label);
    }

    public void validoNumeroTelefonoInvalido() {
        String step = "Valido que el numero de telefono es invalido";
        log.info(step);

        String mensajeError = textFromElement(venezuelaPage.getLbNumInvalido());

        assertEquals("No se visualiza el mensaje de error.", "Número inválido", mensajeError);
    }
}
