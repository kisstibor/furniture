package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.ClosingDevice;
import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.repository.ClosingDeviceRepository;
import ro.sapientia.furniture.repository.FurnitureBodyRepository;

@Service
public class ClosingDeviceService {
	private final ClosingDeviceRepository closingDeviceRepository;

	public ClosingDeviceService(final ClosingDeviceRepository closingDeviceRepository) {
		this.closingDeviceRepository = closingDeviceRepository;
	}

	public List<ClosingDevice> findAllClosingDevices() {
		return this.closingDeviceRepository.findAll();
	}

	public ClosingDevice findClosingDeviceById(final Long id) {
		return this.closingDeviceRepository.findById(id).get();
	}

	public ClosingDevice create(ClosingDevice closingDevice) {
		return this.closingDeviceRepository.saveAndFlush(closingDevice);
	}

	public ClosingDevice update(ClosingDevice closingDevice) {
		final ClosingDevice existingClosingDevice = findClosingDeviceById(closingDevice.getId());
		if (existingClosingDevice == null)
			return null;

		final ClosingDevice newClosingDevice = new ClosingDevice();

		newClosingDevice.setId(existingClosingDevice.getId()); //csak kulcs
		newClosingDevice.setHeight(closingDevice.getHeight());
		newClosingDevice.setWidth(closingDevice.getWidth());
		newClosingDevice.setDepth(closingDevice.getDepth());
		return this.closingDeviceRepository.saveAndFlush(newClosingDevice);
	}

	public void delete(Long id) {
		final ClosingDevice existingClosingDevice = findClosingDeviceById(id);
		if (existingClosingDevice != null)
			this.closingDeviceRepository.deleteById(id);
	}
}
