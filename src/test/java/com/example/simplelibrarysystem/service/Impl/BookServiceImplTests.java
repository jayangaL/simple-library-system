package com.example.simplelibrarysystem.service.Impl;

import com.example.simplelibrarysystem.model.Book;
import com.example.simplelibrarysystem.model.BookStatus;
import com.example.simplelibrarysystem.repo.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTests {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @Test
    void shouldSaveBookSuccessfully(){
         book = new Book(1L,"ISBN-117-8881-uy78","She","Rider Hagard", BookStatus.AVAILABLE);
        Book savedBook = bookRepository.save(book);
       // assertThat(savedBook).isNotNull();
        verify(bookRepository).save(any(Book.class));
        Book save = bookService.registerBook(book);
        Assertions.assertEquals(save, savedBook);
    }

    @Test
    void shouldReturnFindAll(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"223-111","HP1","JK Rowling",BookStatus.AVAILABLE));
        books.add(new Book(2L,"223-121","HP2","JK Rowling",BookStatus.AVAILABLE));
        books.add(new Book(3L,"223-131","HP3","JK Rowling",BookStatus.AVAILABLE));

        given(bookRepository.findAll()).willReturn(books);

        List<Book> exp = bookService.getAllBooks();
        Assertions.assertEquals(books, exp);
    }

    @Test
    void shouldReturnBookWithId(){
        Book book = new Book(1L,"ISBN-117-8881-uy78","She","Rider Hagard", BookStatus.AVAILABLE);
        Book b1 = bookService.registerBook(book);
//        given(bookRepository.findById(1L)).willReturn(b1);
        Optional<Book> bk = bookService.findBookById(1L);
        bk.ifPresent(Assertions::assertNotNull);
    }

    @Test
    void shouldUpdateBookProps() throws Exception {
        Book book = new Book(1L,"ISBN-117-8881-uy78","She","Rider Hagard", BookStatus.AVAILABLE);
        Book b1 = bookService.registerBook(book);

       if(bookService.findBookById(1L).isPresent())
       {
           b1 = bookService.findBookById(1L).get();
           b1.setTitle("Ayesha");
           Book b2 = bookService.updateBook(b1);
           Assertions.assertEquals(b2.getTitle(),"Ayesha");
       }


    }
}
