package modelutil;

import java.util.Date;
import java.util.Optional;


public abstract class Order {

	private int orderCode;
	private Date orderDate;
	private Optional<String> customerEmail;
	private Optional<Integer> employeeID;
	private String orderType;
	private String paymentMethod;
	private int productID;

	public int getProductID() {
		return productID;
	}

	protected void setProductID(int productID) {
		this.productID = productID;
	}

	public int getOrderCode() {
		return orderCode;
	}

	protected void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	protected void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Optional<String> getCustomerEmail() {
		return customerEmail;
	}

	protected void setCustomerEmail(Optional<String> customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Optional<Integer> getEmployeeID() {
		return employeeID;
	}

	protected void setEmployeeID(Optional<Integer> employeeID) {
		this.employeeID = employeeID;
	}

	public String getOrderType() {
		return orderType;
	}

	protected void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	protected void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
