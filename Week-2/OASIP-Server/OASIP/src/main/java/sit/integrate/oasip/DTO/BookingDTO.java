package sit.integrate.oasip.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private CategoryDTO category;
    private Instant startTime;
    private String eventNote;

    public String getStartTime(){
        return startTime.toString();
    }

    public void setStartTime(String startTime) {
        this.startTime = Instant.parse(startTime);
    }
}
