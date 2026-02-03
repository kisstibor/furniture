package ro.diyfurniture.furniture.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.diyfurniture.furniture.business.model.SheetTexture;

@Repository
public interface SheetTextureRepository extends JpaRepository<SheetTexture, String> {}
