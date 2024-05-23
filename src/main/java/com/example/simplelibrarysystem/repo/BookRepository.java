package com.example.simplelibrarysystem.repo;

import com.example.simplelibrarysystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
