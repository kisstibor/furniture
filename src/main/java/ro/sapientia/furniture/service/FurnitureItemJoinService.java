package ro.sapientia.furniture.service;

import org.springframework.stereotype.Service;
import ro.sapientia.furniture.model.FurnitureBody;
import ro.sapientia.furniture.model.FurnitureItemJoin;
import ro.sapientia.furniture.repository.FurnitureItemJoinRepository;

import java.util.List;

@Service
public class FurnitureItemJoinService {

    private final FurnitureItemJoinRepository furnitureItemJoinRepository;

    public FurnitureItemJoinService(final FurnitureItemJoinRepository furnitureItemJoinRepository) {
        this.furnitureItemJoinRepository = furnitureItemJoinRepository;
    }

    public List<FurnitureItemJoin> findAllFurnitureItemJoins() {
        return this.furnitureItemJoinRepository.findAll();
    }

    public FurnitureItemJoin findFurnitureItemJoinById(final Long id) {
        return this.furnitureItemJoinRepository.findFurnitureItemJoinById(id);
    }

    public FurnitureItemJoin create(FurnitureItemJoin furnitureItemJoin) {
        return this.furnitureItemJoinRepository.saveAndFlush(furnitureItemJoin);
    }

    public FurnitureItemJoin update(FurnitureItemJoin furnitureItemJoin) {
        return this.furnitureItemJoinRepository.saveAndFlush(furnitureItemJoin);
    }

    public void delete(Long id) {
        this.furnitureItemJoinRepository.deleteById(id);
    }
}
