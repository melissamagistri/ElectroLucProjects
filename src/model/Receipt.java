package model;

import java.util.Date;

public class Receipt {

	private final int receiptID;
	private final Date dateIssue;
	private final int orderID;

	public Receipt(final int orderID, final int receiptID, final Date dateIssue) {
		this.receiptID = receiptID;
		this.dateIssue = dateIssue;
		this.orderID = orderID;
	}

	public int getReceiptID() {
		return receiptID;
	}

	public Date getReceiptDateIssue() {
		return dateIssue;
	}

	public int getOrderID() {
		return orderID;
	}
}
