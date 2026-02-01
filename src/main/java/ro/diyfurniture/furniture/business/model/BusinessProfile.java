package ro.diyfurniture.furniture.business.model;

public class BusinessProfile {
	private String id;
	private String name;
	private String email;
	private String password;

	public BusinessProfile(String id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
