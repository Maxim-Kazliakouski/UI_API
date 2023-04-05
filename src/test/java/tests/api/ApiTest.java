package tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    public void getBookings() {
        RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/1")
                .then().log().all()
                .extract().response();
    }
}
