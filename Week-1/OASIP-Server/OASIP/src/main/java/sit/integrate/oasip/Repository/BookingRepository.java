package sit.integrate.oasip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrate.oasip.Entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
