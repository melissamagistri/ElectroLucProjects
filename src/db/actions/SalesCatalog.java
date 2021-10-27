package db.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import db.connection.DBConnection;

public class SalesCatalog {

	public static Optional<SQLException> insert(final int modelID) {
		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET SalesCatalogMembership = true WHERE ModelID = ?";
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

	public static Optional<SQLException> remove(final int modelID) {
		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET SalesCatalogMembership = false WHERE ModelID = ?";
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
