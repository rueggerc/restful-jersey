package com.rueggerllc.rest.beans;

public class BookDetails {

	private String description;
	private int rating;
	

	
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("\nBookDetails.description: " + description);
		buffer.append("\nBookDetails.rating: " + rating);
		return buffer.toString();
	}
	
    
}