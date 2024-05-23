package com.example.simplelibrarysystem.service;

import com.example.simplelibrarysystem.model.BookBorrowing;

import java.util.List;

public interface BookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);
    List<BookBorrowing> fetchAllBorrowingBooks();
    List<BookBorrowing> getBorrowingByUserId(Long userId);
    BookBorrowing findByBookId(Long bookId);
}
