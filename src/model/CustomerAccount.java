package model;

import java.util.Optional;

public class CustomerAccount {

	private final String firstName;
	private final String lastName;
	private final Optional<String> phone;
	private final String deliveryAddress;
	private final String email;
	private final String password;

	public CustomerAccount(final String firstName, final String lastName,
			final Optional<String> phone, final String deliveryAddress,
			final String email, final String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.deliveryAddress = deliveryAddress;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Optional<String> getPhone() {
		return phone;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
