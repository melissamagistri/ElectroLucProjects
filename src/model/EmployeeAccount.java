package model;

public class EmployeeAccount {

	private final int employeeID;
	private final String password;

	public EmployeeAccount(final int employeeID, final String password) {
		this.employeeID = employeeID;
		this.password = password;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String getPassword() {
		return password;
	}

}
