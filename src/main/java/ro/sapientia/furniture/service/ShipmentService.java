package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.Shipment;
import ro.sapientia.furniture.repository.ShipmentRepository;

@Service
public class ShipmentService {
	
	private final ShipmentRepository shipmentRepository;
	
	public ShipmentService(final ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}
	
	public List<Shipment> findAllShipments() {
		try {
			return this.shipmentRepository.findAll();
		} catch(RuntimeException e) {
			return null;
		}
	}

	public Shipment findShipmentById(final Long id) {
		return this.shipmentRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Element with the given id " + id + " was not found"));
	}

	public Shipment create(Shipment shipment) {
		try {
			return this.shipmentRepository.saveAndFlush(shipment);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the during create "  + e.getMessage());
			return null;
		}
	}

	public Shipment update(Shipment shipment) {
		try {
			return this.shipmentRepository.saveAndFlush(shipment);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the update item: " + ">>>"  + e.getMessage());
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			this.shipmentRepository.deleteById(id);
			return true;
		} catch(RuntimeException e) {
			System.out.println("Error occured while deleting item with id: " + id + ">>>" + e.getMessage());
			return false;
		}
	}
	
}
