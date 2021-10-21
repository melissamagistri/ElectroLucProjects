package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import modelutil.Order;

public class OrderInStore extends Order {

	public OrderInStore(final int orderID, final Date orderDate,final BigDecimal totalAmount, final String paymentMethod,
			final int employeeID) {
		this.setOrderCode(orderID);
		this.setOrderDate(orderDate);
		this.setTotalAmount(totalAmount);
		this.setPaymentMethod(paymentMethod);
		this.setCustomerEmail(Optional.empty());
		this.setOrderType("OnlineInStore");
		this.setEmployeeID(Optional.of(employeeID));
	}
}
