package model;

public class Supplier {

	private final int vatNumber;
	private final String companyName;
	private final String email;
	private final String address;
	private final String phone;

	public Supplier(final int vatNumber, final String companyName, final String email, final String address,
			final String phone) {
		this.vatNumber = vatNumber;
		this.companyName = companyName;
		this.email = email;
		this.address = address;
		this.phone = phone;	
	}

	public int getVATNumber() {
		return vatNumber;
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
