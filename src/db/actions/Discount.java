package db.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import db.connection.DBConnection;
import model.Model;


public class Discount {

	public static Optional<SQLException> applyDiscount(final Model model, final int discount) {

		//check if it has been insert discount = 0
		if(discount == 0) {
			Discount.removeDiscount(model);
			return Optional.empty();
		}

		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET Discount = ? WHERE ModelID = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.getMySQLConnection().prepareStatement(query);
			preparedStmt.setInt(1, discount);
			preparedStmt.setInt(2, model.getModelID());

			//execute query
			preparedStmt.executeUpdate();

			//close connection
			conn.getMySQLConnection().close();
		} catch (SQLException e) {
			return Optional.of(e);
		}
		return Optional.empty();
	}

	public static Optional<SQLException> removeDiscount(final Model model) {
		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET Discount = null WHERE ModelID = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.getMySQLConnection().prepareStatement(query);
			preparedStmt.setInt(1, model.getModelID());

			//execute query
			preparedStmt.executeUpdate();

			//close connection
			conn.getMySQLConnection().close();
		} catch (SQLException e) {
			return Optional.of(e);
		}
		return Optional.empty();
	}
}
