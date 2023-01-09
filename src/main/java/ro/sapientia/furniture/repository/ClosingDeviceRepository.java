package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.model.FurnitureBody;

public interface ClosingDeviceRepository extends JpaRepository<ClosingDevice, Long>{
	//ClosingDevice findClosingDeviceById(Long id);
}
