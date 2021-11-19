package db.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import util.Pair;
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
		    	rs.getString("Description"), rs.getBigDecimal("UnitSellingPrice"),
		    	discount, rs.getInt("UnitInStock"),	rs.getString("Category"), sales));
		}
		return Optional.empty();
	}


	public static List<Model> search(final Connection conn, final List<Pair<String,String>> modelAttr) throws SQLException{
		List<Model> list = new ArrayList<>();
		boolean inizialize = false;
		int i = 0;
		String query = "";

		for(Pair<String,String> condition : modelAttr){
			if(!condition.getSecond().isBlank()){
				if(!inizialize){
					query = query 
							+"select * "
							+ "from (select * from models "
							+ "where " +condition.getFirst() +"='" +condition.getSecond() +"') as a" +i++;
					inizialize = true;
				} else{
					query = query 
							+" NATURAL JOIN "
							+ "(select ModelID from models "
							+ "where " +condition.getFirst() +"='" +condition.getSecond() +"') as a" +i++;
				}
			}
		}

		if(!query.isBlank()) {
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			// execute the query, and get a java resultset
			ResultSet rs = preparedStmt.executeQuery(query);

			Optional<Integer> discount = Optional.empty();
			Boolean sales;

			// iterate through the java resultset
			while (rs.next()) {
				discount = Optional.ofNullable(rs.getInt("Discount"));
				sales = (rs.getInt("InSale")==1) ? true : false;

				list.add(new Model(rs.getInt("ModelID"), rs.getString("ModelName"), rs.getString("Brand"),
						rs.getString("Description"), rs.getBigDecimal("UnitSellingPrice"),
						discount, rs.getInt("UnitInStock"),	rs.getString("Category"), sales));
			}
		}
		return list;
	}


	public static List<Model> searchAll(final Connection conn) throws SQLException{
		List<Model> list = new ArrayList<>();

		String query = "SELECT * FROM models WHERE InSale = true";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		// execute the query, and get a java resultset
		ResultSet rs = preparedStmt.executeQuery(query);

		Optional<Integer> discount = Optional.empty();
		Boolean sales;

		// iterate through the java resultset
		while (rs.next()) {
			discount = Optional.ofNullable(rs.getInt("Discount"));
			sales = (rs.getInt("InSale")==1) ? true : false;

			list.add(new Model(rs.getInt("ModelID"), rs.getString("ModelName"), rs.getString("Brand"),
					rs.getString("Description"), rs.getBigDecimal("UnitSellingPrice"),
					discount, rs.getInt("UnitInStock"),	rs.getString("Category"), sales));
		}
		return list;
	}
}
