package db.actions;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.connection.DBConnection;

public class SalesCatalog {

	public static void insert(final int modelID) throws SQLException {
		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET SalesCatalogMembership = true WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.getMySQLConnection().prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();
	}

	public static void remove(final int modelID) throws SQLException {
		DBConnection conn = new DBConnection();

		String query = "UPDATE models SET SalesCatalogMembership = false WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.getMySQLConnection().prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();

	}
}
