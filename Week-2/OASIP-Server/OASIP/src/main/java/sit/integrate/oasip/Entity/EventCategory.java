package sit.integrate.oasip.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "EventCategory")
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

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDuration() {
        return duration;
    }

}