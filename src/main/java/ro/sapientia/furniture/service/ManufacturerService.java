package ro.sapientia.furniture.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ro.sapientia.furniture.util.StatusMessage;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.repository.ManufacturerRepository;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
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

    public Manufacturer findManufacturerById(final Long id) throws NotFoundException {
        Manufacturer manufacturer = this.manufacturerRepository.findManufacturerById(id);
        if (manufacturer != null) {
            return manufacturer;
        } else {
            throw new NotFoundException(StatusMessage.NOT_FOUND);
        }
    }

    public Manufacturer create(Manufacturer manufacturer) {
        return this.manufacturerRepository.saveAndFlush(manufacturer);
    }

    public void delete(Long id) throws NotFoundException {
        if (this.manufacturerRepository.findManufacturerById(id) != null) {
            this.manufacturerRepository.deleteById(id);
        } else {
            throw new NotFoundException(StatusMessage.NOT_FOUND);
        }
    }

}
