package db.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class ActionsOnCategory {

	public static List<Category> searchAll(final Connection conn) throws SQLException{
		List<Category> list = new ArrayList<>();

		String query = "SELECT * FROM categories";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		// execute the query, and get a java resultset
		ResultSet rs = preparedStmt.executeQuery(query);

		// iterate through the java resultset
		while (rs.next()) {
			list.add(new Category(rs.getString("CategoryName")));
		}
		return list;
	}

	public static void insert(final Connection conn, final String categoryName) throws SQLException {
		String query = "insert ignore into categories values(?);";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, categoryName);
		preparedStmt.executeUpdate();		
	}
}
