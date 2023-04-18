package dto.post.bodies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBooking {
     String firstname;
     String lastname;
     int totalprice;
     boolean depositpaid;
     Bookingdates bookingdates;
     String additionalneeds;
}
