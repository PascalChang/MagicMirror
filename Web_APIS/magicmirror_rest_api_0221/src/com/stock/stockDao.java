package com.stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class stockDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/magicmirror?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = "root";
	private String jdbcPassword = "st270428";
	
	private static final String SELECT_STOCK_NUM1_BY_RPI_ID = "select stock_num1 from user_id_info where Rpi_id =?";
	private static final String SELECT_STOCK_NUM2_BY_RPI_ID = "select stock_num2 from user_id_info where Rpi_id =?";
	private static final String SELECT_STOCK_NUM3_BY_RPI_ID = "select stock_num3 from user_id_info where Rpi_id =?";
	private static final String SELECT_STOCK_INFO_BY_STOCK_NUM = "select * from stock_info where stock_num = ?";
	
	public stockDao() {
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
	
	public int getStocknum1(String Rpi_id){
		int num1 = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_NUM1_BY_RPI_ID);) {
			preparedStatement.setString(1, Rpi_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				num1 = rs.getInt("Stock_num1");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return num1;
	}
	
	public List<stock> findStockinfo1(int num1){
		List<stock> stockList1 = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_INFO_BY_STOCK_NUM);) {
			preparedStatement.setInt(1, num1);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			stockList1 = new ArrayList<stock>();
			while (rs.next()) {
				int stock_num = rs.getInt(2);
				String name = rs.getString(3);
				float price = rs.getFloat(4);
				stockList1.add(new stock(num1, stock_num, name, price));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return stockList1;
	}
	
	public int getStocknum2(String Rpi_id){
		int num2 = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_NUM2_BY_RPI_ID);) {
			preparedStatement.setString(1, Rpi_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				num2 = rs.getInt("Stock_num2");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return num2;
	}
	
	public List<stock> findStockinfo2(int num2){
		List<stock> stockList2 = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_INFO_BY_STOCK_NUM);) {
			preparedStatement.setInt(1, num2);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			stockList2 = new ArrayList<stock>();
			while (rs.next()) {
				int stock_num = rs.getInt(2);
				String name = rs.getString(3);
				float price = rs.getFloat(4);
				stockList2.add(new stock(num2, stock_num, name, price));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return stockList2;
	}
	
	public int getStocknum3(String Rpi_id){
		int num3 = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_NUM3_BY_RPI_ID);) {
			preparedStatement.setString(1, Rpi_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				num3 = rs.getInt("Stock_num3");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return num3;
	}
	
	public List<stock> findStockinfo3(int num3){
		List<stock> stockList3 = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_INFO_BY_STOCK_NUM);) {
			preparedStatement.setInt(1, num3);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			stockList3 = new ArrayList<stock>();
			while (rs.next()) {
				int stock_num = rs.getInt(2);
				String name = rs.getString(3);
				float price = rs.getFloat(4);
				stockList3.add(new stock(num3, stock_num, name, price));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return stockList3;
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
