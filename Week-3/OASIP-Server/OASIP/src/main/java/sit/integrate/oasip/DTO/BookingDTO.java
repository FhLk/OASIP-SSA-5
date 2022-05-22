package sit.integrate.oasip.DTO;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingDTO {
    private Integer id;
    @NotNull(message = "Name shouldn't be null")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(max = 100,message = "Your Name have length more than 100 character")
    @NotBlank(message = "Name shouldn't be null/empty")
    private String bookingName;

    @Email(message = "Email doesn't follow format")
    @NotBlank(message = "Email shouldn't be null/empty")
    @NotNull(message = "Email shouldn't be null")
    @NotEmpty(message = "Email shouldn't be empty")
    @Size(max = 100,message = "Your Email have length more than 100 character")
    private String bookingEmail;

    @NotNull(message = "Category is null")
    private CategoryDTONoValidate category;

    @Future(message = "Can't Change to Date and Time in Past")
    @NotNull(message = "Date and Time shouldn't be null")
    private LocalDateTime startTime;

    @Max(value = 480,message = "Duration have more than 480 minute")
    @Min(value = 1,message = "Duration have less 1 minute")
    @NotNull(message = "Duration shouldn't be null")
    private Integer bookingDuration;

    @Size(max = 500,message = "Your Note have length more than 500 character")
    private String eventNote;

    public String getStartTime(){
        return startTime.toString();
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime);
    }
}
