package sit.integrate.oasip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrate.oasip.Entity.EventCategory;

public interface CategoryRepository extends JpaRepository<EventCategory, Integer> {
}
