package ro.diyfurniture.furniture.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import ro.diyfurniture.furniture.business.model.BusinessAuthRequest;
import ro.diyfurniture.furniture.business.model.BusinessAuthResponse;
import ro.diyfurniture.furniture.business.model.BusinessProfile;
import ro.diyfurniture.furniture.business.model.CuttingServiceRequest;
import ro.diyfurniture.furniture.business.model.CuttingServiceResponse;
import ro.diyfurniture.furniture.business.model.SheetTypeRequest;
import ro.diyfurniture.furniture.business.model.SheetTypeResponse;

@Service
public class BusinessService {
	private final Map<String, BusinessProfile> businessesById = new ConcurrentHashMap<>();
	private final Map<String, BusinessProfile> businessesByEmail = new ConcurrentHashMap<>();
	private final Map<String, SheetTypeResponse> sheetTypesById = new ConcurrentHashMap<>();
	private final Map<String, CuttingServiceResponse> cuttingServicesById = new ConcurrentHashMap<>();

	public BusinessAuthResponse register(BusinessAuthRequest request) {
		validateAuthRequest(request, true);
		if (businessesByEmail.containsKey(request.getEmail().toLowerCase())) {
			throw new IllegalArgumentException("Business already registered.");
		}
		String id = UUID.randomUUID().toString();
		BusinessProfile profile = new BusinessProfile(
			id,
			request.getName().trim(),
			request.getEmail().trim().toLowerCase(),
			request.getPassword()
		);
		businessesById.put(id, profile);
		businessesByEmail.put(profile.getEmail(), profile);
		return new BusinessAuthResponse(generateToken(), id, profile.getName(), profile.getEmail());
	}

	public BusinessAuthResponse login(BusinessAuthRequest request) {
		validateAuthRequest(request, false);
		BusinessProfile profile = businessesByEmail.get(request.getEmail().trim().toLowerCase());
		if (profile == null || !profile.getPassword().equals(request.getPassword())) {
			throw new IllegalArgumentException("Invalid credentials.");
		}
		return new BusinessAuthResponse(generateToken(), profile.getId(), profile.getName(), profile.getEmail());
	}

	public List<SheetTypeResponse> listSheetTypes(String businessId) {
		List<SheetTypeResponse> result = new ArrayList<>();
		for (SheetTypeResponse sheet : sheetTypesById.values()) {
			if (businessId == null || businessId.isBlank() || sheet.getBusinessId().equals(businessId)) {
				result.add(sheet);
			}
		}
		return result;
	}

	public SheetTypeResponse createSheetType(SheetTypeRequest request) {
		validateSheetRequest(request);
		String id = UUID.randomUUID().toString();
		SheetTypeResponse sheet = new SheetTypeResponse(
			id,
			request.getBusinessId(),
			request.getName().trim(),
			request.getWidth(),
			request.getHeight(),
			request.getThickness(),
			request.getKerf(),
			request.getEdgeBand()
		);
		sheetTypesById.put(id, sheet);
		return sheet;
	}

	public void deleteSheetType(String id) {
		sheetTypesById.remove(id);
	}

	public List<CuttingServiceResponse> listCuttingServices(String businessId) {
		List<CuttingServiceResponse> result = new ArrayList<>();
		for (CuttingServiceResponse service : cuttingServicesById.values()) {
			if (businessId == null || businessId.isBlank() || service.getBusinessId().equals(businessId)) {
				result.add(service);
			}
		}
		return result;
	}

	public CuttingServiceResponse createCuttingService(CuttingServiceRequest request) {
		validateCuttingServiceRequest(request);
		String id = UUID.randomUUID().toString();
		CuttingServiceResponse service = new CuttingServiceResponse(
			id,
			request.getBusinessId(),
			request.getName().trim(),
			request.getPricePerSheet(),
			request.getNotes()
		);
		cuttingServicesById.put(id, service);
		return service;
	}

	public void deleteCuttingService(String id) {
		cuttingServicesById.remove(id);
	}

	private void validateAuthRequest(BusinessAuthRequest request, boolean requireName) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		if (requireName && (request.getName() == null || request.getName().isBlank())) {
			throw new IllegalArgumentException("Business name is required.");
		}
		if (request.getEmail() == null || request.getEmail().isBlank()) {
			throw new IllegalArgumentException("Email is required.");
		}
		if (request.getPassword() == null || request.getPassword().isBlank()) {
			throw new IllegalArgumentException("Password is required.");
		}
	}

	private void validateSheetRequest(SheetTypeRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		if (request.getBusinessId() == null || request.getBusinessId().isBlank()) {
			throw new IllegalArgumentException("Business id is required.");
		}
		if (request.getName() == null || request.getName().isBlank()) {
			throw new IllegalArgumentException("Sheet name is required.");
		}
		if (request.getWidth() <= 0 || request.getHeight() <= 0) {
			throw new IllegalArgumentException("Sheet width and height must be positive.");
		}
		if (request.getThickness() <= 0) {
			throw new IllegalArgumentException("Sheet thickness must be positive.");
		}
		if (request.getKerf() < 0 || request.getEdgeBand() < 0) {
			throw new IllegalArgumentException("Kerf and edge band must be non-negative.");
		}
	}

	private void validateCuttingServiceRequest(CuttingServiceRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request is required.");
		}
		if (request.getBusinessId() == null || request.getBusinessId().isBlank()) {
			throw new IllegalArgumentException("Business id is required.");
		}
		if (request.getName() == null || request.getName().isBlank()) {
			throw new IllegalArgumentException("Service name is required.");
		}
		if (request.getPricePerSheet() < 0) {
			throw new IllegalArgumentException("Price must be non-negative.");
		}
	}

	private String generateToken() {
		return "dev-" + UUID.randomUUID();
	}
}
