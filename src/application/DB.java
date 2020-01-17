package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import departement.Departement;

public class DB {
	private Connection connection;
    private String url = "jdbc:mariaDB://localhost:3306/timetable";
    private String user = "root";
    private String pass = "";

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url,user,pass);
        return connection;
    }

}
