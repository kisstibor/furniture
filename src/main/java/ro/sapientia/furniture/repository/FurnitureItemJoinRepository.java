package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sapientia.furniture.model.FurnitureItemJoin;

public interface FurnitureItemJoinRepository extends JpaRepository<FurnitureItemJoin, Long>{
    FurnitureItemJoin findFurnitureItemJoinById(Long id);
}

