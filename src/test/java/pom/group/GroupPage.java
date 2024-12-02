package pom.group;

import lombok.Getter;

@Getter
public class GroupPage {

    private final String btnNuevogrupo2 = "//button[@class='MuiButtonBase-root MuiIconButton-root add-group']";
    private final String abrirNuevoGrupo = "//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiMenu-paper')]";
    private final String ingresarNombreGrupo = "//input[@name='name']";
    private final String ingresarDescripcionGrupo = "//textarea[@name='description']";
    private final String checkBoxPrimContac = "//*[@id='left-bar-main']/div[2]/div[1]/span";
    private final String checkBoxSegContac = "//*[@id='left-bar-main']/div[2]/div[2]/span";
    private final String checkBoxContacto = "//div[@class='name-room']//*[contains(text(),'%1$s')]/ancestor::div[@class='rooms']//input[@type='checkbox']";;
    private final String lbMiembro = "(//div[@class='selected-group-user'])";
    private final String btnFoto = "//div[@class='profile-picture center-setting']//div[@class='image-change']/img";
    private final String btnCrearGrupoInhab = "button.btn-change[disabled]";
    private final String inputMensaje = "//div[@class='chat-input']/input[1]";
    private final String btnCreargrupo = "//span[@class='MuiButton-label']";
    private final String inputEscribMensaje = "//div[@id='bottom-bar-chat']//input[@type='text']";
    private final String btnEnviarMensaje2 = "//*[name()='path' and @d='M2.01 21L23 12 2.01 3 2 10l15 2-15 2z']";
    private final String lbMensajeEnviado = "(//div[p[normalize-space(.)='Hola como estas?']])[last()]";
    private final String btn3PuntosChat = "//div[@class='top-chat-content']//*[@class='MuiSvgIcon-root']//*";
    private final String btnInformacion = "//div[@class='content-more-chat']//*[contains(text(),'Información')]";
    private final String nombreEnInfo = "//*[contains(text(),'%1$s')]";
    private final String btnEliminarGrupo = "//div[@class='content-more-chat']//*[contains(text(),'Eliminar Chat')]";
    private final String btnConfirmarEliminarGrupo = "//button[contains(text(),'Eliminar')]";
    private final String loading = "//div[@id='loading']";
    private final String btnNuevogrupo = "//button[@class='MuiButtonBase-root MuiIconButton-root add-group']";
    private final String btnChatCreado = "//div[@class='name-room']//*[contains(text(),'%s')]";
    private final String inputBuscarGrupo = "//input[@placeholder='Buscar usuarios o grupos']";
    private final String checkBoxChat = "//div[@class='name-room']//*[contains(text(),'%s')]";
    private final String nombreChat = "//div[@class='profile-chat']/h3[.='%s']";
    private final String btnConfiguracion = "//div[@class='content-more-chat']//*[contains(text(),'Configuración')]//parent::div";
    private final String btnMiembros = "//div[@class='chat-name-s']//*[contains(text(),'Miembros')]";
    private final String lblMiembrosDelGrupo = "//h4[.='Miembros del grupo']";
    private final String miembroGrupo = "//div[@class='selected-group-user']";
    private final String btnEnviarMensaje = "//div[@id='bottom-bar-chat']//div[@class='send']";
    private final String btnNombre = "//div[@class='chat-name-s']//*[contains(text(),'Nombre')]";
    private final String inputCambiarNombre = "//div[@class='chat-name-box']//input";
    private final String btnGuardar = "//button[contains(text(),'Guardar')]";
    private final String btnNuevaFotoGrupo = "//div[@class='center-chat-content-no-messages']//img";
    private final String btnGrupoFoto = "//div[@class='chat-name-s' and .//h4[text()='Foto']]";
    private final String btnActFotoGrupo = "//button[contains(@class, 'btn-change') and .//span[contains(text(), 'Actualizar foto')]]";
    private final String btnEmoji = "//*[contains(@d, 'C6.47 2 2 6.48 2 12s4.47')]";
    private final String btnEmojiSonrisa = "//button[@class='epr-btn epr-emoji epr-visible' and @data-unified='1f600']";
    private final String lbEmojiSonrisa = "(//div[@class='message-container']//div[contains(@class,'my-message')]//p[contains(text(), '\uD83D\uDE00')])[last()]";
    private final String lbArchivoEnviadoImg = "//div[@id='img-message']/img";
    private final String lbArchivoEnviadoPdf = "//div[@id='message']/div/p[@class='line']";
    private final String btnMiembro = "//div[@class='chat-name-s' and .//h4[text()='Miembros']]";
    private final String lbGuardarMiemb = "//div[@class='selected-group-user']";
    private final String btnEliminarMiemb = "//*[@d[contains(., '13.41 8.41')]]";
    private final String lbSinNombreGrupo = "//label[@class='input-form-error']";
}