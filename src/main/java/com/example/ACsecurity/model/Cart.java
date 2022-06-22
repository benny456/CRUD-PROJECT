package com.example.ACsecurity.model;




public class Cart {

	private int id;
	private String name;
	private String price;
	private int quantity;
	private String image;
	
		
	
	public Cart(int id, String name, String price, int i, String image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = i;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "cart [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", image=" + image
				+ "]";
	}
	
	
	
}
