package sit.integrate.oasip.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "EventBooking")
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID", nullable = false)
    private Integer id;

    @Column(name = "BookingName", nullable = false, length = 110)
    private String bookingName;

    @Column(name = "BookingEmail", nullable = false, length = 110)
    private String bookingEmail;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Category", nullable = false)
    private EventCategory category;

    @Column(name = "StartTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "BookingDuration", nullable = false)
    private Integer bookingDuration;

    @Column(name = "EventNote", length = 510)
    private String eventNote;


    public Integer getId() {
        return id;
    }


    public String getBookingName() {
        return bookingName;
    }


    public String getBookingEmail() {
        return bookingEmail;
    }


    public EventCategory getCategory() {
        return category;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime);
    }

    public Integer getBookingDuration() {
        return bookingDuration;
    }


    public String getEventNote() {
        return eventNote;
    }

}