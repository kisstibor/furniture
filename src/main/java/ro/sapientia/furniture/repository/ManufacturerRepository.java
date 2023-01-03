package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Manufacturer findManufacturerById(Long id);
}
