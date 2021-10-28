package db.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.connection.DBConnection;

public class Warranty {

	public static Boolean isInWarranty(final int receiptID, final int productID) throws SQLException {
		DBConnection conn = new DBConnection();

		String query = "SELECT count(*)"
				+ "	FROM orders o, order_details od, receipts r"
				+ "	WHERE r.ReceiptID = " +receiptID
				+ "	AND TIMESTAMPDIFF(YEAR, r.IssueDate, now()) <= 2"
				+ "	AND r.OrderID = o.OrderID"
				+ "	AND o.OrderID = od.OrderID"
				+ "	AND od.ProductID = " +productID +";";
		
		PreparedStatement preparedStmt = conn.getMySQLConnection().prepareStatement(query);

		// execute the query, and get a java resultset
		return preparedStmt.executeQuery(query).isBeforeFirst();
	}
}
