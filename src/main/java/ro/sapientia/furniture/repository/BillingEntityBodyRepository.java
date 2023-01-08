package ro.sapientia.furniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sapientia.furniture.model.BillingEntityBody;

public interface BillingEntityBodyRepository extends JpaRepository<BillingEntityBody, Long>{
	
	BillingEntityBody findBillingEntityBodyById(Long id);

}
