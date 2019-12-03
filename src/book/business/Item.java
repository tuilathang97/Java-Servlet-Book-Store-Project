package book.business;

import java.io.Serializable;

public class Item implements Serializable{
	
	private Book book;
	private int  quantity;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Item(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	} 
	
}
