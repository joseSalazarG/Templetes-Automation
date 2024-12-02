package pom.foro;

import lombok.Getter;

@Getter
public class ForoPage {

    private final String btnAbrirMenu = "//div[@class='profile']";
    private final String btnConferencias = "//div[@class='click-profile']/div[.='Foro de opinión']";
    private final String foroOpinion = "//h2[@class='setting-title']";
    private final String btnFiltro = "//button[contains(@class, 'add-group') and @aria-label='Filter']";
    private final String btnCiencia = "//*[contains(text(), 'Ciencia')]";
    private final String loading = "//div[@id='loading']";
}