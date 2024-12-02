package pom.chatP2P;

import lombok.Getter;

@Getter
public class ChatP2PPage {

    private final String inputBuscarGrupo = "//input[@placeholder='Buscar usuarios o grupos']";
    private final String checkBoxChat = "//div[@class='name-room']//*[contains(text(),'%s')]";
    private final String btn3PuntosChat = "//div[@class='top-chat-content']//*[@class='MuiSvgIcon-root']//*";
    private final String btnEliminarGrupo = "//div[@class='content-more-chat']//*[contains(text(),'Eliminar Chat')]";
    private final String btnConfirmarEliminarGrupo = "//button[contains(text(),'Eliminar')]";
    private final String loading = "//div[@id='loading']";
    private final String btnNuevogrupo = "//button[@class='MuiButtonBase-root MuiIconButton-root add-group']";
    private final String btnChatCreado = "//div[@class='name-room']//*[contains(text(),'%s')]";
    private final String nombreChat = "//div[@class='profile-chat']/h3[.='%s']";
    private final String btnConfiguracion = "//div[@class='content-more-chat']//*[contains(text(),'Configuración')]//parent::div";
    private final String colorTema = "//div[@class='chat-theme-s']//span[@style]";
    private final String btnTema = "//div[@class='chat-theme-s' and .//span[contains(@class, 'theme-color')] and .//h4[text()='Tema']]";
    private final String btnRojo = "//div[@class='col-theme'][div/@style='background: rgb(208, 48, 48);']";
    private final String btnVerde = "//div[@class='col-theme'][div/@style='background: rgb(19, 188, 127);']";
    private final String btnInformacion = "//div[@class='content-more-chat']//*[contains(text(),'Información')]";
    private final String nombreEnInformacion = "//div[@class='back-from-settings' and ./h4[text()='Información']]";
    private final String btnAlias = "//div[@class='chat-name-s']";
    private final String inputCambiarNombre = "//div[@class='chat-name-box']//input";
    private final String btnGuardar = "//button[contains(text(),'Guardar')]";
    private final String lblNuevoAlias = "//div[@class='profile-chat']//*[.='%s']";
}