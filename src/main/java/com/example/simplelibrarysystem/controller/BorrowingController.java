package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.model.Book;
import com.example.simplelibrarysystem.model.BookBorrowing;
import com.example.simplelibrarysystem.model.BookStatus;
import com.example.simplelibrarysystem.service.BookBorrowingService;
import com.example.simplelibrarysystem.service.BookService;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/borrowing")
public class BorrowingController {
    @Autowired
    private BookBorrowingService bookBorrowingService;

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookBorrowing borrowABook(@RequestBody BookBorrowing bookDetails) throws Exception {
        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBookId(bookDetails.getBookId());
        bookBorrowing.setUserId(bookDetails.getUserId());
        bookBorrowing.setReturned(false);
        bookBorrowing = bookBorrowingService.save(bookBorrowing);

//        Book book = new Book();
        Optional<Book> book = bookService.findBookById(bookDetails.getBookId());
        if(book.isPresent()){
            Book bk = book.get();
        bk.setId(bookDetails.getBookId());
        if(bk.getStatus()==BookStatus.BORROWED){
            throw new RuntimeException("Book is already borrowed!");
        }else{
            bk.setStatus(BookStatus.BORROWED);

            bookService.updateBook(bk);
        }

        }
        return bookBorrowing;
    }

    @PostMapping(value = "/return/{id}")
    public BookBorrowing returnABook(@PathVariable Long id) throws Exception {
        BookBorrowing bookBorrowing = bookBorrowingService.findByBookId(id);
        bookBorrowing.setReturned(true);
        bookBorrowing = bookBorrowingService.save(bookBorrowing);

//        Book book = new Book();
        Optional<Book> book = bookService.findBookById(id);
        if(book.isPresent()){
            Book bk = book.get();
            bk.setId(id);
            bk.setStatus(BookStatus.AVAILABLE);

            bookService.updateBook(bk);
        }

        return bookBorrowing;
    }

    @ExceptionHandler({ RuntimeException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(){
        ErrorMessage message = new ErrorMessage("Please re-check user inputs!");
        return message;
    }
}
