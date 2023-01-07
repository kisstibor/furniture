package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findProductById(Long id);
}
