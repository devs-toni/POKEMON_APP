package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploJDBC {
	
	public static void main(String[] args) {
		
		final String DB = "jdbc:mysql://localhost:3306/udemy";
		final String pass = "";
		final String user = "root";
		String sentence = "SELECT * FROM prueba";
		
		try {
			Connection conexion = DriverManager.getConnection(DB, user, pass);
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement.executeQuery(sentence);
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	

}
