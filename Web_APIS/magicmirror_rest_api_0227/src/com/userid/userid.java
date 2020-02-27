package com.userid;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "userid")
public class userid implements Serializable{
	private int id;
	private String Rpi_id;
	private String Password;
	private int Weather_code;
	private int Horo_code;
	private int Stock_num1;
	private int Stock_num2;
	private int Stock_num3;
	
	public userid() {
		
	}
	
	public userid(String rpi_id,  String password) {
		super();
		Rpi_id = rpi_id;
		Password = password;
	}
	
	public userid(String rpi_id, int stock_num1, int stock_num2,
			int stock_num3) {
		super();
		Rpi_id = rpi_id;
		Stock_num1 = stock_num1;
		Stock_num2 = stock_num2;
		Stock_num3 = stock_num3;
	}
	
	public userid(String rpi_id, int weather_code, int horo_code) {
		super();
		Rpi_id = rpi_id;
		Weather_code = weather_code;
		Horo_code = horo_code;
	}
	
	public userid(String rpi_id, String password, int weather_code, int horo_code, int stock_num1, int stock_num2,
			int stock_num3) {
		super();
		Rpi_id = rpi_id;
		Password = password;
		Weather_code = weather_code;
		Horo_code = horo_code;
		Stock_num1 = stock_num1;
		Stock_num2 = stock_num2;
		Stock_num3 = stock_num3;
	}
	public userid(int id, String rpi_id, String password, int weather_code, int horo_code, int stock_num1, int stock_num2,
			int stock_num3) {
		super();
		this.id = id;
		Rpi_id = rpi_id;
		Password = password;
		Weather_code = weather_code;
		Horo_code = horo_code;
		Stock_num1 = stock_num1;
		Stock_num2 = stock_num2;
		Stock_num3 = stock_num3;
	}

	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getRpi_id() {
		return Rpi_id;
	}
	@XmlElement
	public void setRpi_id(String rpi_id) {
		Rpi_id = rpi_id;
	}

	public String getPassword() {
		return Password;
	}
	@XmlElement
	public void setPassword(String password) {
		Password = password;
	}

	public int getWeather_code() {
		return Weather_code;
	}
	@XmlElement
	public void setWeather_code(int weather_code) {
		Weather_code = weather_code;
	}

	public int getHoro_code() {
		return Horo_code;
	}
	@XmlElement
	public void setHoro_code(int horo_code) {
		Horo_code = horo_code;
	}

	public int getStock_num1() {
		return Stock_num1;
	}
	@XmlElement
	public void setStock_num1(int stock_num1) {
		Stock_num1 = stock_num1;
	}

	public int getStock_num2() {
		return Stock_num2;
	}
	@XmlElement
	public void setStock_num2(int stock_num2) {
		Stock_num2 = stock_num2;
	}

	public int getStock_num3() {
		return Stock_num3;
	}
	@XmlElement
	public void setStock_num3(int stock_num3) {
		Stock_num3 = stock_num3;
	}	
}
