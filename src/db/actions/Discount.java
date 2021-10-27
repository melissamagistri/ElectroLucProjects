package db.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import db.connection.DBConnection;


public class Discount {

	public static Optional<SQLException> applyDiscount(final int modelID, final int discount) {

		//check if it has been insert discount = 0
		if(discount == 0) {
			Discount.removeDiscount(modelID);
			return Optional.empty();
		}

		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET Discount = ? WHERE ModelID = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.getMySQLConnection().prepareStatement(query);
			preparedStmt.setInt(1, discount);
			preparedStmt.setInt(2, modelID);

			//execute query
			preparedStmt.executeUpdate();

		} catch (SQLException e) {
			return Optional.of(e);
		}
		return Optional.empty();
	}

	public static Optional<SQLException> removeDiscount(final int modelID) {
		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET Discount = null WHERE ModelID = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.getMySQLConnection().prepareStatement(query);
			preparedStmt.setInt(1, modelID);

			//execute query
			preparedStmt.executeUpdate();

		} catch (SQLException e) {
			return Optional.of(e);
		}
		return Optional.empty();
	}
}
