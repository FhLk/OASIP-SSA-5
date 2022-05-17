package sit.integrate.oasip.DTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SortListDayDTO{
    private String bookingName;
    private Integer bookingDuration;
    private LocalDateTime startTime;

    public String getStartTime(){
        return startTime.toString();
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime);
    }
}