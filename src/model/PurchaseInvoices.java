package model;

import java.math.BigDecimal;
import java.sql.Date;

public class PurchaseInvoices {

	private final int invoiceID;
	private final int orderID;
	private final Date issueDate;
	private final BigDecimal totalAmount;
	private final int supplierID;

	public PurchaseInvoices(final int inviceID, final int orderID, final Date issueDate, final BigDecimal totalAmount,
			final int supplierID) {
		this.invoiceID = inviceID;
		this.orderID = orderID;
		this.issueDate = issueDate;
		this.totalAmount = totalAmount;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public int getSupplierID() {
		return supplierID;
	}

}
