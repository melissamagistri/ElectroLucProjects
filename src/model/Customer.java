package model;

import java.util.Optional;

import modelutil.Person;

public class Customer extends Person{

	private final Optional<String> phone;
	private final String deliveryAddress;

	private Customer(final String fiscalCode, final String firstName, final String secondName,
			final Optional<String> phone, final String deliveryAddress) {
		this.setFirstName(firstName);
		this.setSecondName(secondName);
		this.setFiscalCode(fiscalCode);
		this.phone = phone;
		this.deliveryAddress = deliveryAddress;
	}

	public Customer(final String fiscalCode, final String firstName, final String secondName,
			final String phone, final String deliveryAddress) {
		this(fiscalCode, firstName, secondName, Optional.of(phone), deliveryAddress);
	}

	public Customer(final String fiscalCode, final String firstName, final String secondName,
			final String deliveryAddress) {
		this(fiscalCode, firstName, secondName, Optional.empty(), deliveryAddress);
	}

	public Optional<String> getPhone() {
		return phone;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

}
