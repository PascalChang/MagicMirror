package com.wea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class weatherDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/magicmirror?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = "root";
	private String jdbcPassword = "st270428";
	
	private static final String SELECT_WEA_CODE_BY_RPI_ID = "select weather_code from user_id_info where Rpi_id =?";
	private static final String SELECT_WEA_BY_ID = "select * from weather_crawler where id = ?";
	
	public weatherDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public int getWeatherid(String Rpi_id){
		int wea = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WEA_CODE_BY_RPI_ID);) {
			preparedStatement.setString(1, Rpi_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
			wea = rs.getInt("Weather_code");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return wea;
	}
	public List<weather> findWeatherinfo(int wea){
		List<weather> weaList = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WEA_BY_ID);) {
			preparedStatement.setInt(1, wea);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			weaList = new ArrayList<weather>();
			while (rs.next()) {
				String city = rs.getString(2);
				int mint = rs.getInt(3);
				int maxt = rs.getInt(4);
				int pop = rs.getInt(5);
				String wx = rs.getString(6);
				String ci = rs.getString(7);
				weaList.add(new weather(wea, city, mint, maxt, pop, wx, ci));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return weaList;
	}
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
