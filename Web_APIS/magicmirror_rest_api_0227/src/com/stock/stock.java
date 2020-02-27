package com.stock;

public class stock {
	private int id;
	private int Stock_num;
	private String Name;
	private float Price;
	
	public stock() {
		
	}
	public stock(int id, int stock_num, String name, float price) {
		super();
		this.id = id;
		Stock_num = stock_num;
		Name = name;
		Price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock_num() {
		return Stock_num;
	}
	public void setStock_num(int stock_num) {
		Stock_num = stock_num;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
}
