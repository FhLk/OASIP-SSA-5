package sit.integrate.oasip.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    @JsonIgnore
    private CategoryDTO category;
    private String startTime;
    private String eventNote;


}
