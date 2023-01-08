package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.repository.BillingEntityRepository;
import ro.sapientia.furniture.model.BillingEntity;
import ro.sapientia.furniture.model.dto.BillingEntityRequestDTO;

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
		return this.billingEntityRepository.findBillingEntityById(id);
	}
	
	public BillingEntity create(BillingEntityRequestDTO billingEntityRequestDTO) {
		final BillingEntity billingEntity = BillingEntity.builder()
				.creditCard(billingEntityRequestDTO.getCreditCard())
				.customerType(billingEntityRequestDTO.getCustomerType())
				.paymentMethod(billingEntityRequestDTO.getPaymentMethod())
				.deposit(billingEntityRequestDTO.getDeposit())
				.build();
				
		return this.billingEntityRepository.saveAndFlush(billingEntity);
	}
	
	public BillingEntity update(BillingEntityRequestDTO billingEntityRequestDTO) {
		final BillingEntity existingBillingEntity = findBillingEntityById(billingEntityRequestDTO.getId());
		
		final BillingEntity billingEntity = BillingEntity.builder()
				.id(existingBillingEntity.getId())
				.creditCard(billingEntityRequestDTO.getCreditCard())
				.customerType(billingEntityRequestDTO.getCustomerType())
				.paymentMethod(billingEntityRequestDTO.getPaymentMethod())
				.deposit(billingEntityRequestDTO.getDeposit())
				.build();
		
		return this.billingEntityRepository.saveAndFlush(billingEntity);
	}
	
	public void delete(Long id) {
		this.billingEntityRepository.deleteById(id);
	}
}