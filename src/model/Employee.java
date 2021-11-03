package model;

import modelutil.Person;

public class Employee extends Person{

	private final int employeeID;
	private final double salary;

	public Employee(final String firstName, final String secondName, final String ficalCode,
			final int employeeID, final double salary) {
		this.setFirstName(firstName);
		this.setSecondName(secondName);
		this.setFiscalCode(ficalCode);
		this.employeeID = employeeID;
		this.salary = salary;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public double getSalary() {
		return salary;
	}

}
