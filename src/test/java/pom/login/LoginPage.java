package pom.login;

import lombok.Getter;

@Getter
public class LoginPage {

    private final String btnPais = "//div[@class='flag ve']";
    private final String inputPais = "//input[@class='search-box']";
    private final String btnUnitedStates = "//span[@class='country-name']";
    private final String inputTelefono = "//input[@class='form-control ']";
    private final String btnEnviarSMS = "//button[contains(text(), 'Enviar SMS')]";
    private final String inputCodigo = "//input[@class='input-form']";
    private final String btnIniciarSesion = "//button[@type='submit']";
    private final String btnNuevoGrupo = "//span[@class='MuiIconButton-label']";
    private final String lbMensaje = "//div[@id='error-form']";
    private final String popUpMensaje = "//h5[contains(text(), 'Seleccione su país e introduzca un número de teléfono válido para continuar')]";
    private final String lbTlfNoRegistrado = "//p[contains(text(), 'Este número de teléfono no está registrado como usuario')]";
    private final String img = "//img[contains(@src, 'https://firebasestorage.googleapis.com/')]";
    private final String btnPerfil = "//div[@class='profile']";
    private final String lbCC200Web = "//p[@class='profile-reserved']";
    private final String btnCerraSesion = "//span[contains(text(),'logout')]";
}