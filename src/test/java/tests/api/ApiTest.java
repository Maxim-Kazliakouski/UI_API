package tests.api;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    @Description("Api test")
    public void getBookings() {
        RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/1")
                .then().log().all()
                .extract().response();
    }
}
