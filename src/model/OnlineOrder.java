package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

import modelutil.Order;

public class OnlineOrder extends Order {

	public OnlineOrder(final int orderID, final Date orderDate, final int modelID, final BigDecimal totalAmount,
			final String paymentMethod, final String customerEmail) {
		this.setOrderCode(orderID);
		this.setOrderDate(orderDate);
		this.setModelID(modelID);
		this.setTotalAmount(totalAmount);
		this.setPaymentMethod(paymentMethod);
		this.setCustomerEmail(Optional.of(customerEmail));
		this.setOrderType("online");
		this.setEmployeeID(Optional.empty());
	}

}
