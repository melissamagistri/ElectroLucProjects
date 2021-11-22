package model;

public class EmployeeDate {
	
	private final String firstName;
	private final String lastName;
	private final String fiscalCode;
	private final int employeeID;
	private final double salary;
	private final String hireDate;
	private final String endDate;
	private final String contractType;
	

	public EmployeeDate(final String firstName, final String lastName, final String fiscalCode,
			final int employeeID, final double salary, final String hireDate, final String endDate, final String contractType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fiscalCode = fiscalCode;
		this.employeeID = employeeID;
		this.salary = salary;
		this.hireDate = hireDate;
		this.endDate = endDate;
		this.contractType = contractType;
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


	public String getHireDate() {
		return hireDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public String getContractType() {
		return contractType;
	}


}
