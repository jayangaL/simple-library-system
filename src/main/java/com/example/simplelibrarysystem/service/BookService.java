package com.example.simplelibrarysystem.service;

import com.example.simplelibrarysystem.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Book registerBook(Book book);

    Optional<Book> findBookById(Long id);

    Book updateBook(Book book)throws Exception;
}
