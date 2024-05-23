package com.example.simplelibrarysystem.service.Impl;

import com.example.simplelibrarysystem.model.BookBorrowing;
import com.example.simplelibrarysystem.repo.BookBorrowingRepository;
import com.example.simplelibrarysystem.service.BookBorrowingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookBorrowingServiceImpl implements BookBorrowingService {

    @Autowired
    private BookBorrowingRepository borrowingRepository;

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return borrowingRepository.save(bookBorrowing);
    }

    @Override
    public List<BookBorrowing> fetchAllBorrowingBooks() {
        return borrowingRepository.findAllByReturned(false);
    }

    @Override
    public List<BookBorrowing> getBorrowingByUserId(Long userId) {
        return borrowingRepository.findByUserId(userId);
    }

    @Override
    public BookBorrowing findByBookId(Long bookId) {
        return borrowingRepository.findByBookId(bookId);
    }
}
