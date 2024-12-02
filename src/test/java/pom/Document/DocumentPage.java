package pom.Document;

import lombok.Getter;

@Getter
public class DocumentPage {

    private final String btnMenuDocumentos = "//div[contains(@class, 'my-settings profile-options')]//h4[text()='Documentos']";
    private final String btnDocumentos = "//a[@href='/docs/document']";
    private final String btnAudios = "//a[@href='/docs/audio']";
    private final String btnVideos = "//a[@href='/docs/video']";
    private final String btnImagenes = "//a[@href='/docs/image']";
    private final String btnOtros = "//a[@href='/docs/other']";
    private final String btnVerArchivo = "//p[contains(text(), '%1$s')]/ancestor::tr//button[@class='mantine-focus-auto mantine-active m_77c9d27d mantine-Button-root m_87cf2631 mantine-UnstyledButton-root']";
    private final String btnDescargar = "//button[.='Descargar']";
    private final String btnCerrar = "//button[@class='mantine-focus-auto mantine-active m_220c80f2 m_606cb269 mantine-Modal-close m_86a44da5 mantine-CloseButton-root m_87cf2631 mantine-UnstyledButton-root']";
    private final String imgCargando = "//div[@id='loading']";
    private final String lbDocUno = "//p[contains(text(), 'Ejemplo PPTX  Invisible y público')]";
    private final String lbDocDos = "//p[contains(text(), 'Archivo TXT Público y visible')]";
    private final String lbDocTres = "//p[contains(text(), 'Excel')]";
    private final String lbArchivoImagen1 = "//p[contains(text(), 'Imagen PNG desde ios')]";
    private final String lbArchivoImagen2 = "//p[contains(text(), 'Prueba JPG')]";
    private final String lbArchivoAudio = "//p[contains(text(), 'mp3 - Lasso. Invisible y privado modificado')]";
    private final String lbArchivoVideo1 = "//p[contains(text(), 'Video privado subido por jefe de comunidad')]";
    private final String lbArchivoVideo2 = "//p[contains(text(), 'Video invisible pero público')]";
    private final String lbArchivoOtro1 = "//p[contains(text(), 'Archivo Wav')]";
    private final String lbArchivoOtro2 = "//p[contains(text(), 'archivo Wav desde Android')]";

}
