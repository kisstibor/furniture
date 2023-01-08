package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.repository.BillingEntityBodyRepository;
import ro.sapientia.furniture.model.BillingEntityBody;

@Service
public class BillingEntityBodyService {
	
	private final BillingEntityBodyRepository billingEntityBodyRepository;
	
	public BillingEntityBodyService(final BillingEntityBodyRepository billingEntityBodyRepository) {
		this.billingEntityBodyRepository = billingEntityBodyRepository;
	}
	
	public List<BillingEntityBody> findAllBillingEntities() {
		return this.billingEntityBodyRepository.findAll();
	}
	
	public BillingEntityBody findBillingEntityBodyById(final Long id) {
		return this.billingEntityBodyRepository.findBillingEntityBodyById(id);
	}
	
	public BillingEntityBody create(BillingEntityBody bllingEntityBody) {
		return this.billingEntityBodyRepository.saveAndFlush(bllingEntityBody);
	}
	
	public BillingEntityBody update(BillingEntityBody billingEntityBody) {
		return this.billingEntityBodyRepository.saveAndFlush(billingEntityBody);
	}
	
	public void delete(Long id) {
		this.billingEntityBodyRepository.deleteById(id);
	}
}