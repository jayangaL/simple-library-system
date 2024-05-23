package com.example.simplelibrarysystem.service.Impl;

import com.example.simplelibrarysystem.model.Book;
import com.example.simplelibrarysystem.repo.BookRepository;
import com.example.simplelibrarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book registerBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Book book) throws Exception {
        Optional<Book> book1 = bookRepository.findById(book.getId());

        if(book1.isPresent()){
            Book persisted = book1.get();
            persisted.setTitle(book.getTitle());
            persisted.setAuthor(book.getAuthor());
            persisted.setIsbn(book.getIsbn());
            persisted.setStatus(book.getStatus());
            book = persisted;
            bookRepository.save(persisted);
        }else{
            throw new RuntimeException("Book doesn't exisit!");
        }
        return book;
    }
}
