package sit.integrate.oasip.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class EventBooking {
    @Id
    @Column(name = "BookingID", nullable = false)
    private Integer id;

    @Column(name = "BookingName", nullable = false, length = 50)
    private String bookingName;

    @Column(name = "BookingEmail", nullable = false, length = 45)
    private String bookingEmail;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CategoryID", nullable = false)
    private EventCategory categoryID;

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

    public EventCategory getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(EventCategory categoryID) {
        this.categoryID = categoryID;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

}