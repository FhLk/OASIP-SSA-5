package sit.integrate.oasip.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "EventBooking")
public class EventBooking {
    @Id
    @Column(name = "BookingID", nullable = false)
    private Integer id;

    @Column(name = "BookingName", nullable = false, length = 150)
    private String bookingName;

    @Column(name = "BookingEmail", nullable = false, length = 150)
    private String bookingEmail;

    @ManyToOne(fetch = FetchType.EAGER , optional = false)
    @JoinColumn(name = "Category", nullable = false)
    private EventCategory category;

    @Column(name = "StartTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "BookingDuration", nullable = false)
    private Integer bookingDuration;

    @Column(name = "EventNote", length = 550)
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

    public Integer getBookingDuration() {
        return bookingDuration;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalDateTime.parse(startTime);
    }
}