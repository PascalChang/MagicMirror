package com.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class newsDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/magicmirror?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = "root";
	private String jdbcPassword = "st270428";
	
	private static final String SELECT_ALL_NEWS = "select * from news_crawler";
	
	public newsDao() {
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
	
	public List<news> findNews(){
		List<news> newsList = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NEWS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			newsList = new ArrayList<news>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String url = rs.getString(3);
				
				newsList.add(new news(id, title, url));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return newsList;
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
