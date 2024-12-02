package pom.venezuela;

import lombok.Getter;
@Getter
public class VenezuelaPage {

    private final String btnAbrirMenu = "//div[@class='profile']";
    private final String btnTodosPorVenezuela = "//div[@class='click-profile']/div[.='Todos Por Venezuela']";
    private final String btnAgregarPorComunidad = "/html/body/div/div[2]/div[2]/div/div/div/div[2]/div[1]";
    private final String btnVerListado = "/html/body/div/div[2]/div[2]/div/div/div/div[2]/div[2]";

    // agregar
    private final String comboboxTipoDocumento = "//div[contains(text(),'Tipo de Documento')]";
    private final String comboboxOpcionV = "//div[contains(text(),'V')]";
    private final String inputCedula = "//input[@name='Cédula']";
    private final String btnConsultarBase = "//button[@type='button']";
    private final String lblRespuestaAPI = "//input[@value='PEREZ HERNANDEZ MILAGROS DEL VALLE']";
    private final String inputTelefono = "//input[@class='form-control ']";
    private final String btnAgregar = "//button[contains(text(),'Agregar')]";
    private final String btnOk = "//button[contains(text(),'OK')]";
    private final String lblCedulaRegistrada = "//*[contains(text(),'Esta cédula ya fue registrada')]";
    private final String lblTelefonoRegistrado = "//*[contains(text(),'El número de telefono ya esta registrado')]";

    // listado
    private final String inputBuscar = "//input[@type='text' and @placeholder='Buscar por nombre']";
    private final String lblNombre = "//div[contains(text(),'PEREZ HERNANDEZ MILAGROS DEL VALLE')]";
    private final String lblNombre2 = "//div[contains(text(),'VASQUEZ  JOSE SALVADOR')]";
    private final String lblNombre3 = "//div[contains(text(),'%s')]";
    private final String lblElector1 = "//input[@placeholder='Buscar por nombre']";
    private final String btnVerDetalles = "//*[contains(text(),'Ver detalle')]";
    private final String btnVerDetalles2 = "(//*[contains(text(),'Ver detalle')])[2]";
    private final String lblTelefono = "//span[@class='MuiTypography-root MuiTypography-button css-1pni1vw']";
    private final String btn3Puntos = "//button[@id='long-button']";
    private final String btnEliminar = "//div[contains(text(),'Eliminar')]";
    private final String btnEditar = "//div[contains(text(),'Editar')]";
    private final String btnContinuar = "//button[contains(text(),'Continuar')]";
    private final String btnAtras = "//span[contains(text(),'arrow_back')]";
    private final String lbNumInvalido = "//label[@class='input-form-error' and text()='Número inválido']";
}
