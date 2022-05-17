package sit.integrate.oasip.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SortListDayDTO{
    private LocalDateTime startTime;
    private Integer bookingDuration;
    private String bookingName;

    public String getStartTime(){
        return startTime.toString();
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime);
    }
}