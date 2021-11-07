package model;

public class OrderTable {

	private int orderId;
	private String modelName;
	private int modelPrice;
	private String orderDate;
	
	public OrderTable(int orderID, String modelName, String orderDate, int modelPrice) {
		this.orderId = orderID;
		this.modelName = modelName;
		this.orderDate = orderDate;
		this.modelPrice = modelPrice;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getModelPrice() {
		return modelPrice;
	}

	public void setModelPrice(int modelPrice) {
		this.modelPrice = modelPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	
	
	
}
