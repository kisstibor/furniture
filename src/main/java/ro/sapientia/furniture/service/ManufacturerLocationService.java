package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.repository.ManufacturerLocationRepository;

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

    public ManufacturerLocation findManufacturerLocationById(final Long id) {
        return this.manufacturerLocationRepository.findManufacturerLocationById(id);
    }

    public ManufacturerLocation create(ManufacturerLocation manufacturerLocation) {
        return this.manufacturerLocationRepository.saveAndFlush(manufacturerLocation);
    }

    public ManufacturerLocation update(ManufacturerLocation manufacturerLocation) {
        return this.manufacturerLocationRepository.saveAndFlush(manufacturerLocation);
    }

    public void delete(Long id) {
        this.manufacturerLocationRepository.deleteById(id);
    }
}
