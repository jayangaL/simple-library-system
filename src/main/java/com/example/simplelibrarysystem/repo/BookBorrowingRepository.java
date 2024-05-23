package com.example.simplelibrarysystem.repo;

import com.example.simplelibrarysystem.model.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookBorrowingRepository extends JpaRepository<BookBorrowing,Long> {
    List<BookBorrowing> findByUserId(Long userId);
    BookBorrowing findByBookId(Long bookId);
    List<BookBorrowing> findAllByReturned(Boolean returned);
}
