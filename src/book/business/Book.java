package book.business;

import java.io.Serializable;

public class Book implements Serializable{

	private int    idbook;
	private String title;
	private String image; 
	private String desc;
	private String author;
	private double price;
	
	public Book() {
	}

	
	public Book(int idbook, String title, String image, String desc, String author, double price) {
		this.idbook = idbook;
		this.title = title;
		this.image = image;
		this.desc = desc;
		this.author = author;
		this.price = price;
	}


	public long getIdbook() {
		return idbook;
	}

	public void setIdbook(int idbook) {
		this.idbook = idbook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
}
