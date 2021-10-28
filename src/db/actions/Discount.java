package db.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.connection.DBConnection;


public class Discount {

	public static void applyDiscount(final int modelID, final int discount) throws SQLException {

		//check if it has been insert discount = 0
		if(discount == 0) {
			Discount.removeDiscount(modelID);
			return;
		}

		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET Discount = ? WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.getMySQLConnection().prepareStatement(query);
		preparedStmt.setInt(1, discount);
		preparedStmt.setInt(2, modelID);

		//execute query
		preparedStmt.executeUpdate();

	}

	public static void removeDiscount(final int modelID) throws SQLException {
		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET Discount = null WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.getMySQLConnection().prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();
	}
}
