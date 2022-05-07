package sit.integrate.oasip.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "EventBooking")
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID", nullable = false)
    private Integer id;

    @Column(name = "BookingName", nullable = false, length = 50)
    private String bookingName;

    @Column(name = "BookingEmail", nullable = false, length = 45)
    private String bookingEmail;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Category", nullable = false)
    private EventCategory category;

    @Column(name = "StartTime", nullable = false)
    private Instant startTime;

    @Column(name = "EventNote", length = 50)
    private String eventNote;

    public void setStartTime(String startTime) {
        this.startTime = Instant.parse(startTime);
    }
}