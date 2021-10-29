package model;

public class Receipt {

	private final int receiptID;
	private final int orderID;

	public Receipt(final int orderID, final int receiptID) {
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
