package dto.factories;

import com.github.javafaker.Faker;
import dto.post.bodies.Bookingdates;
import dto.post.bodies.CreateBooking;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Booking {
    public static CreateBooking createBooking() {
//        String pattern = "yyyy.MM.dd";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Random randomDays = ThreadLocalRandom.current();
        LocalDateTime pastDate = LocalDateTime.now().minusDays(randomDays.nextInt(365) - 1);
        LocalDateTime futureDate = LocalDateTime.now().plusDays(randomDays.nextInt(365) + 1);
        Faker faker = new Faker();
        Bookingdates dates = new Bookingdates(pastDate.toString(), futureDate.toString());
        return CreateBooking.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(1, 1000))
                .depositpaid(faker.random().nextBoolean())
                .bookingdates(dates)
                .additionalneeds(faker.food().dish())
                .build();
    }
}
