package pom.login;

import lombok.Getter;

@Getter
public class LoginPage {

    private final String InputLogin = "//input[@name='email']";
    private final String InputPassword = "//input[@name='password']";
    private final String btnSubmit = "//button[@type='submit']";
    private final String lbBienvenido = "//h2[.='Bienvenido']";
    private final String btnPerfil = "(//button[@aria-controls='msgs-menu'])[last()]";
    private final String btnCerrarSesion = "//button[.='Cerrar sesión']";
    private final String lbUsuarioInactivo = "//p[contains(text(),'usuario se encuentra deshabilitado')]";
    private final String lbCampoRequerido = "//p[contains(text(),'Se requiere')]";
    private final String lbCaracteresMinimos = "//p[contains(text(),'El email debe tener mínimo 9 caracteres')]";
    private final String lbCaracteresMinimos2 = "//p[contains(text(),'La contraseña debe tener mínimo 8 caracteres')]";
}
