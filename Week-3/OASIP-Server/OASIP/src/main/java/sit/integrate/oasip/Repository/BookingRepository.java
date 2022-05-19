package sit.integrate.oasip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrate.oasip.Entity.EventBooking;
import org.springframework.data.domain.Pageable;
import sit.integrate.oasip.Entity.EventCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<EventBooking, Integer> {
    List<EventBooking> findAllByCategoryOrderByStartTimeDesc(Pageable page, EventCategory category);
    List<EventBooking> findAllByStartTimeLessThanOrderByStartTimeDesc(Pageable page,LocalDateTime localDateTime);
    List<EventBooking> findAllByStartTimeBetweenOrderByStartTimeAsc(Pageable page,LocalDateTime startdate,LocalDateTime enddate);

}
