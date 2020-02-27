package com.news;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "news")
public class news implements Serializable{
	private int id;
	private String Title;
	private String Url;
	public news() {
		
	}
	
	public news(int id, String title, String url) {
		super();
		this.id = id;
		Title = title;
		Url = url;
	}

	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return Title;
	}
	@XmlElement
	public void setTitle(String title) {
		Title = title;
	}

	public String getUrl() {
		return Url;
	}
	@XmlElement
	public void setUrl(String url) {
		Url = url;
	}
}
