package pom.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import steps.Hooks;

import static org.junit.Assert.*;

public class LoginLogic extends Hooks {

    LoginPage loginPage = new LoginPage();
    private static final Logger log = LogManager.getLogger(LoginLogic.class);

    public void loginFuturo() {
        String step = "Inicio sesion";
        log.info(step);


    }
}
