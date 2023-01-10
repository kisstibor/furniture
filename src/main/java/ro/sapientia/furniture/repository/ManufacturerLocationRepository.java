package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.ManufacturerLocation;

public interface ManufacturerLocationRepository extends JpaRepository<ManufacturerLocation, Long> {
    ManufacturerLocation findManufacturerLocationById(Long id);
}
