package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

@Log4j2
public class Hooks {

	/**
	 * Declaración de variables estáticas y constantes.
	 */
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static Actions action;
	public static String aux = null;
	public static String auxTwo = null;
	public static String auxThree = null;
	public static Set<Cookie> cookies;

	/**
	 * Inicializa los componentes del WebDriver antes de cada prueba.
	 * Configura el navegador Chrome y prepara las herramientas necesarias para la interacción.
	 */
	@Before
	public static void initializeDriver() {
		try {
			try {
				WebDriverManager.chromedriver().clearResolutionCache();
				WebDriverManager.chromedriver().clearDriverCache();
				WebDriverManager.chromedriver().setup();
				System.out.println("[INFO] ChromeDriver descargado y configurado por WebDriverManager.");
			} catch (Exception e) {
				System.err.println("[WARN] No se pudo descargar ChromeDriver automáticamente: " + e.getMessage());
				System.err.println("[WARN] Intentando usar el ChromeDriver ya instalado en el sistema (PATH)...");
			}
			driver = new ChromeDriver(createChromeOptions());
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			action = new Actions(driver);
		} catch (Exception e) {
			System.err.println("[ERROR] No se pudo inicializar ChromeDriver: " + e.getMessage());
			throw new RuntimeException("No se pudo inicializar ChromeDriver. Asegúrate de tener el ejecutable en el PATH o revisa la conexión a internet.", e);
		}
	}

	/**
	 * Crea y configura una instancia de ChromeOptions para WebDriver.
	 * Configura Chrome para que no use sandbox, desactive el uso de memoria compartida y ignore errores de certificado.
	 *
	 * @return Una instancia configurada de ChromeOptions.
	 */
	private static ChromeOptions createChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("ignore-certificate-errors");
		//chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("use-fake-ui-for-media-stream");
		chromeOptions.addArguments("--window-size=1920,1080");
		chromeOptions.addArguments("--disable-extensions");

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 1);
		chromeOptions.setExperimentalOption("prefs", prefs);

		return chromeOptions;
	}

	/**
	 * Después de las pruebas, realiza la limpieza. En caso de fallo, toma una captura de pantalla.
	 * @param scenario El escenario de prueba.
	 */
	@After
	public void tearDown(Scenario scenario) {
		try {
			captureScreenshotOnFailure(scenario);
		} finally {
			closeDriverResources();
		}
	}

	/**
	 * Captura y registra las cookies de la sesión actual
	 *
	 */
	public void captureCookies() {
		cookies = driver.manage().getCookies();
	}

	/**
	 * Carga un conjunto de cookies en el navegador.
	 *
	 * @param cookies El conjunto de cookies a cargar en el navegador.
	 */
	public void loadCookies() {
		try {
			for (Cookie cookie : cookies) {
				driver.manage().addCookie(cookie);
			}
		} catch (Exception e) {
			System.err.println("Error al cargar las cookies: " + e.getMessage());
		}
	}

	/**
	 * Captura una captura de pantalla si el escenario de prueba falla.
	 * @param scenario El escenario de prueba.
	 */
	private void captureScreenshotOnFailure(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "El escenario fallo, referirse a la imagen adjunta.");
			} catch (Exception e) {
				System.err.println("Error al capturar la pantalla: " + e.getMessage());
			}
		}
	}

	/**
	 * Cierra y limpia los recursos del WebDriver.
	 */
	private void closeDriverResources() {
		try {
			closeBrowser();
		} catch (Exception e) {
			System.err.println("Error al cerrar el navegador: " + e.getMessage());
		}
	}

	/**
	 * Cierra todas las instancias del navegador.
	 */
	private static void closeBrowser() {
		if (driver != null) {
			try {
				driver.quit();
				driver = null;
			} catch (WebDriverException e) {
				throw new RuntimeException("Error al cerrar la aplicación 2 veces: " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Valida que un localizador no sea nulo ni vacío.
	 *
	 * @param locator El localizador a validar.
	 * @throws IllegalArgumentException si el localizador es nulo o está vacío.
	 */
	private void validateLocator(String locator) {
		if (locator == null || locator.isEmpty()) {
			throw new IllegalArgumentException("El localizador proporcionado es nulo o está vacío");
		}
	}

	/**
	 * Valida que un objeto WebElement no sea nulo.
	 *
	 * @param element El objeto WebElement a validar.
	 * @throws IllegalArgumentException si el elemento es nulo.
	 */
	private void validateLocator(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("El elemento WebElement proporcionado es nulo");
		}
	}

	/**
	 * Navega a la URL especificada utilizando el WebDriver y maximiza la ventana del navegador.
	 * Espera hasta que la página se haya cargado completamente después de la navegación.
	 *
	 * @param url La URL a la que se navegará.
	 * @throws WebDriverException si ocurre un error al navegar a la URL, al maximizar la ventana, o si la página no se carga completamente en el tiempo esperado.
	 */
	public void navigateToUrl(String url) {
		validateLocator(url);

		try {
			driver.get(url);
			driver.manage().window().maximize();
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al navegar a la URL: " + url, e);
		}
	}

	/**
	 * Refresca la página actual en el navegador.
	 *
	 */
	public void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * Busca y espera hasta que un elemento sea clickeable, basado en un localizador XPath proporcionado.
	 *
	 * @param locator El localizador XPath del elemento a buscar.
	 * @return El WebElement encontrado que es clickeable.
	 * @throws TimeoutException si el elemento no se vuelve clickeable en el tiempo esperado.
	 * @throws NoSuchElementException si el elemento no se puede encontrar.
	 */
	public WebElement findClickableElement(String locator) {
		validateLocator(locator);

		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		} catch (TimeoutException e) {
			throw new TimeoutException("El elemento no se volvió clickeable en el tiempo esperado: " + locator, e);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No se pudo encontrar el elemento con el localizador: " + locator, e);
		}
	}

	/**
	 * Busca y espera hasta que todos los elementos especificados por el localizador XPath estén visibles.
	 *
	 * @param locator El localizador XPath de los elementos a buscar.
	 * @return Una lista de elementos Web que son visibles.
	 * @throws TimeoutException si los elementos no se vuelven visibles en el tiempo esperado.
	 */
	public List<WebElement> findAllVisibleElements(String locator) {
		validateLocator(locator);

		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
		} catch (TimeoutException e) {
			throw new TimeoutException("Los elementos no se volvieron visibles en el tiempo esperado: " + locator, e);
		}
	}

	/**
	 * Espera hasta que un elemento, identificado por un localizador XPath, esté visible en la página.
	 *
	 * @param locator El localizador XPath del elemento a esperar que sea visible.
	 * @throws TimeoutException si el elemento no está visible después del tiempo definido de espera.
	 */
	public void waitForElementToBeVisible(String locator) {
		validateLocator(locator);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch (TimeoutException e) {
			throw new TimeoutException("El elemento no está visible después del tiempo esperado: " + locator, e);
		}
	}

	/**
	 * Espera hasta que un elemento, identificado por un localizador XPath, no esté visible en la página.
	 *
	 * @param locator El localizador XPath del elemento a esperar que sea invisible.
	 * @throws TimeoutException si el elemento sigue visible después del tiempo definido de espera.
	 */
	public void waitForElementToBeInvisible(String locator) {
		validateLocator(locator);

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
		} catch (TimeoutException e) {
			throw new TimeoutException("El elemento sigue visible después del tiempo esperado: " + locator, e);
		}
	}

	/**
	 * Espera hasta que el atributo 'src' de un elemento cambie de una URL inicial/temporal a una que contenga uno de los formatos de imagen especificados.
	 *
	 * @param locator El localizador XPath del elemento a buscar.
	 * @param initialSrcFragment El fragmento inicial del atributo 'src' que se espera cambiar.
	 * @param finalFormat1 El primer formato de imagen final a buscar en el atributo 'src'.
	 * @param finalFormat2 El segundo formato de imagen final a buscar en el atributo 'src'.
	 * @throws TimeoutException si el elemento con el 'src' final esperado no se encuentra en el tiempo esperado.
	 * @throws NoSuchElementException si el elemento con el localizador proporcionado no se puede encontrar.
	 */
	public void waitForElementSrcToChangeFromTo(String locator, String initialSrcFragment, String finalFormat1, String finalFormat2) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("La espera fue interrumpida", e);
		}

		wait.until(webDriver -> {
			String srcValue = element.getAttribute("src");
			return srcValue != null && !srcValue.contains(initialSrcFragment) && (srcValue.contains(finalFormat1) || srcValue.contains(finalFormat2));
		});
	}

	/**
	 * Realiza clic en un elemento identificado por su locator.
	 * Utiliza 'findClickableElement' para localizar el elemento y luego realiza un clic en él.
	 *
	 * @param locator El locator XPath del elemento en el que se realizará clic.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 *        Indica que no se pudo encontrar un elemento con el locator proporcionado.
	 * @throws ElementNotInteractableException si el elemento está presente pero no es interactuable.
	 *        Puede ocurrir si el elemento no es visible o no está habilitado para la interacción.
	 * @throws StaleElementReferenceException si el elemento ya no está presente en el DOM en el momento de interactuar con él.
	 *        Puede ocurrir si la página cambió o se actualizó después de localizar el elemento.
	 */
	public void clickElement(String locator) {
		validateLocator(locator);

		try {
			findClickableElement(locator).click();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (ElementNotInteractableException e) {
			throw new ElementNotInteractableException("El elemento no es interactuable: " + locator);
		} catch (StaleElementReferenceException e) {
			throw new StaleElementReferenceException("El elemento se volvió obsoleto: " + locator);
		}
	}

	/**
	 * Realiza clic en un elemento utilizando acciones (por ejemplo, moverse antes de hacer clic).
	 *
	 * @param locator El localizador XPath del elemento a hacer clic.
	 * @throws NoSuchElementException si el elemento especificado por el localizador no se encuentra.
	 *        Indica que no se pudo encontrar un elemento con el localizador proporcionado.
	 * @throws WebDriverException para capturar y manejar cualquier otra excepción inesperada que pueda ocurrir durante la acción.
	 */
	public void clickElementActions(String locator) {
		validateLocator(locator);

		try {
			WebElement targetElement = findClickableElement(locator);
			action.moveToElement(targetElement).click().perform();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (Exception e) {
			throw new WebDriverException("Error al hacer clic en el elemento: " + locator, e);
		}
	}

	/**
	 * Realiza clic en un elemento utilizando JavaScript, especificado por un localizador XPath.
	 *
	 * @param locator El localizador XPath del elemento a hacer clic.
	 * @throws WebDriverException si hay un error al ejecutar el script de JavaScript.
	 */
	public void clickElementJs(String locator) {
		validateLocator(locator);

		try {
			WebElement element = findClickableElement(locator);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al ejecutar JavaScript para hacer clic en el elemento: " + locator, e);
		}
	}

	/**
	 * Limpia el contenido de un campo de entrada especificado por un localizador.
	 * Utiliza 'findClickableElement' para localizar el elemento y luego limpia su contenido.
	 *
	 * @param locator El localizador XPath del campo de entrada a limpiar.
	 * @throws NoSuchElementException si el elemento especificado por el localizador no se encuentra.
	 *        Indica que no se pudo encontrar un elemento con el localizador proporcionado.
	 * @throws WebDriverException si hay un error al intentar limpiar el elemento.
	 *        Esta excepción podría ocurrir si hay un problema al interactuar con el elemento web.
	 */
	public void clearElement(String locator) {
		validateLocator(locator);

		try {
			findClickableElement(locator).clear();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar limpiar el elemento: " + locator, e);
		}
	}

	/**
	 * Limpia el contenido de un campo de entrada usando la tecla Backspace.
	 * Utiliza 'findClickableElement' para localizar el elemento y luego limpia su contenido.
	 *
	 * @param locator El localizador XPath del campo de entrada a limpiar.
	 * @throws NoSuchElementException si el elemento especificado por el localizador no se encuentra.
	 *        Indica que no se pudo encontrar un elemento con el localizador proporcionado.
	 * @throws WebDriverException si hay un error al intentar limpiar el elemento.
	 *        Esta excepción podría ocurrir si hay un problema al interactuar con el elemento web.
	 */
	public void clearWithBackspace(String locator) {
		validateLocator(locator);

		try {
			WebElement element = findClickableElement(locator);
			element.click();
			element.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar limpiar el elemento: " + locator, e);
		}
	}

	/**
	 * Escribe texto en un elemento identificado por su locator.
	 * @param locator      El locator del elemento en el que se escribirá el texto.
	 * @param textToWrite  El texto que se escribirá en el elemento. No debe ser nulo.
	 * @throws NoSuchElementException          Si el elemento no se encuentra.
	 * @throws ElementNotInteractableException Si el elemento no es interactuable.
	 */
	public void write(String locator, String textToWrite) {
		validateLocator(locator);

		try {
			WebElement element = findClickableElement(locator);
			element.click();
			element.clear();
			element.sendKeys(textToWrite);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (ElementNotInteractableException e) {
			throw new ElementNotInteractableException("El elemento no es interactuable: " + locator);
		}
	}

	/**
	 * Escribe texto en un elemento caracter por caracter identificado por su locator.
	 * @param locator      El locator del elemento en el que se escribirá el texto.
	 * @param textToWrite  El texto que se escribirá en el elemento. No debe ser nulo.
	 * @throws NoSuchElementException          Si el elemento no se encuentra.
	 * @throws ElementNotInteractableException Si el elemento no es interactuable.
	 */
	public void writeFor(String locator, String textToWrite) {
		validateLocator(locator);

		try {
			WebElement element = findClickableElement(locator);
			element.clear();

			for (int i = 0; i < textToWrite.length(); i++) {
				element.sendKeys(String.valueOf(textToWrite.charAt(i)));
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (ElementNotInteractableException e) {
			throw new ElementNotInteractableException("El elemento no es interactuable: " + locator);
		}
	}

	/**
	 * Escribe texto en un elemento utilizando JavaScript. Este método es útil cuando los métodos estándar de Selenium no funcionan.
	 *
	 * @param locator El selector del elemento en el que se escribirá el texto. Debe ser un selector CSS válido.
	 * @param text    El texto que se escribirá en el elemento.
	 * @throws WebDriverException si hay un error al ejecutar el script de JavaScript.
	 */
	public void writeJs(String locator, String text) {
		validateLocator(locator);

		try {
			String typeText = "document.querySelector('" + locator + "').setAttribute('value', '" + text + "')";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(typeText);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al ejecutar el script de JavaScript", e);
		}
	}

	/**
	 * Selecciona un valor de un menú desplegable especificado por su localizador.
	 *
	 * @param locator El localizador del menú desplegable en el que se realizará la selección.
	 * @param valueToSelect El valor que se seleccionará del menú desplegable.
	 * @throws NoSuchElementException si el elemento del menú desplegable no se encuentra en el DOM.
	 * @throws ElementNotInteractableException si el elemento del menú desplegable no es interactuable.
	 */
	public void selectFromDropdownByValue(String locator, String valueToSelect) {
		validateLocator(locator);

		try {
			Select dropdown = new Select(findClickableElement(locator));
			dropdown.selectByValue(valueToSelect);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento del menú desplegable no encontrado: " + locator);
		} catch (ElementNotInteractableException e) {
			throw new ElementNotInteractableException("El elemento del menú desplegable no es interactuable: " + locator);
		}
	}

	/**
	 * Selecciona un valor de un menú desplegable por su índice, especificado por su localizador.
	 *
	 * @param locator El localizador del menú desplegable en el que se realizará la selección.
	 * @param valueToSelect El índice del valor que se seleccionará del menú desplegable.
	 * @throws NoSuchElementException si el elemento del menú desplegable no se encuentra en el DOM.
	 * @throws ElementNotInteractableException si el elemento del menú desplegable no es interactuable.
	 */
	public void selectFromDropdownByIndex(String locator, int valueToSelect) {
		validateLocator(locator);

		try {
			Select dropdown = new Select(findClickableElement(locator));
			dropdown.selectByIndex(valueToSelect);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento del menú desplegable no encontrado: " + locator);
		} catch (ElementNotInteractableException e) {
			throw new ElementNotInteractableException("El elemento del menú desplegable no es interactuable: " + locator);
		}
	}

	/**
	 * Selecciona un valor de un menú desplegable por su texto visible, especificado por su localizador.
	 *
	 * @param locator El localizador del menú desplegable en el que se realizará la selección.
	 * @param valueToSelect El texto visible del valor que se seleccionará del menú desplegable.
	 * @throws NoSuchElementException si el elemento del menú desplegable no se encuentra en el DOM.
	 * @throws ElementNotInteractableException si el elemento del menú desplegable no es interactuable.
	 */
	public void selectFromDropdownByText(String locator, String valueToSelect) {
		validateLocator(locator);

		try {
			Select dropdown = new Select(findClickableElement(locator));
			dropdown.selectByVisibleText(valueToSelect);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento del menú desplegable no encontrado: " + locator);
		} catch (ElementNotInteractableException e) {
			throw new ElementNotInteractableException("El elemento del menú desplegable no es interactuable: " + locator);
		}
	}

	/**
	 * Mueve el cursor sobre un elemento especificado por su localizador.
	 *
	 * @param locator El localizador del elemento sobre el cual se moverá el cursor.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 * @throws WebDriverException si hay un error al realizar la acción de mover el cursor.
	 */
	public void hoverOverElement(String locator) {
		validateLocator(locator);

		try {
			action.moveToElement(findClickableElement(locator)).click().build().perform();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar mover el cursor sobre el elemento: " + locator, e);
		}
	}

	/**
	 * Realiza un doble clic en un elemento especificado por su localizador.
	 *
	 * @param locator El localizador del elemento en el que se realizará un doble clic.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 * @throws WebDriverException si hay un error al realizar el doble clic.
	 */
	public void doubleClick(String locator) {
		validateLocator(locator);

		try {
			action.doubleClick(findClickableElement(locator)).perform();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar realizar un doble clic en el elemento: " + locator, e);
		}
	}

	/**
	 * Realiza un clic derecho en un elemento especificado por su localizador.
	 *
	 * @param locator El localizador del elemento en el que se realizará un clic derecho.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 * @throws WebDriverException si hay un error al realizar el clic derecho.
	 */
	public void rightClick(String locator) {
		validateLocator(locator);

		try {
			action.contextClick(findClickableElement(locator)).perform();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar realizar un clic derecho en el elemento: " + locator, e);
		}
	}

	/**
	 * Cambia el contexto del controlador al iFrame especificado por su ID.
	 *
	 * @param iFrameID El ID del iFrame al que se cambiará.
	 * @throws NoSuchFrameException si el iFrame con el ID especificado no se encuentra.
	 * @throws WebDriverException si hay un error al cambiar al iFrame.
	 */
	public void switchToiFrame(int iFrameID) {
		try {
			driver.switchTo().frame(iFrameID);
		} catch (NoSuchFrameException e) {
			throw new NoSuchFrameException("iFrame no encontrado con ID: " + iFrameID, e);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar cambiar al iFrame con ID: " + iFrameID, e);
		}
	}

	/**
	 * Cambia el contexto del controlador al marco padre del marco actual.
	 *
	 * @throws WebDriverException si hay un error al cambiar al marco padre.
	 */
	public void switchParentFrame() {
		try {
			driver.switchTo().parentFrame();
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar cambiar al marco padre", e);
		}
	}

	/**
	 * Descarta la alerta actualmente activa.
	 *
	 * @throws NoAlertPresentException si no hay ninguna alerta presente.
	 * @throws WebDriverException si hay un error al descartar la alerta.
	 */
	public void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException e) {
			throw new NoAlertPresentException("No hay ninguna alerta presente para descartar", e);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar descartar la alerta", e);
		}
	}

	/**
	 * Obtiene el texto de un elemento especificado por su localizador.
	 *
	 * @param locator El localizador del elemento del cual se obtendrá el texto.
	 * @return El texto del elemento.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 * @throws WebDriverException si hay un error al obtener el texto del elemento.
	 */
	public String textFromElement(String locator) {
		validateLocator(locator);

		try {
			return findClickableElement(locator).getText();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar obtener el texto del elemento: " + locator, e);
		}
	}

	/**
	 * Obtiene el texto de un WebElement dado.
	 *
	 * @param locator El WebElement del cual se obtendrá el texto.
	 * @return El texto del elemento.
	 * @throws WebDriverException si hay un error al obtener el texto del elemento.
	 */
	public String readFromElement(WebElement locator) {
		validateLocator(locator);

		try {
			return locator.getText();
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al intentar obtener el texto del elemento", e);
		}
	}

	/**
	 * Obtiene el valor del atributo 'value' de un elemento especificado por su localizador.
	 *
	 * @param locator El localizador del elemento.
	 * @return El valor del atributo 'value' del elemento.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 */
	public String textFromElementAttribute(String locator, String attribute) {
		validateLocator(locator);

		try {
			return findClickableElement(locator).getAttribute(attribute);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		}
	}

	/**
	 * Verifica si un elemento especificado por su localizador está habilitado.
	 *
	 * @param locator El localizador del elemento.
	 * @return true si el elemento está habilitado, false de lo contrario.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 */
	public boolean elementEnabled(String locator) {
		validateLocator(locator);

		try {
			return findClickableElement(locator).isEnabled();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		}
	}

	/**
	 * Espera hasta que un elemento esté presente y verifica si está habilitado.
	 *
	 * @param locator El localizador CSS del elemento a verificar.
	 * @return true si el elemento está habilitado, false en caso contrario.
	 * @throws NoSuchElementException si el elemento no se puede encontrar después del tiempo de espera.
	 */
	public boolean isElementEnabledByCss(String locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
			return element.isEnabled();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No se pudo encontrar el elemento con el localizador: " + locator, e);
		}
	}

	/**
	 * Verifica si un elemento especificado por su localizador existe en el DOM
	 * y está visible para el usuario en la página.
	 *
	 * @param locator El localizador del elemento.
	 * @return true si el elemento está visible, false de lo contrario.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 */
	public boolean elementIsDisplayed(String locator) {
		validateLocator(locator);

		try {
			return findClickableElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		}
	}

	/**
	 * Verifica si un elemento especificado por su localizador está seleccionado.
	 *
	 * @param locator El localizador del elemento.
	 * @return true si el elemento está seleccionado, false de lo contrario.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 */
	public boolean elementIsSelected(String locator) {
		validateLocator(locator);

		try {
			return findClickableElement(locator).isSelected();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		}
	}

	/**
	 * Verifica si el texto de un elemento especificado por su localizador está vacío.
	 *
	 * @param locator El localizador del elemento.
	 * @return true si el texto del elemento está vacío, false de lo contrario.
	 * @throws NoSuchElementException si el elemento no se encuentra en el DOM.
	 */
	public boolean isTextEmpty(String locator) {
		validateLocator(locator);

		try {
			return textFromElement(locator).isEmpty();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		}
	}

	/**
	 * Encuentra y devuelve todos los elementos visibles que coinciden con el localizador dado.
	 *
	 * @param locator El localizador CSS o XPath de los elementos a buscar.
	 * @return Una lista de elementos WebElement que son visibles y coinciden con el localizador.
	 * @throws NoSuchElementException si no se encuentran elementos con el localizador dado.
	 */
	public List<WebElement> findAllVisibleElementsByLocator(String locator) {
		validateLocator(locator);

		try {
			return findAllVisibleElements(locator);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No se encontraron elementos con el localizador: " + locator);
		}
	}

	/**
	 * Obtiene los textos de todos los elementos visibles que coinciden con el localizador dado.
	 *
	 * @param locator El localizador CSS o XPath de los elementos a buscar.
	 * @return Una lista de cadenas que representan el texto de cada elemento encontrado.
	 * @throws NoSuchElementException si no se encuentran elementos con el localizador dado.
	 */
	public List<String> readFromElements(String locator) {
		validateLocator(locator);

		try {
			List<WebElement> list = findAllVisibleElementsByLocator(locator);
			List<String> chainList = new ArrayList<>();

			for (WebElement e : list) {
				chainList.add(e.getText());
			}
			return chainList;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No se encontraron elementos con el localizador: " + locator);
		}
	}

	/**
	 * Obtiene el valor del atributo 'value' de todos los elementos visibles que coinciden con el localizador dado.
	 *
	 * @param locator El localizador CSS o XPath de los elementos a buscar.
	 * @return Una lista de cadenas que representan el valor del atributo 'value' de cada elemento encontrado.
	 * @throws NoSuchElementException si no se encuentran elementos con el localizador dado.
	 */
	public List<String> readFromElementsAttribute(String locator) {
		validateLocator(locator);

		try {
			List<WebElement> list = findAllVisibleElementsByLocator(locator);
			List<String> chainList = new ArrayList<>();

			for (WebElement e : list) {
				chainList.add(e.getAttribute("value"));
			}
			return chainList;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("No se encontraron elementos con el localizador: " + locator);
		}
	}

	/**
	 * Obtiene el identificador de la ventana actual.
	 *
	 * @return El identificador de la ventana actual.
	 */
	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	/**
	 * Cambia el control del WebDriver a la primera ventana diferente encontrada.
	 *
	 * @param currentWindowHandle El identificador de la ventana actual.
	 */
	public void switchToDifferentWindow(String currentWindowHandle) {
		for (String windowHandle : driver.getWindowHandles()) {
			if (!currentWindowHandle.equals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
	}

	/**
	 * Abre una nueva pestaña y devuelve los identificadores de la pestaña principal y la nueva pestaña.
	 *
	 * @return Una lista de cadenas que contiene los identificadores de la pestaña principal y la nueva pestaña.
	 * @throws WebDriverException si hay un error al manejar las pestañas del navegador.
	 */
	public List<String> newTab() {
		String mainTab = driver.getWindowHandle();
		String secondTab = "";

		try {
			((JavascriptExecutor) driver).executeScript("window.open()");
			Set<String> handles = driver.getWindowHandles();

			for (String actual : handles) {
				if (!actual.equalsIgnoreCase(mainTab)) {
					driver.switchTo().window(actual);
					secondTab = actual;
				}
			}

			List<String> tabs = new ArrayList<>();
			tabs.add(mainTab);
			tabs.add(secondTab);
			return tabs;
		} catch (WebDriverException e) {
			throw new WebDriverException("Error al manejar nuevas pestañas", e);
		}
	}

	/**
	 * Verifica si existe un elemento en el DOM de la página, identificado por su localizador XPath.
	 * NO USAR PARA VALIDAR SI ES VISIBLE UN ELEMENTO
	 *
	 * @param locator El localizador XPath del elemento a verificar.
	 * @return true si el elemento existe, false de lo contrario.
	 */
	public boolean existsElement(String locator) {
		validateLocator(locator);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			return true;
		} catch (NoSuchElementException | TimeoutException e) {
			return false;
		}
	}

	/**
	 * Obtiene la URL de la página actual.
	 *
	 * @return La URL de la página actual.
	 */
	public String getUrlFromPage() {
		return driver.getCurrentUrl();
	}

	/**
	 * Hace visible un elemento de tipo 'file' en la página.
	 */
	public void makeFileInputElementVisible() {
		String script = "document.getElementById('file').style.visibility = 'visible';";
		((JavascriptExecutor) driver).executeScript(script);
	}

	/**
	 * Escribe texto en un campo de entrada visible en la página.
	 * Nota: Asegúrese de que el campo de entrada esté visible antes de llamar a este método.
	 *
	 * @param text El texto a escribir en el campo de entrada.
	 */
	public void typeTextInVisibleInputField(String text) {
		String script = "document.getElementById('inputField').value = arguments[0];";
		((JavascriptExecutor) driver).executeScript(script, text);
	}

	/**
	 * Desplaza la vista hasta un elemento especificado por su localizador XPath.
	 *
	 * @param locator El localizador XPath del elemento al que se desplazará la vista.
	 * @throws NoSuchElementException si el elemento no se encuentra en la página.
	 */
	public void scroll(String locator) {
		validateLocator(locator);

		try {
			WebElement locate = findClickableElement(locator);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", locate);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Elemento no encontrado: " + locator);
		}
	}

	/**
	 * Sube un archivo a un input de tipo file en la página web.
	 *
	 * @param filePath La ruta del archivo a subir.
	 * @param fileInputClass La clase CSS del elemento input de tipo file.
	 */
	public static void uploadFileClassName(String filePath, String fileInputClass) {
		try {
			java.io.File file = new java.io.File(filePath);
			WebElement uploadElement = driver.findElement(By.className(fileInputClass));
			uploadElement.sendKeys(file.getAbsolutePath());
			System.out.println("Archivo subido exitosamente: " + filePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No se pudo subir el archivo: " + filePath);
		}
	}

	/**
	 * Sube un archivo a un input de tipo file en la página web utilizando su XPath.
	 *
	 * @param filePath La ruta del archivo a subir.
	 * @param fileInputXPath El XPath del elemento input de tipo file.
	 */
	public void uploadFileXPath(String filePath, String fileInputXPath) {
		try {
			java.io.File file = new java.io.File(filePath);
			WebElement uploadElement = driver.findElement(By.xpath(fileInputXPath));
			uploadElement.sendKeys(file.getAbsolutePath());
			System.out.println("Archivo subido exitosamente: " + filePath);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("No se pudo subir el archivo: " + filePath);
		}
	}

	/**
	 * Espera hasta que una alerta esté presente y luego la acepta.
	 *
	 * @throws NoSuchElementException si no se encuentra ninguna alerta en el tiempo de espera especificado.
	 */
	public void acceptAlert() {
		try {
			// Jose: que esperanza la mia quien hizo esto?
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert == null) {
				throw new NoSuchElementException("No se encontró ninguna alerta en el tiempo de espera especificado.");
			}
			alert.accept();
		} catch (Exception e) {
			throw new NoSuchElementException("No se encontró ninguna alerta en el tiempo de espera especificado.");
		}
	}
}

