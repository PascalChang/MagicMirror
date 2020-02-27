package com.horo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class horoDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/magicmirror?useSSL=false&serverTimezone=CST";
	private String jdbcUsername = "root";
	private String jdbcPassword = "st270428";
	
	private static final String SELECT_HORO_CODE_BY_RPI_ID = "select horo_code from user_id_info where Rpi_id =?";
	private static final String SELECT_HORO_BY_ID = "select * from horoscope where id = ?";
	
	public horoDao() {
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
	public int getHoroid(String Rpi_id){
		int horo = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HORO_CODE_BY_RPI_ID);) {
			preparedStatement.setString(1, Rpi_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
			horo = rs.getInt("Horo_code");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return horo;
	}
	public List<horo> findHoroinfo(int horo){
		List<horo> horoList = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HORO_BY_ID);) {
			preparedStatement.setInt(1, horo);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			horoList = new ArrayList<horo>();
			while (rs.next()) {
				String star_name = rs.getString(2);
				String general_pt= rs.getString(3);
				String career_pt= rs.getString(4);
				String love_pt= rs.getString(5);
				String money_pt= rs.getString(6);
				String general_txt= rs.getString(7);
				String career_txt= rs.getString(8);
				String love_txt= rs.getString(9);
				String money_txt= rs.getString(10);
				int lucky_num = rs.getInt(11);
				String lucky_color = rs.getString(12);
				String matched_star= rs.getString(13);
				horoList.add(new horo(horo, star_name, general_pt, career_pt, love_pt, money_pt, general_txt, career_txt, love_txt, money_txt, lucky_num, lucky_color, matched_star));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return horoList;
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
