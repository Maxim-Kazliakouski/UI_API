package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bookings {
    String firstname;
    String lastname;
    Integer totalprice;
    Boolean depositpaid;
    BookingsDates bookingdates;
    String additionalneeds;
}



