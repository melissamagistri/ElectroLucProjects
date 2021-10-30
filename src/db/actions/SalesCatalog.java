package db.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesCatalog {

	public static void remove(final Connection conn, final int modelID) throws SQLException {

		String query = "UPDATE models SET SalesCatalogMembership = false WHERE ModelID = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, modelID);

		//execute query
		preparedStmt.executeUpdate();

	}
}
