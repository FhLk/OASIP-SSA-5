package sit.integrate.oasip.Entity;

import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "EventBooking")
public class EventBooking {
    @Id
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public String getBookingEmail() {
        return bookingEmail;
    }

    public void setBookingEmail(String bookingEmail) {
        this.bookingEmail = bookingEmail;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = Instant.parse(startTime);
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

}