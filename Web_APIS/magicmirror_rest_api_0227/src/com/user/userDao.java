package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/magicmirror?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = "root";
	private String jdbcPassword = "st270428";

	private static final String INSERT_USERS_SQL = "INSERT INTO userinfo" + "  (rpi_id, nickname, gender, date_of_birth, email) VALUES "
			+ " (?, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_RPI_ID = "select nickname, gender, date_of_birth, email from userinfo where rpi_id =?";
	private static final String SELECT_ALL_USERS = "select * from userinfo";
	private static final String DELETE_USERS_SQL = "delete from userinfo where rpi_id = ?";
	private static final String UPDATE_USERS_SQL = "update userinfo set nickname = ?, gender = ?, date_of_birth =?, email = ? where rpi_id = ?;";

	public userDao() {
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
	
	public void insertUser(user user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getRpi_id());
			preparedStatement.setString(2, user.getNickname());
			preparedStatement.setString(3, user.getGender());
			preparedStatement.setString(4, user.getDate_of_birth());
			preparedStatement.setString(5, user.getEmail());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public List<user> selectUser(String rpi_id) {
		List<user> userList = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_RPI_ID);) {
			preparedStatement.setString(1, rpi_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			userList = new ArrayList<user>();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nickname = rs.getString("nickname");
				String gender = rs.getString("gender");
				String db = rs.getString("date_of_birth");
				String email = rs.getString("email");
				userList.add(new user(rpi_id, nickname, gender, db, email));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return userList;
	}

	public List<user> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<user> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nickname = rs.getString("nickname");
				String gender = rs.getString("gender");
				String db = rs.getString("date_of_birth");
				String email = rs.getString("email");
				users.add(new user(nickname, gender, db, email));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(String rpi_id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, rpi_id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
		
	}

	public boolean updateUser(user user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getNickname());
			statement.setString(2, user.getGender());
			statement.setString(3, user.getDate_of_birth());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getRpi_id());

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
