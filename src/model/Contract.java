package model;

import java.util.Date;
import java.util.Optional;

public class Contract {

	private final String contractType;
	private final Date hireDate;
	private final Optional<Date> endDate;
	private final int employeeID;

	private Contract(final String contractType, final Date hireDate, final Optional<Date> endDate, 
			final int employeeID) {
		this.contractType = contractType;
		this.hireDate = hireDate;
		this.endDate = endDate;
		this.employeeID = employeeID;
	}

	public Contract(final String contractType, final Date hireDate, 
			final int employeeID) {
		this(contractType, hireDate, Optional.empty(), employeeID);
	}

	public Contract(final String contractType, final Date hireDate, final Date endDate,
			final int employeeID) {
		this(contractType, hireDate, Optional.of(endDate), employeeID);
	}

	public String getContractType() {
		return contractType;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public Optional<Date> getEndDate() {
		return endDate;
	}

	public int getEmployeeID() {
		return employeeID;
	}

}
