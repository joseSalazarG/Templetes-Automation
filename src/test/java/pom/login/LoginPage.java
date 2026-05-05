package pom.login;

import lombok.Getter;

@Getter
public class LoginPage {

    // botones en la tienda
    private final String btnLogin = "//a[@href='/login']/parent::li";
    private final String btnCarrito = "//a[@href='/view_cart']/parent::li";
    private final String lbCarritoVacio = "//b[text()='Cart is empty!']";
    private final String btnLogOut = "//a[@href='/logout']/parent::li";
    private final String InputEmail = "//input[@type='email']";
    private final String InputPassword = "//input[@type='password']";
    private final String btnSubmit = "//button[@type='submit']";

    // ejemplos de xpath
    /*
    private final String InputLogin = "//input[@name='login' or @name='login_fake']";
    private final String InputPassword = "//input[@name='password' or @name='password_fake']";
    private final String errorUsuario = "//div[.='Debes llenar el campo usuario.']";
    private final String errorLogin = "//div[.='%s']";
    private final String lbCampoRequerido = "//p[contains(text(),'Se requiere')]";
    private final String lbCaracteresMinimos = "//p[contains(text(),'El email debe tener mínimo 9 caracteres')]";
    private final String lbCaracteresMinimos2 = "//p[contains(text(),'La contraseña debe tener mínimo 8 caracteres')]";
    */
}
