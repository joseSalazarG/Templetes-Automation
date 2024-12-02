package pom.conferences;

import lombok.Getter;

@Getter
public class ConferencesPage {

    private final String btnAbrirMenu = "//div[@class='profile']";
    private final String btnConferencias = "//div[@class='click-profile']/div[.='Conferencias']";
    private final String video = "//div[@id='video-message']";
}
