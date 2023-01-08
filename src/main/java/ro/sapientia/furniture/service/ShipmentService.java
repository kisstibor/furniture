package ro.sapientia.furniture.service;

import java.util.ArrayList;
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
		List<Shipment> shipments = new ArrayList<Shipment>();
		if (this.shipmentRepository.findAll().size() == 0) {
			throw new RuntimeException("No element was found!");
		} else {
			shipments = this.shipmentRepository.findAll();
		}
		return shipments;
	}

	public Shipment findShipmentById(final Long id) {
		return this.shipmentRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Element with the given id was not found"));
	}

	public Shipment create(Shipment shipment) {
		try {
			this.shipmentRepository.saveAndFlush(shipment);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the create " + e.getMessage());
		}
		return shipment;
	}

	public Shipment update(Long id, Shipment shipment) {
		Shipment existingShipment = new Shipment();
		try {
			existingShipment = this.shipmentRepository.findShipmentById(id);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the update " + e.getMessage());
		}
		shipment.setId(id);
		return this.shipmentRepository.saveAndFlush(existingShipment);
	}

	public void delete(Long id) {
		this.shipmentRepository.deleteById(id);
	}
	
}
