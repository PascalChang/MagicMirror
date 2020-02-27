package com.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "user")
public class user implements Serializable{
	private int id;
	private String rpi_id;
	private String nickname;
	private String gender;
	private String date_of_birth;
	private String email;
	
	public user() {
		
	}
	
	public user(String nickname, String gender, String date_of_birth, String email) {
		super();
		this.nickname = nickname;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.email = email;
	}
	
	public user(String rpi_id, String nickname, String gender, String date_of_birth, String email) {
		super();
		this.rpi_id = rpi_id;
		this.nickname = nickname;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.email = email;
	}

	public user(int id, String rpi_id, String nickname, String gender, String date_of_birth, String email) {
		super();
		this.id = id;
		this.rpi_id = rpi_id;
		this.nickname = nickname;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	public String getRpi_id() {
		return rpi_id;
	}
	@XmlElement
	public void setRpi_id(String rpi_id) {
		this.rpi_id = rpi_id;
	}
	public String getNickname() {
		return nickname;
	}
	@XmlElement
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	@XmlElement
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	@XmlElement
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getEmail() {
		return email;
	}
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}	
}
