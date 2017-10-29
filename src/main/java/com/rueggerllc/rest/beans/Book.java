package com.rueggerllc.rest.beans;

public class Book {

	private int id;
	private String title;
	private String author;
	private int numberOfPages;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Book.id: " + id);
		buffer.append("\nBook.title: " + title);
		buffer.append("\nBook.author: " + author);
		buffer.append("\nBook.numberOfPages: " + numberOfPages);
		return buffer.toString();
	}
	
    
}