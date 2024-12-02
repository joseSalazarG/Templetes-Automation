package steps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ApiHooks {

    public static String userToken = null;
    public static String hubToken = null;
    public static final String urlToken = "https://hub-cc200.appzone.dev/api/dev/token";
    public static final String credenciales = "{\"username\":\"rvelazquez@nolatech.ai\",\"password\":\"rvelazquez@nolatech.ai\"}";
    public static final String authToken = "505E6z66wBTJUIA95cEe9GAjPs76ydSGM00KUb1PfSHANEry1i";

    /**
     * Obtiene el token de autenticación para realizar solicitudes en la aplicación.
     * @return El token de autenticación.
     */
    public String getHubToken() {
        String baseUrl = "https://hub-cc200.appzone.dev/api/dev/token";
        String json = "{\"username\":\"rvelazquez@nolatech.ai\",\"password\":\"rvelazquez@nolatech.ai\"}";

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "505E6z66wBTJUIA95cEe9GAjPs76ydSGM00KUb1PfSHANEry1i");
        request.contentType(ContentType.JSON);
        request.body(json);

        Response response = request.post(baseUrl);
        int statusCode = response.getStatusCode();
        String cachedToken = null;

        if (statusCode == 200) {
            cachedToken = response.jsonPath().getString("data.token");
        }
        return cachedToken;
    }

    /**
     * Busca un usuario en la aplicación y devuelve su identificador.
     * @return El identificador del usuario.
     * @throws IllegalStateException Si no se encuentran usuarios en la respuesta.
     */
    public String searchUser(String phone) {
        String baseUrl = "https://hub-cc200.appzone.dev/api/dev/user?page=1&count=10&criteria=" + phone;
        hubToken = getHubToken();

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", hubToken);
        request.contentType(ContentType.JSON);

        Response response = request.get(baseUrl);
        List<String> userIds = response.jsonPath().getList("data.users._id");

        if (!userIds.isEmpty()) {
            return userIds.get(0);
        } else {
            throw new IllegalStateException("No se encontraron usuarios en la respuesta.");
        }
    }

    /**
     * Obtiene el token de usuario para realizar solicitudes en nombre del usuario.
     * @return El token de usuario.
     */
    public String getUserToken(String phone) {
        String baseUrl = "https://hub-cc200.appzone.dev/user/usertoken";
        String id = searchUser(phone);
        String json = "{ \"id\": \"" + id + "\" }";

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", hubToken);
        request.contentType(ContentType.JSON);
        request.body(json);

        Response response = request.post(baseUrl);
        int statusCode = response.getStatusCode();
        String cachedToken = null;

        if (statusCode == 200) {
            cachedToken = response.jsonPath().getString("data");
        }
        return cachedToken;
    }

    /**
     * Lista todos los grupos y busca uno por su nombre, devolviendo el identificador del grupo.
     * @param name El nombre del grupo a buscar.
     * @return El identificador del grupo.
     * @throws RuntimeException Si no se encuentra un grupo con el nombre proporcionado.
     */
    public String listGroups(String name, String phone) {
        String baseUrl = "https://hub-cc200.appzone.dev/api/chat/myrooms?type=GROUP";
        userToken = getUserToken(phone);

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", userToken);
        request.contentType(ContentType.JSON);

        Response response = request.get(baseUrl);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Código de respuesta: " + statusCode);
        System.err.println("Cuerpo de respuesta: " + responseBody);

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> rooms = jsonPath.getList("data.rooms");

        for (Map<String, Object> room : rooms) {
            if (name.equals(room.get("name"))) {
                return room.get("room").toString();
            }
        }
        throw new RuntimeException("No se encontró una habitación con el nombre " + name);
    }

    /**
     * Elimina un grupo en la aplicación utilizando su nombre.
     * @param name El nombre del grupo.
     */
    public void deleteGroup(String name, String phone) {
        String groupId = listGroups(name, phone);
        String baseUrl = "https://hub-cc200.appzone.dev/api/chat/group/" + groupId;

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", userToken);
        request.contentType(ContentType.JSON);

        Response response = request.delete(baseUrl);
        int statusCode = response.getStatusCode();

        System.out.println("Código de respuesta: " + statusCode);
    }

    /**
     * Obtiene el token de autenticación para realizar solicitudes en la aplicación.
     * @param baseUrl El endpoint a la api para obtener el token.
     * @param jsonCredenciales las credenciales para conectarse.
     * @param authorization token de autorizacion para acceder a la api.
     * @return el token de autenticación.
     */
    public String getHubToken(String baseUrl, String jsonCredenciales ,String authorization ) {

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", authorization);
        request.contentType(ContentType.JSON);
        request.body(jsonCredenciales);

        Response response = request.post(baseUrl);
        int statusCode = response.getStatusCode();
        String cachedToken = null;

        if (statusCode == 200) {
            cachedToken = response.jsonPath().getString("data.token");
            System.err.println(cachedToken);
        }
        return cachedToken;
    }

    /**
     * Busca la ultima solicitud en la aplicación segun la cedula del usuario y devuelve su identificador.
     * @param user el numero de cedula del usuario.
     * @return El identificador del usuario.
     * @throws IllegalStateException Si no se encuentran usuarios en la respuesta.
     */
    public String searchRequestId(String user) {
        String url = "https://hub-cc200.appzone.dev/api/dev/userstreamchange/list_get?search=";
        String baseUrl = url + user;

        hubToken = getHubToken(urlToken,credenciales, authToken);

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", hubToken);
        request.contentType(ContentType.JSON);

        Response response = request.get(baseUrl);
        List<String> solicitudIds = response.jsonPath().getList("data.data._id");

        if (!solicitudIds.isEmpty()) {

            return solicitudIds.get(0);
        } else {
            throw new IllegalStateException("No se encontraron solicitudes en la respuesta.");
        }
    }
    /**
     * Busca la ultima solicitud en la aplicación segun la cedula del usuario  y devuelve su tipo.
     * @param user el numero de cedula del usuario.
     * @return El tipo de solicitud.
     * @throws IllegalStateException Si no se encuentra el tipo de las solicitudes en la respuesta.
     */
    public String searchRequestChangeType(String user) {
        String url = "https://hub-cc200.appzone.dev/api/dev/userstreamchange/list_get?search=";
        String baseUrl = url + user;

        hubToken = getHubToken(urlToken,credenciales, authToken);

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", hubToken);
        request.contentType(ContentType.JSON);

        Response response = request.get(baseUrl);
        List<String> solicitudChangeTypes = response.jsonPath().getList("data.data.changeType");

        if (!solicitudChangeTypes.isEmpty()) {

            return solicitudChangeTypes.get(0);
        } else {
            throw new IllegalStateException("No se encontraron solicitudes en la respuesta.");
        }
    }

    /**
     * Cambia el estado de una solicitud en la aplicación segun la cedula del usuario  y su tipo.
     * @param user el numero de cedula del usuario.
     * @param status el estado al cual se quiere cambiar la solicitud nueva.
     * @throws IllegalStateException Si no se encuentra el tipo de las solicitudes en la respuesta.
     */
    public void putChangeStatus(String user, String status) {
        String baseUrl = "https://hub-cc200.appzone.dev/api/dev/userstreamchange/status";
        String id = searchRequestId(user);
        String changeType = searchRequestChangeType(user);
        String json= null;

        if(Objects.equals(changeType, "NEW")){
            json = "{ \"_id\": \"" + id + "\",\"status\": \"" + status + "\",\"statusReason\": \"Motivo de prueba\" }";
        } else if (Objects.equals(changeType, "DELETE")) {
            json = "{ \"_id\": \"" + id + "\",\"status\": \"ACEPTADO\",\"statusReason\": \"Motivo de prueba\" }";
        }else if (Objects.equals(changeType, "UPDATE")) {
            json = "{ \"_id\": \"" + id + "\",\"status\": \"ACEPTADO\",\"statusReason\": \"Motivo de prueba\" }";
        }

        hubToken = getHubToken(urlToken, credenciales, authToken);

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", hubToken);
        request.contentType(ContentType.JSON);
        request.body(json);

        Response response = request.put(baseUrl);
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            throw new IllegalStateException("No se encontraron solicitudes que modificar en la respuesta.");
        }
    }
}
