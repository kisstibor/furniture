package ro.sapientia.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.location.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
