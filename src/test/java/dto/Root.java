package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dto.Bookings;
import io.cucumber.java.mk_latn.No;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {
    Integer bookingid;
    Bookings booking;
}
