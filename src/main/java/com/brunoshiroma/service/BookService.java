package com.brunoshiroma.service;

import com.brunoshiroma.entity.BookEntity;
import io.smallrye.mutiny.Uni;

public interface BookService {

    Uni<BookEntity> getBook(Long bookId);

    Uni<BookEntity> createBook(BookEntity book);

}
