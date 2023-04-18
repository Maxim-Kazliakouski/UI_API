package tests.api;

import dto.Bookings;
import dto.Root;
import dto.factories.Booking;
import dto.post.bodies.CreateBooking;
import dto.post.bodies.GetAuthToken;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import steps.Hooks;
import utils.PropertyReader;

import static adapters.GetAuthToken.authToken;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ApiTest extends Hooks {
    private final static String URL = PropertyReader.getProperty("mainURL");

    @Test
    @Description("Api test for getting bookings")
    public void getBookingsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Bookings bookings = given()
                .when()
                .get(URL + "/booking/3")
                .then().log().all()
                .body("firstname", Matchers.notNullValue())
                .body("lastname", Matchers.notNullValue())
                .body("totalprice", Matchers.notNullValue())
                .body("depositpaid", Matchers.notNullValue())
                .body("bookingdates.checkin", Matchers.notNullValue())
                .body("bookingdates.checkout", Matchers.notNullValue())
                .extract().body().as(Bookings.class);
    }

    @Test
    @Description("Api test for getting auth token")
    public void gettingAuthToken() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        GetAuthToken user = new GetAuthToken("admin", "password123");
        given()
                .body(user)
                .when()
                .post(URL + "/auth")
                .then().log().all()
                .body("token", Matchers.notNullValue());
    }

    @Test
    @Description("Api test for creating bookings")
    public void createBooking() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        CreateBooking booking = Booking.createBooking();
//        Gson gson = new Gson();
//        String data = gson.toJson(booking);
        Root bron =
                given()
                        .when()
                        .body(booking)
                        .post(URL + "/booking")
                        .then().log().all()
                        .extract().body().as(Root.class);
        assertNotNull(bron.getBookingid());
        assertNotNull(bron.getBooking().getFirstname());
        assertNotNull(bron.getBooking().getLastname());
        assertNotNull(bron.getBooking().getTotalprice());
        assertNotNull(bron.getBooking().getDepositpaid());
        assertNotNull(bron.getBooking().getBookingdates().getCheckin());
        assertNotNull(bron.getBooking().getBookingdates().getCheckout());
    }

    @Test
    @Description("Api test for updating booking info")
    public void updateBooking() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        String tokenValue = authToken();
        String firstname = "Max";
        CreateBooking booking = Booking.createBooking();
        booking.setFirstname(firstname);
        Bookings bron = given()
                .when()
                .header("Cookie", "token=" + tokenValue)
                .body(booking)
                .put(URL + "/booking/1")
                .then().log().all()
                .extract().body().as(Bookings.class);
        assertEquals(bron.getFirstname(), firstname, "The firstname hasn't been updated");
    }
}