package model;

public class Purchase_certificate {

	private final int certificateID;
	private final int orderID;

	public Purchase_certificate(final int orderID, final int certificateID) {
		this.certificateID = certificateID;
		this.orderID = orderID;
	}

	public int getCertificateID() {
		return certificateID;
	}

	public int getOrderID() {
		return orderID;
	}
}
