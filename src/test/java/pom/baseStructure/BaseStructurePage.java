package pom.baseStructure;

import lombok.Getter;

@Getter

public class BaseStructurePage {

    //pantalla inicial
    private final String btnMenu3Puntos =  "//div[@class='profile']";
    private final String btnMenuEstructuraBase = "//div[@class='click-profile']/div[.='Estructura base']";
    private final String btnMenuInicio = "//div[@class='click-profile']/div[.='Inicio']";
    private final String btnAgregarMiembros ="//div[@class='choose-set']/div[.='Agregar miembro']";

    //validar cedula
    private final String inputCedula = "//input[@name='Cédula']";
    private final String cboxTipoDeDocumento ="//div[contains(text(),'Tipo de Documento')]";
    private final String cboxOpcionV = "//div[contains(text(),'V')]";
    private final String btnConsultarBD ="//button[contains(text(),'Consultar base de datos')]";
    private final String lbCedulaRegistrada = "//div[.='Esta cédula ya fue registrada']";
    private final String cboxResponsabilidad = "//div[.='Responsabilidad']";
    private final String cboxResponsabilidadOpcion = "//div[.='%1$s']";

    //datos personales
    private final String inputNombre = "//input[@name='name']";
    private final String inputNombreValor = "//input[@value='%1$s']";
    private final String inputEmail ="//input[@name='email']";
    private final String inputEmailEditado = "//div[.='%1$s']";
    private final String cboxGenero ="//div[contains(text(),'M o F')]";
    private final String cboxGeneroM ="//div[.='M']";
    private final String cboxGeneroMET = "//div[.='M']";
    private final String inputNumeroTlf ="//input[@name='phone']";
    private final String btnSiguienteDatosPersonales ="//button[contains(text(),'Siguiente')]";

    //otros datos
    private final String inputNombreUBCH ="//input[@name='ubchName']";
    private final String inputNombreComunidad ="//input[@name='communityName']";
    private final String inputNombreComunidadEditado= "//span[contains(text(),'%1$s')]";
    private final String inputNombreCalle ="//input[@name='streetName']";
    private final String btnAgregarMiembro ="//button[contains(text(),'Agregar')]";
    private final String lbMiembroAgregadoPorAprobar =" //h5[contains(text(),'Esta acción requiere aprobación, te notificaremos en cuanto sea procesado.')]";
    private final String btnOkPopupMiembroPorAprobar ="(//button[contains(text(),'OK')])[last()]";

    //listado miembros
    private final String loading = "//div[@id='loading']";
    private final String btnListadoMiembros ="//div[@class='choose-set']/div[.='Listado de miembros']"; //div[@class='box-set']/*[*='Listado de miembros']  // "/html/body/div/div[2]/div[2]/div/div/div/div[2]/div[2]";  //
    private final String lbListaDeMiembros = "//div[@class='wrp-title']/*[contains(text(),'Lista de miembros')]";
    private final String btnFiltroListado ="//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium add-group css-1yxmbwk']";
    private final String rbtnFiltro ="//label[.='%1$s']";

    //detalles de miembros
    private final String inputBusqueda ="//input[@type='text']";
    private final String btnMiembroDeLista ="//div[@class='MuiGrid-root MuiGrid-container MuiGrid-item MuiGrid-spacing-xs-2 css-7aty7k']/div[contains(.,'%1$s')]";
    private final String btnDetalles ="//button[contains(text(),'Ver detalle')]";
    private final String lbFiltrarPor = "//h2[contains(text(), 'Filtrar')]";
    private final String btnDetallesMiembro ="(//div[.='%1$s']//ancestor::div[@class='MuiCardContent-root css-1qw96cp'])//following-sibling::div[@class='MuiCardActions-root MuiCardActions-spacing css-3zukih']//button[.='Ver detalle']";
    private final String btnMenu3PuntosDetalles = "//button[@id='long-button']";
    private final String lbDatosPersonales = "//h6[.='Información Personal']";
    private final String lbOtrosDatos = "//h6[.='Otros datos']";
    private final String btnEliminar = "//div[@class='MuiGrid-root MuiGrid-container css-1d3bbye'][contains(.,'Eliminar')]";
    private final String cboxTipoDeDocumentoEliminar ="//div[@class='react-select-container css-b62m3t-container']"; ////input[contains(@id,'react-select-2-input')]
    private final String cboxOpcionVEliminar = "//div[@id='react-select-2-option-1']";
    private final String btnEnviarEliminar = "//button[contains(text(),'OK')]";

    // equipo de trabajo
    private final String btnEquipoDeTrabajo ="//div[@class='choose-set']/div[.='Equipo de trabajo ']";
    private final String btnAgregarMiembroET ="//button[contains(text(),'Agregar Miembro')]";
    private final String lbRolEquipoTrabajo = "//span[contains(text(),'FORMACIÓN') or contains(text(),'ELECTORAL Y APC') or contains(text(),'MOVILIZACIÓN Y EVENTOS') or contains(text(),'MUJERES') or contains(text(),'ECONOMÍA PRODUCTIVA') or contains(text(),'JUVENTUD') or contains(text(),'COMUNAS Y MOVIMIENTOS SOCIALES') or contains(text(),'TRABAJADORES') or contains(text(),'MISIONES Y GRANDES MISIONES') or contains(text(),'PROPULSOR') or contains(text(),'DEFENSA INTEGRAL DE LA NACIÓN')]";
    private final String lbRolJefe = "//span[contains(text(),'JEFE DE COMUNIDAD UPPAZ') or contains(text(),'JEFE DE CALLE') or contains(text(),'JEFE DE UBCH')]";
    private final String cboxRolDeMiembro = "//div[contains(text(),'Rol de miembro')]";
    private final String cboxOpcionRol = "//div[contains(text(),'FORMACIÓN')]";
    private final String lbSeAgregoEquipoTrabajo = "//h5[contains(text(),'Miembro agregado con éxito.')]";
    private final String btnOkPopupMiembroETAgregado ="//button[contains(text(),'OK')]";
    private final String lbEliminadoEquipoDeTrabajo = "//h5[contains(text(),'Miembro eliminado con éxito')]";
    private final String btnEditar = "//div[contains(text(),'Editar')]";
    private final String btnContinuar ="//button[contains(text(),'Continuar')]";
    private final String lbETEditado = "//h5[contains(text(),'Miembro editado con éxito.')]";

    //otros elementos
    private final String elementoDeListaTodos =  "//span[contains(text(),'ACEPTADO') or contains(text(),'DECLINADO')]";
    private final String elementoDeLaLista = "//span[contains(.,'%1$s')]";
    private final String btnFlechaAtras = "//span[contains(text(), 'arrow_back')]";
}