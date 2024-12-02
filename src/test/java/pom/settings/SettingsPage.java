package pom.settings;

import lombok.Getter;

@Getter
public class SettingsPage {

    private final String loading = "//div[@id='loading']";
    private final String img = "(//img[contains(@src, 'https://firebasestorage.googleapis.com/')])[1]";
    private final String btnMenuPrincipal = "//div[contains(@class, 'profile') and .//img[contains(@src, 'firebasestorage.googleapis.com')]]";
    private final String lbCC200 = "//p[@class='profile-reserved']";
    private final String btnConfig = "//div[contains(@class, 'change-link') and .//*[text()='Configuración']]";
    private final String btnOpcionInfoPerfil = "//div[contains(@class, 'box-set')]//h4[text()='Información Personal']";
    private final String inputNombre = "//input[@name='firstName']";
    private final String btnActualizaNombre = "//button[contains(text(), 'Guardar Datos')]";
    private final String alertaCambioNombre = "//div[contains(text(), 'Nombres guardados')]";
    private final String lbMiPerfil = "//div[@class='my-profile']";
    private final String lbNombrePerfil= "//h3[.='%s']";
    private final String btnInicio = "//*[contains(text(), 'Inicio')]";
    private final String lblCaracteresEspeciales = "//label[contains(@class, 'input-form-error') and text()='No puede contener caracteres especiales']";
    private final String btnOpcionEmail= "//div[contains(@class, 'box-set')]//h4[text()='Email ']";
    private final String inputEmail = "//input[@name='email']";
    private final String btnConfirmarEmail = "//button[contains(text(), 'Actualizar Email')]";
    private final String alertaCambioEmail = "//div[contains(text(), 'Email guardado')]";
    private final String btnVolverEmail = "//span[@class='material-icons']";
    private final String lblAdvEmailSinEstructura = "//html/body/div/div[2]/div[2]/div/div/div/form/div/label";
    private final String lblAdvEmailExiste = "//html/body/div/div[2]/div[2]/div/div/div[1]/div/div[2]";
    private final String btnOpcionFotoPerfil = "//div[contains(@class, 'box-set')]//h4[text()='Foto de Perfil']";
    private final String btnAddFotoPerfil = "//*[@id='center-bar']/div/div[2]/div/img";
    private final String btnActualizaFotoPerfil = "//button[contains(text(), 'Actualizar Foto')]";
    private final String btnVolverFotoPerfil = "//span[contains(text(), 'arrow_back')]";
}