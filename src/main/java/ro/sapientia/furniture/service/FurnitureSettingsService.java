package ro.sapientia.furniture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.sapientia.furniture.model.SettingsServiceBody;
import ro.sapientia.furniture.repository.FurnitureSettingsServiceRepository;

@Service
public class FurnitureSettingsService {
	private final FurnitureSettingsServiceRepository furnitureSettingsServiceRepository;

	public FurnitureSettingsService (final FurnitureSettingsServiceRepository furnitureSettingsServiceRepository) {
		this.furnitureSettingsServiceRepository = furnitureSettingsServiceRepository;
	}
	
	public List<SettingsServiceBody> findAllSettingsServiceBody() {
		return this.furnitureSettingsServiceRepository.findAll();
	}
	
	public SettingsServiceBody findServiceBodyById(final Long id) {
		return this.furnitureSettingsServiceRepository.findSettingsServiceBodyById(id);
	}
	
	public SettingsServiceBody create(SettingsServiceBody settingsServiceBody) {
		return this.furnitureSettingsServiceRepository.saveAndFlush(settingsServiceBody);
	}
	
	public SettingsServiceBody update(SettingsServiceBody settingsServiceBody) {
		return this.furnitureSettingsServiceRepository.saveAndFlush(settingsServiceBody);
	}
	
	public void delete(Long id) {
		this.furnitureSettingsServiceRepository.deleteById(id);
	}
}
