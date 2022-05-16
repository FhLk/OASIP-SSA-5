package sit.integrate.oasip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrate.oasip.Entity.EventBooking;

public interface BookingRepository extends JpaRepository<EventBooking, Integer> {
    List<EventBooking> findAllByStartTimeLessThanOrderByStartTimeDesc(LocalDateTime localDateTime);
    List<EventBooking> findAllByStartTimeAfter(LocalDateTime localDateTime);
}