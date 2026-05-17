package pom.register;

import lombok.Getter;

@Getter
public class RegisterPage {

    private final String lbErrorCorreoExistente = "//p[text()='Email Address already exist!']";
    private final String inputNombre = "//input[@data-qa='signup-name']";
    private final String inputCorreo = "//input[@data-qa='signup-email']";
    private final String buttonSignup = "//button[text()='Signup']";

}
