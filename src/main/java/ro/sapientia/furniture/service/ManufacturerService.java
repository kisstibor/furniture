package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.repository.ManufacturerRepository;

import java.util.List;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Manufacturer> findAllManufacturers() {
        return this.manufacturerRepository.findAll();
    }

    public Manufacturer findManufacturerById(final Long id) {
        return this.manufacturerRepository.findManufacturerById(id);
    }

    public Manufacturer create(Manufacturer manufacturer) {
        return this.manufacturerRepository.saveAndFlush(manufacturer);
    }

    public Manufacturer update(Manufacturer manufacturer) {
        return this.manufacturerRepository.saveAndFlush(manufacturer);
    }

    public void delete(Long id) {
        this.manufacturerRepository.deleteById(id);
    }

}
