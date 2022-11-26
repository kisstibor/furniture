package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findMaterialById(Long id);
}
