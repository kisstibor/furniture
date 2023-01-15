package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.repository.ManufacturerLocationRepository;
import ro.sapientia.furniture.util.StatusMessage;

import java.util.List;

@Service
public class ManufacturerLocationService {
    private final ManufacturerLocationRepository manufacturerLocationRepository;

    public ManufacturerLocationService(ManufacturerLocationRepository manufacturerLocationRepository) {
        this.manufacturerLocationRepository = manufacturerLocationRepository;
    }

    public List<ManufacturerLocation> findAllManufacturerLocations() {
        return this.manufacturerLocationRepository.findAll();
    }

    public ManufacturerLocation findManufacturerLocationById(final Long id) throws NotFoundException {
        ManufacturerLocation location = this.manufacturerLocationRepository.findManufacturerLocationById(id);
        if (location != null) {
            return location;
        } else {
            throw new NotFoundException(StatusMessage.NOT_FOUND);
        }
    }

    public ManufacturerLocation create(ManufacturerLocation manufacturerLocation) {
        return this.manufacturerLocationRepository.saveAndFlush(manufacturerLocation);
    }

    public ManufacturerLocation update(ManufacturerLocation manufacturerLocation) {
        return this.manufacturerLocationRepository.saveAndFlush(manufacturerLocation);
    }

    public void delete(Long id) throws NotFoundException {
        if (manufacturerLocationRepository.findManufacturerLocationById(id) != null) {
            this.manufacturerLocationRepository.deleteById(id);
        } else {
            throw new NotFoundException(StatusMessage.NOT_FOUND);
        }
    }
}
