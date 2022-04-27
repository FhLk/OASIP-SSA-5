package sit.integrate.oasip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrate.oasip.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
