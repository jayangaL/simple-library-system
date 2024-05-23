package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.model.Book;
import com.example.simplelibrarysystem.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Book> addBook(
            @Validated
            @RequestBody Book book) {
        bookService.registerBook(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        //bookService.getAllBooks();
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
