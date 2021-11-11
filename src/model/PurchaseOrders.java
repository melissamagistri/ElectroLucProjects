package model;

public class PurchaseOrders {

	private final int orderID;
	private final int quantity;
	private final int modelID;

	public PurchaseOrders(final int orderID, final int quantity, final int modelID) {
		this.orderID = orderID;
		this.quantity = quantity;
		this.modelID = modelID;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getModelID() {
		return modelID;
	}

}
