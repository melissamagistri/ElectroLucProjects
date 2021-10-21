package model;

public class CostumerAccount {

	private final String email;
	private final String password;
	private final String fiscalCode;

	public CostumerAccount(final String fiscalCode, final String email, final String password) {
		this.email = email;
		this.password = password;
		this.fiscalCode = fiscalCode;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getCostumerFiscalCode() {
		return fiscalCode;
	}

}
