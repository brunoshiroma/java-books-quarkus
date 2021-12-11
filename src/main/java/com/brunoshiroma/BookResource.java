package com.brunoshiroma;

import com.brunoshiroma.entity.BookEntity;
import com.brunoshiroma.service.BookService;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService){
        this.bookService = bookService;
    }

    @GET
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BookEntity> getBookById(@PathParam("bookId") Long bookId) {
        return bookService.getBook(bookId);
    }

    @POST
    public Uni<BookEntity> createBook(BookEntity book){
        return bookService.createBook(book);
    }
}