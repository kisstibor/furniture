package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.Material;
import ro.sapientia.furniture.repository.MaterialRepository;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(final MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> findAllMaterials() {
        return this.materialRepository.findAll();
    }

    public Material findMaterialById(final Long id) {
        return this.materialRepository.findMaterialById(id);
    }

    public Material create(Material material) {
        return this.materialRepository.saveAndFlush(material);
    }

    public Material update(Material material) {
        return this.materialRepository.saveAndFlush(material);
    }

    public void delete(Long id) {
        this.materialRepository.deleteById(id);
    }
}
