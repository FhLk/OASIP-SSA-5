package sit.integrate.oasip.DTO;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
