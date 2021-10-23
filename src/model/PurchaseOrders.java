package model;

import java.math.BigDecimal;

public class PurchaseOrders {

	private final int orderID;
	private final int quantity;
	private final BigDecimal netPrice;
	private final int modelID;

	public PurchaseOrders(final int orderID, final int quantity, final BigDecimal netPrice, final int modelID) {
		this.orderID = orderID;
		this.quantity = quantity;
		this.netPrice = netPrice;
		this.modelID = modelID;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}

	public int getModelID() {
		return modelID;
	}

}
