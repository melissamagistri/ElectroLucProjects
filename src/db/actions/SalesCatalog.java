package db.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesCatalog {

	public static void remove(final Connection conn, final int modelID) throws SQLException {

		String query = "UPDATE models SET InSale = false WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();

	}

	public static void insert(final Connection conn, final int modelID) throws SQLException {

		String query = "UPDATE models SET InSale = true WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();

	}
}
