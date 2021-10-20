package model;

import modelutil.Person;

public class Employee extends Person{

	private final int employeeID;
	private final int salary;

	public Employee(final String firstName, final String secondName, final String ficalCode,
			final int employeeID, final int salary) {
		this.setFirstName(firstName);
		this.setSecondName(secondName);
		this.setFiscalCode(ficalCode);
		this.employeeID = employeeID;
		this.salary = salary;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public int getSalary() {
		return salary;
	}

}
