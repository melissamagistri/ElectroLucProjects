package db.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import model.Model;

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

	public static Optional<Model> searchByID(final Connection conn, final int modelID) throws SQLException {
		String query = "SELECT * FROM models WHERE ModelID = " +modelID +";";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		// execute the query, and get a java resultset
		ResultSet rs = preparedStmt.executeQuery(query);

		Optional<Integer> discount = Optional.empty();
		Boolean sales;

		// iterate through the java resultset
		if (rs.isBeforeFirst() && rs.next()) {
		    discount = Optional.ofNullable(rs.getInt("Discount"));
		    sales = (rs.getInt("InSale")==1) ? true : false;

		    return Optional.of(new Model(rs.getInt("ModelID"), rs.getString("ModelName"), rs.getString("Brand"),
		    	rs.getString("Description"), rs.getBigDecimal("UnitSellingPrice"), rs.getBigDecimal("UnitPurchasePrice"),
		    	discount, rs.getInt("UnitInStock"),	rs.getString("Category"), sales));
		}
		return Optional.empty();
	}
}
