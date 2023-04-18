package dto.post.bodies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class Bookingdates {
     String checkin;
     String checkout;
}
