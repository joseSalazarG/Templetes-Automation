package pom.register;

import lombok.Getter;

@Getter
public class RegisterPage {

    private final String btnAbrirMenu = "//div[@class='profile']";
    private final String btnConferencias = "//div[@class='click-profile']/div[.='Registrar 1x10']";
    private final String lbRegistrar = "//h2[@class='setting-title' and text()='Registrar 1x10']";
    private final String loading = "//div[@id='loading']";
    private final String cboxTipoDeDocumento ="//div[contains(text(),'Tipo Doc.')]";
    private final String cboxOpcionV = "//div[contains(text(),'V')]";
    private final String inputCedula = "//input[@name='documentId']";
    private final String btnValidar = "//button[contains(text(), 'Validar')]";
    private final String lbMensaje = "//*[contains(text(), '%s')]";
    private final String btnEliminar = "//span[contains(text(), '%s')]/ancestor::li//button[.//*[@data-testid='DeleteIcon']]";
    private final String inputTelefono = "//input[@class='form-control ' and @placeholder='phone' and @type='tel']";
    private final String btnContinuar = "//button[contains(text(), 'Continuar')]";
    private final String btnPdf = "//button[contains(text(), 'Descargar PDF')]";
}