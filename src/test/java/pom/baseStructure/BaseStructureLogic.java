package pom.baseStructure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.login.LoginLogic;
import steps.ApiHooks;
import steps.Hooks;
import org.openqa.selenium.TimeoutException;

import java.util.Arrays;

import static org.junit.Assert.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BaseStructureLogic extends Hooks {

    BaseStructurePage baseStructurePage = new BaseStructurePage();
    LoginLogic loginLogic = new LoginLogic();
    ApiHooks apiHooks = new ApiHooks();
    private static final Logger log = LogManager.getLogger(BaseStructureLogic.class);

    //Variables utilizadas en la seccion agregar miembros
    private static final String ci = "15686150";
    private static final String nombre = "COSSIO JIMENEZ LIDIA JOHANA";
    private static final String nombre2 = "Perencejo Testing";
    private static final String email = "perencejo@gmail.com";
    private static final String email2 ="perencejo@nolatech.com";
    private static final String tlf = "4247896543";
    private static final String nombreUBCH = "peren";
    private static final String nombreComunidadEditado = "perenEditado";
    private static final String nombreComunidad = "cejo";
    private static final String nombreCalle = "perez";

    //Variables utilizadas para la seccion equipo de trabajo
    private static final String ciET = "10056530";
    private static final String nombreET = "PERAZA LUGO NILDA NOEMI";
    private static final String nombreListaET = "Equipo de Trabajo Testing";
    private static final String emailET = "sutanejo@gmail.com";
    private static final String tlfET = "4249876254";
    private static final String nombreUBCHET = "suta";
    private static final String nombreComunidadET = "nejo";
    private static final String nombreCalleET = "perez";

    public void agregoMiembroAgregarMiembro(String rol) {
        String step = "Agrego un miembro desde agregar miembro";
        log.info(step);

        String[] rolesET = {"FORMACIÓN", "ELECTORAL Y APC", "MOVILIZACIÓN Y EVENTOS", "MUJERES" ,
                "ECONOMÍA PRODUCTIVA" ,"JUVENTUD" ,"COMUNAS Y MOVIMIENTOS SOCIALES","TRABAJADORES"
                ,"MISIONES Y GRANDES MISIONES","DEFENSA INTEGRAL DE LA NACIÓN"};

        clickElement(baseStructurePage.getBtnMenu3Puntos() );
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnAgregarMiembros());

        //Formulario de cedula
        clickElement(baseStructurePage.getCboxTipoDeDocumento());
        clickElement(baseStructurePage.getCboxOpcionV());

        if (Arrays.asList(rolesET).contains(rol)) {

            write(baseStructurePage.getInputCedula(), ciET);
            clickElementActions(baseStructurePage.getCboxResponsabilidad());

            String opcionRol = String.format(baseStructurePage.getCboxResponsabilidadOpcion(), rol);

            clickElement(opcionRol);
            clickElement(baseStructurePage.getBtnConsultarBD());

            //validacion de que cargo el nombre del usuario a traves de la api
            String nombreValor = String.format(baseStructurePage.getInputNombreValor(), nombreET);

            //Formulario de Datos personales
            try {
                waitForElementToBeVisible(nombreValor);
            } catch (TimeoutException e) {
                if (existsElement(baseStructurePage.getLbCedulaRegistrada())) {
                    fail("Esta cédula ya esta registrada");
                } else {
                    fail("La API no respondio en el tiempo esperado");
                }
            }

            write(baseStructurePage.getInputEmail(), emailET);
            clickElementActions(baseStructurePage.getCboxGenero());
            waitForElementToBeVisible(baseStructurePage.getCboxGeneroM());
            clickElementActions(baseStructurePage.getCboxGeneroM());
            write(baseStructurePage.getInputNumeroTlf(), tlfET);
            clickElement(baseStructurePage.getBtnSiguienteDatosPersonales());

            //Formulario de otros datos
            write(baseStructurePage.getInputNombreUBCH(), nombreUBCHET);
            write(baseStructurePage.getInputNombreComunidad(), nombreComunidadET);
            write(baseStructurePage.getInputNombreCalle(), nombreCalleET);
            clickElement(baseStructurePage.getBtnAgregarMiembro());

            boolean lbEquipoTrabajoAgregado = elementIsDisplayed(baseStructurePage.getLbSeAgregoEquipoTrabajo()) ;

            assertTrue("No se visualiza mensaje 'Miembro agregado con éxito.'", lbEquipoTrabajoAgregado);

            clickElement(baseStructurePage.getBtnOkPopupMiembroETAgregado());

        } else {

            write(baseStructurePage.getInputCedula(), ci);
            clickElementActions(baseStructurePage.getCboxResponsabilidad());

            String opcionRol = String.format(baseStructurePage.getCboxResponsabilidadOpcion(), rol);

            clickElement(opcionRol);
            clickElement(baseStructurePage.getBtnConsultarBD());

            //validacion de que cargo el nombre del usuario a traves de la api
            String nombreValor = String.format(baseStructurePage.getInputNombreValor(), nombre);

            //Formulario de Datos personales
            try {
                waitForElementToBeVisible(nombreValor);
            } catch (TimeoutException e) {
                if (existsElement(baseStructurePage.getLbCedulaRegistrada())) {
                    fail("Esta cédula ya esta registrada");
                } else {
                    fail("La API no respondio en el tiempo esperado");
                }
            }

            write(baseStructurePage.getInputEmail(), email);
            clickElementActions(baseStructurePage.getCboxGenero());
            waitForElementToBeVisible(baseStructurePage.getCboxGeneroM());
            clickElementActions(baseStructurePage.getCboxGeneroM());
            write(baseStructurePage.getInputNumeroTlf(), tlf);
            clickElement(baseStructurePage.getBtnSiguienteDatosPersonales());

            //Formulario de otros datos
            write(baseStructurePage.getInputNombreUBCH(), nombreUBCH);
            write(baseStructurePage.getInputNombreComunidad(), nombreComunidad);
            write(baseStructurePage.getInputNombreCalle(), nombreCalle);
            clickElement(baseStructurePage.getBtnAgregarMiembro());

            boolean lbPorAprobar = elementIsDisplayed(baseStructurePage.getLbMiembroAgregadoPorAprobar());

            assertTrue("No se visualiza mensaje de miembro por aprobar", lbPorAprobar);

            clickElement(baseStructurePage.getBtnOkPopupMiembroPorAprobar());
        }

    }

    public void validoAgregueMiembro(String estado) {
        String step = "Valido que agregue el miembro con estado " + estado;
        log.info(step);

        apiHooks.putChangeStatus("V" + ci, estado);

        //codigo para refrescar la pagina
        loginLogic.navegoPaginaWebCC200();

        clickElement(baseStructurePage.getBtnMenu3Puntos());
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());
        write(baseStructurePage.getInputBusqueda(), nombre);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombre);
        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(baseStructurePage.getBtnDetalles()), 1));

        assertTrue("No se visualiza el contacto en la lista", miembroVisible);
    }

    public void eliminoMiembro() {
        String step = "Elimino el miembro";
        log.info(step);

        clickElement(baseStructurePage.getBtnDetalles());
        clickElement(baseStructurePage.getBtnMenu3PuntosDetalles());
        clickElement(baseStructurePage.getBtnEliminar());
        clickElement(baseStructurePage.getCboxTipoDeDocumentoEliminar());

        clickElement(baseStructurePage.getCboxOpcionVEliminar());
        write(baseStructurePage.getInputCedula(), ciET);
        clickElement(baseStructurePage.getBtnEnviarEliminar());

        clickElement(baseStructurePage.getBtnOkPopupMiembroPorAprobar());

        apiHooks.putChangeStatus("V" + ci, "ACEPTADO");

        clickElement(baseStructurePage.getBtnOkPopupMiembroPorAprobar());
    }

    public void ingresoListadoMiembros() {
        String step = "Ingreso a listado de miembros";
        log.info(step);

        clickElement(baseStructurePage.getBtnMenu3Puntos() );
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());
    }

    public void validoVerListadoMiembros() {
        String step = "Valido que puedo ver el listado de miembros";
        log.info(step);

        String locatorLbListaDeMiembro = String.format(baseStructurePage.getLbListaDeMiembros());
        boolean lbVisible = elementIsDisplayed(locatorLbListaDeMiembro);

        assertTrue("No se visualiza el titulo de la pagina", lbVisible);

        write(baseStructurePage.getInputBusqueda(), nombre2);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombre2);
        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        assertTrue("No se visualiza el contacto en la lista", miembroVisible);
    }

    public void validoFiltroEstado() {
        String step = "Valido que se filtro por estado";
        log.info(step);

        String locatorMiembro, filtroEstado;
        String[] estados = {"Todos", "Equipo de trabajo", "Pendientes", "Aceptados", "Declinados"};

        for (String estado: estados) {
            switch (estado) {
                case "Todos" -> {
                    step = "Valido que se filtro por Todos";
                    log.info(step);

                    filtroEstado = String.format(baseStructurePage.getRbtnFiltro(), estado);

                    clickElementJs(baseStructurePage.getBtnFiltroListado());
                    clickElementJs(filtroEstado);

                    waitForElementToBeInvisible(baseStructurePage.getLbFiltrarPor());

                    locatorMiembro = String.format(baseStructurePage.getElementoDeLaLista(), "PENDIENTE");

                    boolean miembroFiltradoPendiente = elementIsDisplayed(locatorMiembro);

                    assertTrue("No se visualizan miembros con la etiqueta Pendiente en la lista", miembroFiltradoPendiente);

                    boolean miembroFiltradoRechazadoAceptado = elementIsDisplayed(baseStructurePage.getElementoDeListaTodos());

                    assertTrue("No se visualizan miembros con la etiqueta declinado o aceptado en la lista", miembroFiltradoRechazadoAceptado);
                }
                case "Equipo de trabajo" -> {
                    step = "Valido que se filtro por Equipo de trabajo";
                    log.info(step);

                    filtroEstado = String.format(baseStructurePage.getRbtnFiltro(), estado);

                    clickElementJs(baseStructurePage.getBtnFiltroListado());
                    clickElementJs(filtroEstado);

                    waitForElementToBeInvisible(baseStructurePage.getLbFiltrarPor());

                    boolean miembroEquipoTrabajo = elementIsDisplayed(baseStructurePage.getLbRolEquipoTrabajo());

                    assertTrue("No se visualizan miembros con alguna etiqueta de equipo de trabajo en la lista", miembroEquipoTrabajo);

                    boolean miembroJefes = existsElement(baseStructurePage.getLbRolJefe());

                    assertFalse ("Se visualizan miembros con la etiqueta de jefe en la lista", miembroJefes);
                }
                case "Pendientes" -> {
                    step = "Valido que se filtro por Pendientes";
                    log.info(step);

                    filtroEstado = String.format(baseStructurePage.getRbtnFiltro(), estado);

                    waitForElementToBeVisible(baseStructurePage.getBtnFiltroListado());
                    clickElementJs(baseStructurePage.getBtnFiltroListado());
                    clickElementJs(filtroEstado);

                    waitForElementToBeInvisible(baseStructurePage.getLbFiltrarPor());

                    locatorMiembro = String.format(baseStructurePage.getElementoDeLaLista(), "PENDIENTE");

                    boolean miembroFiltrado = elementIsDisplayed(locatorMiembro);

                    assertTrue("No se visualizan miembros con la etiqueta Pendiente en la lista", miembroFiltrado);

                    //Validacion de que solo se muestren miembros con el estado pendiente
                    locatorMiembro = String.format(baseStructurePage.getElementoDeLaLista(), "DECLINADO");

                    boolean miembroFiltradoRechazado = existsElement(locatorMiembro);

                    assertFalse("Se visualizan miembros con la etiqueta declinado en la lista filtrada con pendientes", miembroFiltradoRechazado);

                }
                case "Aceptados" -> {
                    step = "Valido que se filtro por Aceptados";
                    log.info(step);

                    filtroEstado = String.format(baseStructurePage.getRbtnFiltro(), estado);

                    clickElementJs(baseStructurePage.getBtnFiltroListado());
                    clickElementJs(filtroEstado);

                    waitForElementToBeInvisible(baseStructurePage.getLbFiltrarPor());

                    locatorMiembro = String.format(baseStructurePage.getElementoDeLaLista(), "ACEPTADO");

                    assertTrue("No se visualizan miembros con la etiqueta Aceptado en la lista", elementIsDisplayed(locatorMiembro));

                    //Validacion de que solo se muestren miembros con el estado aceptado
                    locatorMiembro = String.format(baseStructurePage.getElementoDeLaLista(), "DECLINADO");

                    boolean miembroFiltradoRechazado = existsElement(locatorMiembro);

                    assertFalse("Se visualizan miembros con la etiqueta declinado en la lista filtrada con aceptados", miembroFiltradoRechazado);
                }
                case "Declinados" -> {
                    step = "Valido que se filtro por Declinados";
                    log.info(step);

                    filtroEstado = String.format(baseStructurePage.getRbtnFiltro(), estado);

                    clickElementJs(baseStructurePage.getBtnFiltroListado());
                    clickElementJs(filtroEstado);

                    waitForElementToBeInvisible(baseStructurePage.getLbFiltrarPor());

                    locatorMiembro = String.format(baseStructurePage.getElementoDeLaLista(), "DECLINADO");

                    boolean miembroFiltradoRechazado = elementIsDisplayed(locatorMiembro);

                    assertTrue("No se visualizan miembros con la etiqueta declinado en la lista", miembroFiltradoRechazado);

                    //Validacion de que solo se muestren miembros con el estado declinado
                    locatorMiembro = String.format(baseStructurePage.getElementoDeLaLista(), "ACEPTADO");

                    boolean miembroFiltradoAceptado = existsElement(locatorMiembro);

                    assertFalse("Se visualizan miembros con la etiqueta Aceptado en la lista filtrada con declinados", miembroFiltradoAceptado);
                }
            }
        }
    }

    public void ingresoDetalleMiembro() {
        String step = "Ingreso al detalle de un miembro" ;
        log.info(step);

        write(baseStructurePage.getInputBusqueda(), nombre2);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombre2);
        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        assertTrue("No se visualiza el contacto en la lista", miembroVisible);

        clickElement(baseStructurePage.getBtnDetalles());
    }

    public void validoDetalleMiembro() {
        String step = "Valido que se visualizan los detalles del miembro";
        log.info(step);

        boolean lbDatosPersonales = elementIsDisplayed(baseStructurePage.getLbDatosPersonales());

        assertTrue("No se visualiza el etiqueta Datos personales",lbDatosPersonales);

        boolean lbOtrosDatos = elementIsDisplayed(baseStructurePage.getLbDatosPersonales());

        assertTrue("No se visualiza el etiqueta Otros datos", lbOtrosDatos);
    }

    public void validoBuscarMiembro() {
        String step = "Valido que puedo buscar un miembro";
        log.info(step);

        write(baseStructurePage.getInputBusqueda(), nombre2);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombre2);

        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        assertTrue("No se visualizan miembros en la lista", miembroVisible);
    }

    public void editarMiembro() {
        String step = "Edito un miembro desde listado de miembros";
        log.info(step);

        apiHooks.putChangeStatus("V" + ci, "ACEPTADO");

        loginLogic.navegoPaginaWebCC200();

        clickElement(baseStructurePage.getBtnMenu3Puntos());
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());

        String locatorDetallesMiembro = String.format(baseStructurePage.getBtnDetallesMiembro(), nombre);

        write(baseStructurePage.getInputBusqueda(), nombre);
        waitForElementToBeVisible(locatorDetallesMiembro);
        clickElement(locatorDetallesMiembro);
        clickElement(baseStructurePage.getBtnMenu3PuntosDetalles());
        clickElement(baseStructurePage.getBtnEditar());
        write(baseStructurePage.getInputNombreComunidad(), nombreComunidadEditado);
        clickElement(baseStructurePage.getBtnContinuar());

        boolean lbEditadoPorAprobar = elementIsDisplayed(baseStructurePage.getLbMiembroAgregadoPorAprobar());

        assertTrue("No se visualizan mensaje de edicion por aprobar", lbEditadoPorAprobar);

        apiHooks.putChangeStatus("V" + ci, "ACEPTADO");

        clickElement(baseStructurePage.getBtnOkPopupMiembroPorAprobar());
    }

    public void validoEditarMiembro() {
        String step = "Valido que puedo editar el miembro";
        log.info(step);

        loginLogic.navegoPaginaWebCC200();

        clickElement(baseStructurePage.getBtnMenu3Puntos() );
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());

        write(baseStructurePage.getInputBusqueda(), nombre);

        String locatorDetallesMiembro = String.format(baseStructurePage.getBtnDetallesMiembro(), nombre);

        waitForElementToBeVisible(locatorDetallesMiembro);
        clickElement(locatorDetallesMiembro);

        String comunidadEditado = String.format(baseStructurePage.getInputNombreComunidadEditado(), nombreComunidadEditado);
        boolean comunidad = elementIsDisplayed(comunidadEditado);

        assertTrue("No se edito el nombre de comunidad", comunidad);

       clickElement(baseStructurePage.getBtnFlechaAtras());
       write(baseStructurePage.getInputBusqueda(), nombre);
    }

    public void buscoMiembroEliminar() {
        String step = "Busco un miembro para eliminar";
        log.info(step);

        //codigo para refrescar la pagina
        loginLogic.navegoPaginaWebCC200();

        clickElement(baseStructurePage.getBtnMenu3Puntos());
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());
        write(baseStructurePage.getInputBusqueda(), nombre);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombre);

        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        assertTrue("No se visualizan miembros en la lista", miembroVisible);
    }

    public void validoEliminarmiembroEstructuraBase() {
        String step = "Valido que puedo eliminar un miembro desde estructura base";
        log.info(step);

        //codigo para refrescar la pagina
        loginLogic.navegoPaginaWebCC200();

        clickElement(baseStructurePage.getBtnMenu3Puntos());
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());
        write(baseStructurePage.getInputBusqueda(), nombre);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombre);
        boolean miembroVisible = existsElement(locatorMiembro);

        assertFalse("Se visualiza el contacto en la lista", miembroVisible);
    }

    public void agregoMiembroEquipoTrabajo() {
        String step = "Agrego un miembro desde equipo de trabajo";
        log.info(step);

        clickElement(baseStructurePage.getBtnMenu3Puntos() );
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnEquipoDeTrabajo());
        clickElement(baseStructurePage.getBtnAgregarMiembroET());
        clickElement(baseStructurePage.getCboxTipoDeDocumento());
        clickElement(baseStructurePage.getCboxOpcionV());
        write(baseStructurePage.getInputCedula(), ciET);
        clickElement(baseStructurePage.getCboxRolDeMiembro());
        clickElement(baseStructurePage.getCboxOpcionRol());
        clickElement(baseStructurePage.getBtnConsultarBD());

        //validacion de que cargo el nombre del usuario a traves de la api
        String nombreValor = String.format(baseStructurePage.getInputNombreValor(), nombreET);

        try {
            waitForElementToBeVisible(nombreValor);
        } catch (TimeoutException e) {
            if (existsElement(baseStructurePage.getLbCedulaRegistrada())) {
                fail("Esta cédula ya esta registrada");
            } else {
                fail("La API no respondio en el tiempo esperado");
            }
        }

        clickElement(baseStructurePage.getInputEmail());
        write(baseStructurePage.getInputEmail(), emailET);
        clickElementActions(baseStructurePage.getCboxGenero());
        clickElementActions(baseStructurePage.getCboxGeneroMET());
        write(baseStructurePage.getInputNumeroTlf(), tlfET);
        clickElement(baseStructurePage.getBtnSiguienteDatosPersonales());
        write(baseStructurePage.getInputNombreUBCH(), nombreUBCHET);
        write(baseStructurePage.getInputNombreComunidad(), nombreComunidadET);
        write(baseStructurePage.getInputNombreCalle(), nombreCalleET);
        clickElement(baseStructurePage.getBtnAgregarMiembro());

        boolean lbEquipoTrabajoAgregado = elementIsDisplayed(baseStructurePage.getLbSeAgregoEquipoTrabajo()) ;

        assertTrue("No se visualiza mensaje 'Miembro agregado con éxito.'", lbEquipoTrabajoAgregado);

        clickElement(baseStructurePage.getBtnOkPopupMiembroETAgregado());
    }

    public void validoAgregoMiembroEquipoTrabajo() {
        String step = "Valido que se agrego el miembro a equipo de trabajo";
        log.info(step);

        //codigo para refrescar la pagina
        loginLogic.navegoPaginaWebCC200();
        clickElement(baseStructurePage.getBtnMenu3Puntos());
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombreET);

        write(baseStructurePage.getInputBusqueda(), nombreET);

        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        assertTrue("No se visualiza el contacto en la lista", miembroVisible);

        boolean miembroEquipoTrabajo = elementIsDisplayed(baseStructurePage.getLbRolEquipoTrabajo());

        assertTrue("No se visualizan miembros con alguna etiqueta de equipo de trabajo en la lista", miembroEquipoTrabajo);
    }

    public void eliminarMiembroEquipoTrabajo() {
        String step = "Eliminar un miembro desde equipo de trabajo";
        log.info(step);

        String detalles = String.format(baseStructurePage.getBtnDetallesMiembro(), nombreET);

        clickElement(detalles);
        clickElement(baseStructurePage.getBtnMenu3PuntosDetalles());
        clickElement(baseStructurePage.getBtnEliminar());

        boolean lbMiembroEliminadoET =  elementIsDisplayed(baseStructurePage.getLbEliminadoEquipoDeTrabajo());

        assertTrue("No se visualiza el mensaje 'Miembro eliminado con éxito.'", lbMiembroEliminadoET);

        clickElement(baseStructurePage.getBtnOkPopupMiembroETAgregado());
    }

    public void validoVerDetallesMiembroEquipoTrabajo() {
        String step = "Valido que puedo ver los detalles de un miembro desde equipo de trabajo";
        log.info(step);

        clickElement(baseStructurePage.getBtnListadoMiembros());
        write(baseStructurePage.getInputBusqueda(), nombreET);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombreET);

        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        assertTrue("No se visualiza el contacto en la lista", miembroVisible);

        String detalles = String.format(baseStructurePage.getBtnDetallesMiembro(), nombreET);

        clickElement(detalles);

        boolean lbDatosPersonales = elementIsDisplayed(baseStructurePage.getLbDatosPersonales());

        assertTrue("No se visualiza el etiqueta Datos personales",lbDatosPersonales);

        boolean lbOtrosDatos = elementIsDisplayed(baseStructurePage.getLbOtrosDatos());

        assertTrue("No se visualiza el etiqueta Otros datos", lbOtrosDatos);

        //boton para retroceder, es una flecha
        clickElement(baseStructurePage.getBtnFlechaAtras());
    }

    public void ingresoListadoMiembrosEquipoTrabajo() {
        String step = "Ingreso a listado de miembros de equipo de trabajo";
        log.info(step);

        clickElement(baseStructurePage.getBtnMenu3Puntos() );
        clickElement(baseStructurePage.getBtnMenuEstructuraBase());
        waitForElementToBeInvisible(baseStructurePage.getLoading());
        clickElementActions(baseStructurePage.getBtnListadoMiembros());

        String filtroEstado = String.format(baseStructurePage.getRbtnFiltro(), "Equipo de trabajo");

        clickElementJs(baseStructurePage.getBtnFiltroListado());
        clickElementJs(filtroEstado);
    }

    public void validoVerListadoMiembrosEquipoTrabajo() {
        String step = "Valido que puedo ver el listado de miembros de equipo trabajo";
        log.info(step);

        boolean miembroEquipoTrabajo = elementIsDisplayed(baseStructurePage.getLbRolEquipoTrabajo());

        assertTrue("No se visualizan miembros con alguna etiqueta de equipo de trabajo en la lista", miembroEquipoTrabajo);
    }

    public void validoBuscarMiembroPorNombre() {
        String step = "Valido que puedo buscar el miembro por nombre";
        log.info(step);

        clickElement(baseStructurePage.getBtnListadoMiembros());

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombreET);

        write(baseStructurePage.getInputBusqueda(), nombreET);

        boolean miembroVisible = elementIsDisplayed(locatorMiembro);

        assertTrue("No se visualiza el contacto en la lista", miembroVisible);
    }

    public void editarMiembroDeEquipo() {
        String step = "Edito un miembro desde equipo de trabajo";
        log.info(step);

        clickElement(baseStructurePage.getBtnListadoMiembros());

        write(baseStructurePage.getInputBusqueda(), nombreET);

        String detalles = String.format(baseStructurePage.getBtnDetallesMiembro(), nombreET);

        clickElement(detalles);
        clickElement(baseStructurePage.getBtnMenu3PuntosDetalles());
        clickElement(baseStructurePage.getBtnEditar());
        write(baseStructurePage.getInputNombreComunidad(), nombreComunidadEditado);
        clickElement(baseStructurePage.getBtnContinuar());
        clickElement(baseStructurePage.getBtnOkPopupMiembroPorAprobar());

        //boton para retroceder, es una flecha
        clickElement(baseStructurePage.getBtnFlechaAtras());
    }

    public void validoEditarMiembroDeEquipo() {
        String step = "Valido que puedo editar el miembro desde equipo de trabajo";
        log.info(step);

        clickElement(baseStructurePage.getBtnListadoMiembros());

        write(baseStructurePage.getInputBusqueda(), nombreET);

        String detalles = String.format(baseStructurePage.getBtnDetallesMiembro(), nombreET);

        clickElement(detalles);

        String comunidadEditado = String.format(baseStructurePage.getInputNombreComunidadEditado(), nombreComunidadEditado);
        boolean comunidad = elementIsDisplayed(comunidadEditado);

        assertTrue("No se edito el nombre de comunidad", comunidad);

        //boton para retroceder, es una flecha
        clickElement(baseStructurePage.getBtnFlechaAtras());
    }

    public void validoEliminoMiembroEquipoDeTrabajo() {
        String step = "Valido que elimine un miembro desde equipo de trabajo";
        log.info(step);

        String locatorMiembro = String.format(baseStructurePage.getBtnMiembroDeLista(), nombreET);

        write(baseStructurePage.getInputBusqueda(), nombreET);

        boolean miembroVisible = existsElement(locatorMiembro);

        assertFalse("Se visualiza el miembro en la lista", miembroVisible);
    }
}
