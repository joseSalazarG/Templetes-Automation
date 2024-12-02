package pom.uppaz;

import lombok.Getter;

@Getter
public class UppazPage {

    private final String btnAbrirMenu = "//div[@class='profile']";
    private final String btnUPPAZ = "//div[@class='click-profile']/div[.='UPPAZ']";
    private final String btnAgregarUPPAZ = "/html/body/div/div[2]/div[2]/div/div/div/div[2]/div[1]";
    private final String btnVerListado = "/html/body/div/div[2]/div[2]/div/div/div/div[2]/div[2]";
    private final String btnVerListadoDifusor = "/html/body/div/div[2]/div[2]/div/div/div/div[2]/div[1]";

    // agregar
    private final String comboboxTipoDocumento = "//div[contains(text(),'Tipo de Documento')]";
    private final String comboboxOpcionV = "//div[contains(text(),'V')]";
    private final String comboboxRol = "//div[contains(text(),'Responsabilidad')]";
    private final String comboboxOpcionPorComunidad = "//div[contains(text(),'DE COMUNIDAD')]";
    private final String comboboxOpcionMotorizados = "//div[contains(text(),'MOTORIZADOS')]";
    private final String comboboxOpcionPorCalle = "//div[contains(text(),'DE CALLE')]";
    private final String inputCedula = "//input[@name='Cédula']";
    private final String btnConsultarBase = "//button[@type='button']";
    private final String inputMail = "//input[@name='email']";
    private final String comboboxGenero = "//div[contains(text(),'M o F')]";
    private final String comboboxOpcionFemale = "//div[contains(text(),'F')]";
    private final String lblRespuestaAPI = "//input[@value='PEREZ HERNANDEZ MILAGROS DEL VALLE']";
    private final String lblRespuestaAPI2 = "//div[text()='Bocono']";
    private final String inputTelefono = "//input[@class='form-control ']";
    private final String btnSiguiente = "//button[contains(text(),'Siguiente')]";
    private final String inputUBCH ="//input[@placeholder='Nombre de UBCH']";
    private final String inputComunidad = "//input[@placeholder='Nombre de comunidad']";
    private final String inputCalle = "//input[@placeholder='Nombre de calle']";
    private final String btnAgregar = "//button[contains(text(),'Agregar')]";
    private final String btnOk = "//button[contains(text(),'OK')]";
    private final String lblAvisoAgregar = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 css-1w6ph7q']/h5";
    private final String lblCedulaRegistrada = "//*[contains(text(),'Esta cédula ya fue registrada')]";
    private final String lblTelefonoRegistrado = "//*[contains(text(),'El número de telefono ya esta registrado')]";

    // listado
    private final String inputBuscar = "//input[1]";
    private final String lblNombre = "//div[contains(text(),'Miembro UPPAZ')]";
    private final String lblMiembro1 = "//div[@class='MuiTypography-root MuiTypography-h5 MuiTypography-gutterBottom css-t1nuxs']";
    private final String btnVerDetalles = "//button[contains(text(),'Ver detalle')]";
    private final String btnVerDetallesHernandez = "(//div[.='PEREZ HERNANDEZ MILAGROS DEL VALLE']//ancestor::div[@class='MuiCardContent-root css-1qw96cp'])//following-sibling::div[@class='MuiCardActions-root MuiCardActions-spacing css-3zukih']//button[.='Ver detalle']";
    private final String btnVerDetallesJaimito = "(//div[.='Jaimito Perez']//ancestor::div[@class='MuiCardContent-root css-1qw96cp'])//following-sibling::div[@class='MuiCardActions-root MuiCardActions-spacing css-3zukih']//button[.='Ver detalle']";
    private final String btnVerDetalles2 = "(//button[contains(text(),'Ver detalle')])[2]";
    private final String lblCorreoTest = "//span[contains(text(),'usuario@ejemplo.net')]";
    private final String lblRol = "//span[contains(text(),'%1$s')]";
    private final String btn3Puntos = "//button[@id='long-button']";
    private final String lblTelefono = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-item MuiGrid-grid-xs-6 css-1dts1zg']//span[@class='MuiTypography-root MuiTypography-button css-1pni1vw']";
    private final String btnEliminar = "//div[contains(text(),'Eliminar')]";
    private final String btnEditar = "//div[contains(text(),'Editar')]";
    private final String btnContinuar = "//button[contains(text(),'Continuar')]";
}