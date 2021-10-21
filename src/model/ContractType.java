package model;

public class ContractType {
	
	private final String type;
	private final int baseSalary;
	private final int wHD;
	
	public ContractType(final String contractType, final int baseSalary, final int workingHoursDuration) {
		this.type = contractType;
		this.baseSalary = baseSalary;
		this.wHD = workingHoursDuration;
	}

	public String getType() {
		return type;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public int getWorkingHoursDuration() {
		return wHD;
	}
}