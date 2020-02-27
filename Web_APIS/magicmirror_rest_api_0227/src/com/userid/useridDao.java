package com.userid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class useridDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/magicmirror?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = "root";
	private String jdbcPassword = "st270428";

	private static final String INSERT_USER_ID_SQL = "INSERT INTO user_id_info" + "  (rpi_id, weather_code, horo_code) VALUES (?, ?, ?);";
	private static final String INSERT_PASSWD_SQL = "INSERT INTO user_id_info" + "  (rpi_id, password) VALUES (?, md5(?));";
	private static final String INSERT_STOCK_NUM_SQL = "INSERT INTO user_id_info" + "  (rpi_id, stock_num1, stock_num2, stock_num3) VALUES (?, ?, ?, ?);";
	private static final String SELECT_USER_ID_BY_RPI_ID = "select rpi_id, weather_code, horo_code, stock_num1, stock_num2, stock_num3 from user_id_info where rpi_id =?";
	private static final String SELECT_ALL_USER_ID = "select * from user_id_info";
	private static final String DELETE_USER_ID_SQL = "delete from user_id_info where rpi_id = ?";
	private static final String UPDATE_USER_ID_SQL = "update user_id_info set weather_code = ?, horo_code =? where rpi_id = ?;";
	private static final String UPDATE_PASSWD_SQL = "update user_id_info set password = md5(?) where rpi_id = ?;";
	private static final String UPDATE_STOCK_NUM_SQL = "update user_id_info set stock_num1 = ?,  stock_num2 = ?, stock_num3 = ? where rpi_id = ?;";
	public useridDao() {
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
	
	public void insertUserid(userid userid) throws SQLException {
		System.out.println(INSERT_USER_ID_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_ID_SQL)) {
			preparedStatement.setString(1, userid.getRpi_id());
			preparedStatement.setInt(2, userid.getWeather_code());
			preparedStatement.setInt(3, userid.getHoro_code());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void insertPasswd(userid userid) throws SQLException {
		System.out.println(INSERT_PASSWD_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PASSWD_SQL)) {
			preparedStatement.setString(1, userid.getRpi_id());
			preparedStatement.setString(2, userid.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void insertStocknum(userid userid) throws SQLException {
		System.out.println(INSERT_USER_ID_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STOCK_NUM_SQL)) {
			preparedStatement.setString(1, userid.getRpi_id());
			preparedStatement.setInt(2, userid.getStock_num1());
			preparedStatement.setInt(3, userid.getStock_num2());
			preparedStatement.setInt(4, userid.getStock_num3());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public List<userid> selectUserid(String rpi_id) {
		List<userid> useridList = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID_BY_RPI_ID);) {
			preparedStatement.setString(1, rpi_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			useridList = new ArrayList<userid>();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String pw = rs.getString("password");
				int wc = rs.getInt("weather_code");
				int hc = rs.getInt("horo_code");
				int st1 = rs.getInt("stock_num1");
				int st2 = rs.getInt("stock_num2");
				int st3 = rs.getInt("stock_num3");
				useridList.add(new userid(rpi_id, pw, wc, hc, st1, st2, st3));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return useridList;
	}

	public List<userid> selectAllUserid() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<userid> userid = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_ID);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String rpi_id = rs.getString("rpi_id");
				String pw = rs.getString("password");
				int wc = rs.getInt("weather_code");
				int hc = rs.getInt("horo_code");
				int st1 = rs.getInt("stock_num1");
				int st2 = rs.getInt("stock_num2");
				int st3 = rs.getInt("stock_num3");
				userid.add(new userid(id, rpi_id, pw, wc, hc, st1, st2, st3));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return userid;
	}

	public boolean deleteUserid(String rpi_id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_ID_SQL);) {
			statement.setString(1, rpi_id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
		
	}

	public boolean updateUserid(userid userid) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ID_SQL);) {
			statement.setInt(1, userid.getWeather_code());
			statement.setInt(2, userid.getHoro_code());
			statement.setString(3, userid.getRpi_id());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean updatePasswd(userid userid) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWD_SQL);) {
			statement.setString(1, userid.getPassword());
			statement.setString(2, userid.getRpi_id());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean updateStocknum(userid userid) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STOCK_NUM_SQL);) {
			statement.setInt(1, userid.getStock_num1());
			statement.setInt(2, userid.getStock_num2());
			statement.setInt(3, userid.getStock_num3());
			statement.setString(4, userid.getRpi_id());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
