package com.rueggerllc.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.rueggerllc.rest.beans.Book;
import com.rueggerllc.rest.beans.BookDetails;

@Path("/books")
@Singleton
public class BookResource {
	
	private static Logger logger = Logger.getLogger(BookResource.class);
	private List<Book> books = null;
	
	@GET
	@Path("books")
	@Produces({"application/xml", "application/json"})
	public Response fetchBooks(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		logger.info("Retrieving Books");
		getOrCreateBooks(); 
		if (books != null) {
			return Response.ok(books, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Books Not Found").build();
		}
	}
	
	@GET
	@Path("/book/id/{id}")
	@Produces({"application/xml", "application/json"})
	public Response fetchBook(@PathParam("id") int id, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
		logger.info("Retrieving Book " + id);
		getOrCreateBooks(); 
		Book book = fetchBook(id);
		if (book != null) {
			return Response.ok(book, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Books Not Found").build();
		}
	}
	
	
	@POST
	@Path("/book")
	@Produces({"application/xml", "application/json"})
	public Response createBook(Book bookIn, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
		logger.info("Create Book from:\n" + bookIn);
		getOrCreateBooks(); 
		Book createdBook = createBook(bookIn);
		if (createdBook != null) {
			return Response.ok(createdBook, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Could Not Create Book").build();
		}
	}
	
	@PUT
	@Path("/book/id/{id}")
	@Produces({"application/xml", "application/json"})
	public Response updateBook(Book bookIn, @PathParam("id") int id, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
		logger.info("Update Book from:\n" + bookIn);
		getOrCreateBooks(); 
		Book bookToUpdate = fetchBook(id);
		if (bookToUpdate != null) {
			bookToUpdate.setAuthor(bookIn.getAuthor());
			bookToUpdate.setTitle(bookIn.getTitle());
			bookToUpdate.setNumberOfPages(bookIn.getNumberOfPages());			
			return Response.ok(bookToUpdate, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Could Not Update Book").build();
		}
	}
	
	@DELETE
	@Path("/book/id/{id}")
	public Response deleteBook(@PathParam("id") int id, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
		logger.info("Delete Book " + id);
		getOrCreateBooks();
		deleteBook(id);
		return Response.noContent().build();
	}
	
	private void deleteBook(int id) {
		getOrCreateBooks();
		Book book = fetchBook(id);
		books.remove(book);
	}
	
	private Book createBook(Book bookIn) {
		getOrCreateBooks();
		Book newBook = new Book();
		newBook.setId(books.size());
		newBook.setAuthor(bookIn.getAuthor());
		newBook.setTitle(bookIn.getTitle());
		newBook.setNumberOfPages(bookIn.getNumberOfPages());
		books.add(newBook);
		return newBook;
	}
	
	private Book fetchBook(int id) {
		for (Book book : books) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	
	
	private void getOrCreateBooks() {
		if (books != null) {
			return;
		}
		books = new ArrayList<Book>();
		for (int i = 0; i < 10; i++) {
			Book book = new Book();
			book.setId(i);
			book.setTitle("Title" + i);
			book.setNumberOfPages(311+i);
			book.setAuthor("Author"+i);
			BookDetails details = new BookDetails();
			details.setDescription("Stupendous!");
			details.setRating(5);
			book.setDetails(details);
			books.add(book);
		}
	}
	
	
	

}
