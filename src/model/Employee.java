package model;

public class Employee {

	private final String firstName;
	private final String lastName;
	private final String fiscalCode;
	private final int employeeID;
	private final double salary;

	public Employee(final String firstName, final String lastName, final String fiscalCode,
			final int employeeID, final double salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fiscalCode = fiscalCode;
		this.employeeID = employeeID;
		this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public double getSalary() {
		return salary;
	}

}
