package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import modelutil.Order;

public class OrderInStore extends Order {

	public OrderInStore(final int orderID, final Date orderDate, final int modelID, final BigDecimal totalAmount,
			final String paymentMethod, final int employeeID) {
		this.setOrderCode(orderID);
		this.setOrderDate(orderDate);
		this.setPaymentMethod(paymentMethod);
		this.setModelID(modelID);
		this.setTotalAmount(totalAmount);
		this.setCustomerEmail(Optional.empty());
		this.setOrderType("in store");
		this.setEmployeeID(Optional.of(employeeID));
	}
}
