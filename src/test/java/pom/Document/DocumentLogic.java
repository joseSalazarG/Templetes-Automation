package pom.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pom.login.LoginPage;
import steps.Hooks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DocumentLogic extends Hooks {

    DocumentPage documentPage = new DocumentPage();
    private static final Logger log = LogManager.getLogger(DocumentLogic.class);
    LoginPage loginPage = new LoginPage();

    public void ingresoDocumentos() {
        String step = "Ingreso a documentos";
        log.info(step);

        elementIsDisplayed(loginPage.getImg());
        clickElement(loginPage.getBtnPerfil());

        elementIsDisplayed(loginPage.getLbCC200Web());
        elementIsDisplayed(documentPage.getBtnMenuDocumentos());
        clickElement(documentPage.getBtnMenuDocumentos());
    }

    public void validoVerDocumentosMenu() {
        String step = "Valido que puedo ver los documentos en menu";
        log.info(step);

        String docUno = textFromElement(documentPage.getLbDocUno());
        String docDos = textFromElement(documentPage.getLbDocDos());

        waitForElementToBeInvisible(documentPage.getImgCargando());

        assertAll("Verificar contenido de la URL",
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Ejemplo PPTX Invisible y público", docUno),
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Archivo TXT Público y visible" , docDos)
        );
    }

    public void abroDocumentos() {
        String step = "Abro documentos";
        log.info(step);
        
        waitForElementToBeInvisible(documentPage.getImgCargando());
        clickElement(documentPage.getBtnDocumentos());
    }

    public void validoVerListadoDocumentos() {
        String step = "Valido ver el listado de documentos";
        log.info(step);

        String docUno = textFromElement(documentPage.getLbDocUno());
        String docDos = textFromElement(documentPage.getLbDocDos());

        waitForElementToBeInvisible(documentPage.getImgCargando());

        assertAll("Verificar contenido de la URL",
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Ejemplo PPTX Invisible y público", docUno),
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Archivo TXT Público y visible" , docDos)
        );
    }

    public void abroAudios() {
        String step = "Abro audios";
        log.info(step);

        waitForElementToBeInvisible(documentPage.getImgCargando());
        clickElement(documentPage.getBtnAudios());
    }

    public void validoVerListadoAudios() {
        String step = "Valido ver el listado de audios";
        log.info(step);

        String audioUno = textFromElement(documentPage.getLbArchivoAudio());

        waitForElementToBeInvisible(documentPage.getImgCargando());

        assertEquals("No hay documento con el nombre esperado",
                        "mp3 - Lasso. Invisible y privado modificado", audioUno);
    }

    public void abroVideos() {
        String step = "Abro videos";
        log.info(step);

        waitForElementToBeInvisible(documentPage.getImgCargando());
        clickElement(documentPage.getBtnVideos());
    }

    public void validoVerListadoVideos() {
        String step = "Valido ver el listado de videos";
        log.info(step);

        String videoUno = textFromElement(documentPage.getLbArchivoVideo1());
        String videoDos = textFromElement(documentPage.getLbArchivoVideo2());

        waitForElementToBeInvisible(documentPage.getImgCargando());

        assertAll("Verificar contenido de la URL",
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Video privado subido por jefe de comunidad", videoUno),
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Video invisible pero público" , videoDos)
        );
    }

    public void abroImagenes() {
        String step = "Abro imagenes";
        log.info(step);

        waitForElementToBeInvisible(documentPage.getImgCargando());
        clickElement(documentPage.getBtnImagenes());
    }

    public void validoVerListadoImagenes() {
        String step = "Valido ver el listado de imagenes";
        log.info(step);

        String imgUno = textFromElement(documentPage.getLbArchivoImagen1());
        String imgDos = textFromElement(documentPage.getLbArchivoImagen2());

        waitForElementToBeInvisible(documentPage.getImgCargando());

        assertAll("Verificar contenido de la URL",
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Imagen PNG desde ios", imgUno),
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Prueba JPG" , imgDos)
        );
    }

    public void validoVerListadoOtrosDocumentos() {
        String step = "Valido ver el listado de otros documentos";
        log.info(step);

        String otroDocUno = textFromElement(documentPage.getLbArchivoOtro1());
        String otroDocDos = textFromElement(documentPage.getLbArchivoOtro2());

        waitForElementToBeInvisible(documentPage.getImgCargando());

        assertAll("Verificar contenido de la URL",
                () -> assertEquals("No hay documento con el nombre esperado",
                        "Archivo Wav", otroDocUno),
                () -> assertEquals("No hay documento con el nombre esperado",
                        "archivo Wav desde Android" , otroDocDos)
        );
    }

    public void validoAbrirUnDocumento() {
        String step = "Valido abrir un documento";
        log.info(step);

        String nombreArchivo = "Ejemplo PPTX  Invisible y público";

        String btnVer = String.format(documentPage.getBtnVerArchivo(), nombreArchivo);

        clickElement(btnVer);

        boolean btnDownload = elementIsDisplayed(documentPage.getBtnDescargar());
        boolean btnClose = elementIsDisplayed(documentPage.getBtnCerrar());

        assertAll("Verificar contenido de la vista previa",
                () -> assertTrue("No se visualiza vista previa", btnDownload),
                () -> assertTrue("No se visualiza vista previa", btnClose)
        );

    }

    public void validoAbrirUnAudio() {
        String step = "Valido abrir un audio";
        log.info(step);

        String nombreArchivo = "mp3 - Lasso. Invisible y privado";

        String btnVer = String.format(documentPage.getBtnVerArchivo(), nombreArchivo);

        clickElement(btnVer);

        boolean btnDownload = elementIsDisplayed(documentPage.getBtnDescargar());
        boolean btnClose = elementIsDisplayed(documentPage.getBtnCerrar());

        assertAll("Verificar contenido de la vista previa",
                () -> assertTrue("No se visualiza vista previa", btnDownload),
                () -> assertTrue("No se visualiza vista previa", btnClose)
        );
    }

    public void validoAbrirUnVideo() {
        String step = "Valido abrir un video";
        log.info(step);

        String nombreArchivo = "Video invisible pero público";

        String btnVer = String.format(documentPage.getBtnVerArchivo(), nombreArchivo);

        clickElement(btnVer);

        boolean btnDownload = elementIsDisplayed(documentPage.getBtnDescargar());
        boolean btnClose = elementIsDisplayed(documentPage.getBtnCerrar());

        assertAll("Verificar contenido de la vista previa",
                () -> assertTrue("No se visualiza vista previa", btnDownload),
                () -> assertTrue("No se visualiza vista previa", btnClose)
        );
    }

    public void validoAbrirUnaImagen() {
        String step = "Valido abrir una imagen";
        log.info(step);

        String nombreArchivo = "Prueba JPG";

        String btnVer = String.format(documentPage.getBtnVerArchivo(), nombreArchivo);

        clickElement(btnVer);

        boolean btnDownload = elementIsDisplayed(documentPage.getBtnDescargar());
        boolean btnClose = elementIsDisplayed(documentPage.getBtnCerrar());

        assertAll("Verificar contenido de la vista previa",
                () -> assertTrue("No se visualiza vista previa", btnDownload),
                () -> assertTrue("No se visualiza vista previa", btnClose)
        );
    }

    public void abroOtrosDocumentos() {
        String step = "Abro otros documentos";
        log.info(step);

        waitForElementToBeInvisible(documentPage.getImgCargando());
        clickElement(documentPage.getBtnOtros());
    }

    public void validoAbrirOtrosDocumentos() {
        String step = "Valido abrir otros documentos";
        log.info(step);

        String nombreArchivo = "Archivo Wav";

        String btnVer = String.format(documentPage.getBtnVerArchivo(), nombreArchivo);

        clickElement(btnVer);

        boolean btnDownload = elementIsDisplayed(documentPage.getBtnDescargar());
        boolean btnClose = elementIsDisplayed(documentPage.getBtnCerrar());

        assertAll("Verificar contenido de la vista previa",
                () -> assertTrue("No se visualiza vista previa", btnDownload),
                () -> assertTrue("No se visualiza vista previa", btnClose)
        );

    }
}