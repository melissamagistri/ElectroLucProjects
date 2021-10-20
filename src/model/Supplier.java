package model;

public class Supplier {

	private final String pIva;
	private final String companyName;
	private final String email;
	private final String address;
	private final String phone;

	public Supplier(final String pIva, final String companyName, final String email, final String address,
			final String phone) {
		this.pIva = pIva;
		this.companyName = companyName;
		this.email = email;
		this.address = address;
		this.phone = phone;	
	}

	public String getpIva() {
		return pIva;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

}
