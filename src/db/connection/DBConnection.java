package db.connection;

import java.sql.*;
import java.util.Optional;

public class DBConnection {
    
    //nome del database
    private final String DB_NAME = "negozio elettronica";

    //MySQL locale
    final String DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URI = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
	final String DB_USER_NAME = "root";
	final String DB_PASSWORD = "LucCarino2000@";

	public Optional<Connection> getMySQLConnection() throws ClassNotFoundException, SQLException  {
	    Class.forName(DRIVER);
	    Optional<Connection> connection = Optional.ofNullable(DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD));
	    return connection;
    }
}
