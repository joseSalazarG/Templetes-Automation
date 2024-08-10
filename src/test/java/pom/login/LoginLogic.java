package pom.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pom.baseUrl.UrlConstant;
import steps.Hooks;
import static org.junit.jupiter.api.Assertions.assertAll;

import static org.junit.Assert.*;

public class LoginLogic extends Hooks {

    LoginPage loginPage = new LoginPage();
    private static final Logger log = LogManager.getLogger(LoginLogic.class);

    public void navegoPaginaWebAmyr() {
        String step = "Navego a la pagina web de Amyr";
        log.info(step);

        navigateToUrl(UrlConstant.DEV);
    }
}