package db.actions;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import db.connection.DBConnection;
import model.Model;

public class ModelAction {

	public static List<Model> searchModelInSalesCatalog(final String modelName) throws SQLException {

		DBConnection conn = new DBConnection();

		List<Model> res = new ArrayList<>();
		String query = "SELECT * FROM models WHERE ModelName = " +modelName +" AND SalesCatalogMembership = true";
		PreparedStatement preparedStmt = conn.getMySQLConnection().prepareStatement(query);

		// execute the query, and get a java resultset
		ResultSet rs = preparedStmt.executeQuery(query);

		Optional<Blob> image = Optional.empty();
		Optional<Integer> discount = Optional.empty();
		Boolean sales;

		// iterate through the java resultset
		while (rs.next()) {
			image = Optional.ofNullable(rs.getBlob("ModelImage"));
		    discount = Optional.ofNullable(rs.getInt("Discount"));
		    sales = (rs.getInt("SalesCatalogMembership")==1) ? true : false;

		    res.add(new Model(rs.getInt("ModelID"), rs.getString("ModelName"), rs.getString("Brand"),
		    		rs.getString("Description"), image, rs.getBigDecimal("UnitPrice"),
		    		discount, rs.getInt("UnitInStock"), rs.getInt("MaxQuantytyPerOrder"), 
		    		rs.getString("Category"), rs.getString("Shelf"), rs.getString("Lane"), 
		    		rs.getString("Compartment"), sales));
		}
		return res;
	}
}
