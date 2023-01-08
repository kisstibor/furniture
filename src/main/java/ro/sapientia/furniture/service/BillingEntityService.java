package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.repository.BillingEntityRepository;
import ro.sapientia.furniture.model.BillingEntity;

@Service
public class BillingEntityService {
	
	private final BillingEntityRepository billingEntityRepository;
	
	public BillingEntityService(final BillingEntityRepository billingEntityRepository) {
		this.billingEntityRepository = billingEntityRepository;
	}
	
	public List<BillingEntity> findAllBillingEntities() {
		return this.billingEntityRepository.findAll();
	}
	
	public BillingEntity findBillingEntityById(final Long id) {
		return this.billingEntityRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Billing entity wirh the given id: " + id + "is not found!"));
	}
	
	public BillingEntity create(BillingEntity billingEntity) {
		try {
			return this.billingEntityRepository.saveAndFlush(billingEntity);
		}
		catch(Exception ex) {
			System.out.println("Error occured when saving new entity. Whit the next error: " + ex.getLocalizedMessage());
			return null;
		}
	}
	
	public BillingEntity update(Long id, BillingEntity billingEntity) {
		try {
			final BillingEntity existingbillingEntity = findBillingEntityById(id);
			billingEntity.setId(id);
			return this.billingEntityRepository.saveAndFlush(billingEntity);
		}
		catch(Exception ex) {
			System.out.println("Error occured when updating entity with id: " + id + ". With the next error: " + ex.getLocalizedMessage());
			return null;
		}
	}
	
	public void delete(Long id) {
		try {
			this.billingEntityRepository.deleteById(id);
		}
		catch(Exception ex) {
			System.out.println("Error occured when deleting entity with id: " + id + ". With the next error: " + ex.getLocalizedMessage());
		}
	}
}