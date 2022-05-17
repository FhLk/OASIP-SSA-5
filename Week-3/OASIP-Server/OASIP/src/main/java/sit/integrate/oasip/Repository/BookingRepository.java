package sit.integrate.oasip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrate.oasip.DTO.CategoryDTO;
import sit.integrate.oasip.Entity.EventBooking;
import sit.integrate.oasip.Entity.EventCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<EventBooking, Integer> {
    List<EventBooking> findAllByCategoryOrderByStartTimeDesc(CategoryDTO categoryName);
    List<EventBooking> findAllByStartTimeLessThanOrderByStartTimeDesc(LocalDateTime localDateTime);
    List<EventBooking> findAllByStartTimeAfter(LocalDateTime localDateTime);
    List<EventBooking> findAllByStartTimeBetweenOrderByStartTimeAsc(LocalDateTime startdate,LocalDateTime enddate);
}
