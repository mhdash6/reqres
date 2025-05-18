package reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import reqres.model.User;

import static reqres.Constants.USERS_ENDPOINT;

public class UserUtils {

    private static RequestSpecification baseRequest(String token) {
        return RestAssured.given()
                .baseUri(Constants.BASE_URL)
                .headers(Header.getHeaders(token));
    }

    public static Response getUser(int id, int expectedStatus, String token) {
        return baseRequest(token)
                .get(USERS_ENDPOINT + "/" + id)
                .then()
                .statusCode(expectedStatus)
                .extract()
                .response();
    }

    public static Response getUsersByPage(int page, int expectedStatus, String token) {
        return baseRequest(token)
                .queryParam("page", page).log().params()
                .get(USERS_ENDPOINT)
                .then()
                .statusCode(expectedStatus)
                .extract()
                .response();
    }

    public static Response createUser(String token, User user, int expectedStatus) {
        return baseRequest(token)
                .body(user)
                .post(USERS_ENDPOINT)
                .then()
                .statusCode(expectedStatus)
                .extract()
                .response();
    }

    public static Response updateUser(int id, String token, User user, int expectedStatus) {
        return baseRequest(token)
                .body(user)
                .put(USERS_ENDPOINT + "/" + id)
                .then()
                .statusCode(expectedStatus)
                .extract()
                .response();
    }

    public static Response deleteUser(int id, String token, int expectedStatus) {
        return baseRequest(token)
                .delete(USERS_ENDPOINT + "/" + id)
                .then()
                .statusCode(expectedStatus)
                .extract()
                .response();
    }
}


