package model;

import java.util.Date;

public class Receipt {

	private final int receiptID;
	private final int orderID;

	public Receipt(final int orderID, final Date dateIssue) {
		this.receiptID = receiptID;
		this.orderID = orderID;
	}

	public int getReceiptID() {
		return receiptID;
	}

	public int getOrderID() {
		return orderID;
	}
}
