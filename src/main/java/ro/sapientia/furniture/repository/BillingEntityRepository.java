package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.BillingEntity;

public interface BillingEntityRepository extends JpaRepository<BillingEntity, Long>{
	
	BillingEntity findBillingEntityById(Long id);

}
