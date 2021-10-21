package model;

import java.util.Date;
import java.util.Optional;

public class Contract {

	private final String contractType;
	private final Date hireDate;
	private final Optional<Date> firedDate;
	private final int employeeID;

	private Contract(final String contractType, final Date hireDate, final Optional<Date> firedDate, 
			final int employeeID) {
		this.contractType = contractType;
		this.hireDate = hireDate;
		this.firedDate = firedDate;
		this.employeeID = employeeID;
	}

	public Contract(final String contractType, final Date hireDate, 
			final int employeeID) {
		this(contractType, hireDate, Optional.empty(), employeeID);
	}

	public Contract(final String contractType, final Date hireDate, final Date firedDate,
			final int employeeID) {
		this(contractType, hireDate, Optional.of(firedDate), employeeID);
	}

	public String getContractType() {
		return contractType;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public Optional<Date> getFiredDate() {
		return firedDate;
	}

	public int getEmployeeID() {
		return employeeID;
	}

}
