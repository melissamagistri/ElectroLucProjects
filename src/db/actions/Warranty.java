package db.actions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.connection.DBConnection;

public class Warranty {

	public static Boolean isInWarranty(final int receiptID, final int productID) {
		DBConnection conn = new DBConnection();

		String query = "SELECT COUNT(*)"
				+ "FROM orders o, receipts r, order_deteils od "
				+ "WHERE r.ReceiptID = " +receiptID +" "
				+ "AND r.OrderID = o.OrderID "
				+ "AND o.OrderID = od.OrderID "
				+ "AND od.ProductID = " +productID +" "
				+ "AND year(TIMEDIFF(CURRENT_DATE(), r.IssueDate)) < 2;";
		try {
			PreparedStatement preparedStmt = conn.getMySQLConnection().prepareStatement(query);

		    // execute the query, and get a java resultset
			return preparedStmt.executeQuery(query).next();

		} catch (SQLException e) {
			System.err.print(e);
			return false;
		}
	}
}
