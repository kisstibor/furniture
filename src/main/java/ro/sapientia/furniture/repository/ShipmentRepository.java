package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long>  {
	
	Shipment findShipmentById(Long id);
	
}
