package sit.integrate.oasip.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import sit.integrate.oasip.Entity.EventCategory;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @Valid
    private CategoryDTO category;
    @Past(message = "Can't Change to Date and Time in Past")
    @NotBlank(message = "Date and Time shouldn't be empty")
    private LocalDateTime startTime;
    @Min(1)@Max(480)
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
