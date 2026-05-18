package pom.home;

import lombok.Getter;

@Getter
public class HomePage {

    // botones en el home
    private final String btnProductos = "//a[@href='/products']";
    private final String inputBusqueda = "(//input)[1]";
    private final String btnBusqueda = "//button[@id='submit_search']";
    private final String CamisaVerdeBusqueda = "((//div[@class='features_items']//child::p)[3])[text()='Fancy Green Top']";
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
