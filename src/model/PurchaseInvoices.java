package model;

import java.sql.Date;

public class PurchaseInvoices {

	private final int invoiceID;
	private final int orderID;
	private final Date issueDate;
	private final int supplierID;

	public PurchaseInvoices(final int inviceID, final int orderID, final Date issueDate,
			final int supplierID) {
		this.invoiceID = inviceID;
		this.orderID = orderID;
		this.issueDate = issueDate;
		this.supplierID = supplierID;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public int getOrderID() {
		return orderID;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public int getSupplierID() {
		return supplierID;
	}

}
