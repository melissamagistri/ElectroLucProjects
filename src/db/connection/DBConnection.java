package db.connection;

import java.sql.*;

public class DBConnection {
    
    //nome del database
    private final String DB_NAME = "pippodb";

    //MySQL locale
    final String DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URI = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
	final String DB_USER_NAME = "root";
	final String DB_PASSWORD = "luccarino";

	public Connection getMySQLConnection()  {
		
		Connection connection = null;
	    try {
	    	System.out.println("DataSource.getConnection() driver = " + DRIVER);
	        Class.forName(DRIVER);
	        System.out.println("DataSource.getConnection() dbUri = " + DB_URI);
	        connection = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD);
	    }catch (ClassNotFoundException e) {
	    	new Exception(e.getMessage());
	        System.out.println("Errore "+ e.getMessage());
	    }catch(SQLException e) {
	    	new Exception(e.getMessage());
	    	System.out.println("Errore "+ e.getMessage());
	    }
	    return connection;
    }
}
