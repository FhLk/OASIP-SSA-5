package sit.integrate.oasip.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sit.integrate.oasip.Entity.EventBooking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class EventCategory {
    @Id
    @Column(name = "CategoryID", nullable = false)
    private Integer id;

    @Column(name = "CategoryName", nullable = false, length = 45)
    private String categoryName;

    @Column(name = "Description")
    private String description;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryID")
    private Set<EventBooking> eventBookings = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<EventBooking> getEventBookings() {
        return eventBookings;
    }

    public void setEventBookings(Set<EventBooking> eventBookings) {
        this.eventBookings = eventBookings;
    }

}