package ro.diyfurniture.furniture.business.model;

public class BusinessAuthResponse {
	private String token;
	private String businessId;
	private String name;
	private String email;

	public BusinessAuthResponse(String token, String businessId, String name, String email) {
		this.token = token;
		this.businessId = businessId;
		this.name = name;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public String getBusinessId() {
		return businessId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
