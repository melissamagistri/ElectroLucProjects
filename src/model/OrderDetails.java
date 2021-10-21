package model;

public class OrderDetails {

	private final int quantity;
	private final int orderID;
	private final int prodID;

	public OrderDetails(final int prodID, final int orderID, 
			final int quantity) {
		this.orderID = orderID;
		this.prodID = prodID;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getProdID() {
		return prodID;
	}

}
