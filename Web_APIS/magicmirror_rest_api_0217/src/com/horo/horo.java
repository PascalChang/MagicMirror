package com.horo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "horo")
public class horo implements Serializable{
	private int id;
	private String star_name;
	private String general_pt;
	private String career_pt;
	private String love_pt;
	private String money_pt;
	private String general_txt;
	private String career_txt;
	private String love_txt;
	private String money_txt;
	private int  lucky_num;
	private String lucky_color;
	private String matched_star;
	
	public horo() {}
	public horo(int id, String star_name, String general_pt, String career_pt, String love_pt, String money_pt,
			String general_txt, String career_txt, String love_txt, String money_txt, int lucky_num, String lucky_color,
			String matched_star) {
		super();
		this.id = id;
		this.star_name = star_name;
		this.general_pt = general_pt;
		this.career_pt = career_pt;
		this.love_pt = love_pt;
		this.money_pt = money_pt;
		this.general_txt = general_txt;
		this.career_txt = career_txt;
		this.love_txt = love_txt;
		this.money_txt = money_txt;
		this.lucky_num = lucky_num;
		this.lucky_color = lucky_color;
		this.matched_star = matched_star;
	}

	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	public String getStar_name() {
		return star_name;
	}
	@XmlElement
	public void setStar_name(String star_name) {
		this.star_name = star_name;
	}
	public String getGeneral_pt() {
		return general_pt;
	}
	@XmlElement
	public void setGeneral_pt(String general_pt) {
		this.general_pt = general_pt;
	}
	public String getCareer_pt() {
		return career_pt;
	}
	@XmlElement
	public void setCareer_pt(String career_pt) {
		this.career_pt = career_pt;
	}
	public String getLove_pt() {
		return love_pt;
	}
	@XmlElement
	public void setLove_pt(String love_pt) {
		this.love_pt = love_pt;
	}
	public String getMoney_pt() {
		return money_pt;
	}
	@XmlElement
	public void setMoney_pt(String money_pt) {
		this.money_pt = money_pt;
	}
	public String getGeneral_txt() {
		return general_txt;
	}
	@XmlElement
	public void setGeneral_txt(String general_txt) {
		this.general_txt = general_txt;
	}
	public String getCareer_txt() {
		return career_txt;
	}
	@XmlElement
	public void setCareer_txt(String career_txt) {
		this.career_txt = career_txt;
	}
	public String getLove_txt() {
		return love_txt;
	}
	@XmlElement
	public void setLove_txt(String love_txt) {
		this.love_txt = love_txt;
	}
	public String getMoney_txt() {
		return money_txt;
	}
	@XmlElement
	public void setMoney_txt(String money_txt) {
		this.money_txt = money_txt;
	}
	public int getLucky_num() {
		return lucky_num;
	}
	@XmlElement
	public void setLucky_num(int lucky_num) {
		this.lucky_num = lucky_num;
	}
	public String getLucky_color() {
		return lucky_color;
	}
	@XmlElement
	public void setLucky_color(String lucky_color) {
		this.lucky_color = lucky_color;
	}
	public String getMatched_star() {
		return matched_star;
	}
	@XmlElement
	public void setMatched_star(String matched_star) {
		this.matched_star = matched_star;
	}
	
}