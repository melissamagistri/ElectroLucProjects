package model;

import java.math.BigDecimal;

public class PurchaseOrders {

	private final int orderID;
	private final int quantity;
	private final BigDecimal unitPurchasePrice;
	private final int modelID;

	public PurchaseOrders(final int orderID, final int quantity, final BigDecimal unitPurchasePrice, 
			final int modelID) {
		this.orderID = orderID;
		this.quantity = quantity;
		this.unitPurchasePrice = unitPurchasePrice;
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

	public BigDecimal getUnitPurchasePrice() {
		return unitPurchasePrice;
	}

}
