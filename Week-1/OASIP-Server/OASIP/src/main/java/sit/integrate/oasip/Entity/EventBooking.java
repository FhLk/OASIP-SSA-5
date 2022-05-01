package sit.integrate.oasip.Entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "EventBooking")
public class EventBooking {
    @Id
    @Column(name = "BookingID", nullable = false)
    private Integer id;

    @Column(name = "BookingName", nullable = false, length = 50)
    private String bookingName;

    @Column(name = "BookingEmail", nullable = false, length = 50)
    private String bookingEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryID", nullable = false)
    private EventCategory categoryID;

    @Column(name = "StartTime", nullable = false)
    private Instant startTime;

    @Column(name = "EventNote")
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