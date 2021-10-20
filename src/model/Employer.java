package model;

import modelutil.Person;

public class Employer extends Person{

	private final int employerID;
	private final String PASSWORD = "123";

	public Employer(final String firstName, final String secondName, final String ficalCode, 
			final int employerID) {
		this.setFirstName(firstName);
		this.setSecondName(secondName);
		this.setFiscalCode(ficalCode);
		this.employerID = employerID;
	}

	public int getEmployerID() {
		return employerID;
	}

	public String getEmployerPassword() {
		return PASSWORD;
	}
}
