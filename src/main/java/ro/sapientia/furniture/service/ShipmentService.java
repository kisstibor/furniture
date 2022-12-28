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
		return this.shipmentRepository.findAll();
	}

	public Shipment findShipmentById(final Long id) {
		return this.shipmentRepository.findShipmentById(id);
	}

	public Shipment create(Shipment shipment) {
		return this.shipmentRepository.saveAndFlush(shipment);
	}

	public Shipment update(Shipment shipment) {
		return this.shipmentRepository.saveAndFlush(shipment);
	}

	public void delete(Long id) {
		this.shipmentRepository.deleteById(id);
	}
}
