package modelutil;

public abstract class Person {

	private String firstName;
	private String secondName;
	private String fiscalCode;

	protected String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	protected String getSecondName() {
		return secondName;
	}

	protected void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	protected String getFiscalCode() {
		return fiscalCode;
	}

	protected void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

}
