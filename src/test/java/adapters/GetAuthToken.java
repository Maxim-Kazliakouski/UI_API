package adapters;

import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import tests.api.Specifications;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class GetAuthToken {
    private final static String URL = PropertyReader.getProperty("mainURL");

    public static String authToken() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        dto.post.bodies.GetAuthToken user = new dto.post.bodies.GetAuthToken("admin", "password123");
        Response response = given()
                .body(user)
                .when()
                .post(URL + "/auth")
                .then().log().all()
                .extract().response();
        JsonPath data = response.jsonPath();
        return data.get("token");
    }
}
