package reqres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;



public class LoginUtils {

    public static Response login(String email, String password, int expectedStatusCode) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        return RestAssured.given()
                .baseUri(Constants.BASE_URL)
                .headers(Header.getHeadersWithoutAuth())
                .contentType(ContentType.JSON)
                .body(credentials)
                .post(Constants.LOGIN_ENDPOINT)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    public static String getToken(Response response) {
        return "Bearer " + response.jsonPath().getString("token");
    }

}
