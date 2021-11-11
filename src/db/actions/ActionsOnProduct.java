package db.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActionsOnProduct {

	public static void deleteProduct(final Connection conn, final int modelID) throws SQLException {

		//caso piu semplice senza update
		String query = "update warehouse set ModelID = null where ModelID = ?;";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, modelID);
		preparedStmt.executeUpdate();

		SalesCatalog.remove(conn, modelID);
		query = "update models set UnitInStock = 0, Discount = null where ModelID = ?;";
		preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, modelID);
		preparedStmt.executeUpdate();

	}

	public static void searchByID(final Connection conn, final int modelID) throws SQLException {
		
	}
}
