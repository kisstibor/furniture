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
<<<<<<< HEAD
		try {
			return this.shipmentRepository.findAll();
		} catch(RuntimeException e) {
			return null;
		}
=======
		List<Shipment> shipments = new ArrayList<Shipment>();
		if (this.shipmentRepository.findAll().size() == 0) {
			throw new RuntimeException("No element was found!");
		} else {
			shipments = this.shipmentRepository.findAll();
		}
		return shipments;
>>>>>>> ebb9041 (Resolved merge error)
	}

	public Shipment findShipmentById(final Long id) {
		return this.shipmentRepository.findById(id).orElseThrow(
<<<<<<< HEAD
				() -> new RuntimeException("Element with the given id " + id + " was not found"));
=======
				() -> new RuntimeException("Element with the given id was not found"));
>>>>>>> ebb9041 (Resolved merge error)
	}

	public Shipment create(Shipment shipment) {
		try {
<<<<<<< HEAD
			return this.shipmentRepository.saveAndFlush(shipment);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the during create "  + e.getMessage());
			return null;
		}
=======
			this.shipmentRepository.saveAndFlush(shipment);
		} catch (Exception e) {
			System.out.println("Something happend with the create " + e.getMessage());
		}
		return shipment;
>>>>>>> ebb9041 (Resolved merge error)
	}

	public Shipment update(Shipment shipment) {
		try {
<<<<<<< HEAD
			return this.shipmentRepository.saveAndFlush(shipment);
		} catch (RuntimeException e) {
			System.out.println("Something happend with the update item: " + ">>>"  + e.getMessage());
			return null;
		}
=======
			Shipment existingShipment = this.shipmentRepository.findById(shipment.getId());
			this.shipmentRepository.saveAndFlush(shipment);
		} catch (Exception e) {
			System.out.println("Something happend with the update " + e.getMessage());
		}
		return shipment;
>>>>>>> ebb9041 (Resolved merge error)
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
