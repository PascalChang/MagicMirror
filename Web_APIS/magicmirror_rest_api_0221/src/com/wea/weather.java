package com.wea;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "weather")
public class weather implements Serializable{
	private int id;
	private String City;
	private int MinT;
	private int MaxT;
	private int PoP;
	private String Wx;
	private String CI;
	
	public weather() {
		
	}
	public weather(int id, String city, int minT, int maxT, int poP, String wx, String cI) {
		super();
		this.id = id;
		City = city;
		MinT = minT;
		MaxT = maxT;
		PoP = poP;
		Wx = wx;
		CI = cI;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public int getMinT() {
		return MinT;
	}
	public void setMinT(int minT) {
		MinT = minT;
	}
	public int getMaxT() {
		return MaxT;
	}
	public void setMaxT(int maxT) {
		MaxT = maxT;
	}
	public int getPoP() {
		return PoP;
	}
	public void setPoP(int poP) {
		PoP = poP;
	}
	public String getWx() {
		return Wx;
	}
	public void setWx(String wx) {
		Wx = wx;
	}
	public String getCI() {
		return CI;
	}
	public void setCI(String cI) {
		CI = cI;
	}
}