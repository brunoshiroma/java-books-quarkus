package com.brunoshiroma.service.impl;

import com.brunoshiroma.entity.BookEntity;
import com.brunoshiroma.repository.BookRepository;
import com.brunoshiroma.service.BookService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Uni<BookEntity> getBook(Long bookId) {
        return BookEntity.findById(bookId);
    }

    @Override
    public Uni<BookEntity> createBook(BookEntity book) {
        return bookRepository.create(book)
                .replaceWith(Uni.createFrom().item(book));
    }

}
