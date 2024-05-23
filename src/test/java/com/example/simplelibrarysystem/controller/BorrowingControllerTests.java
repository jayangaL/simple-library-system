package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.model.Book;
import com.example.simplelibrarysystem.model.BookBorrowing;
import com.example.simplelibrarysystem.model.BookStatus;
import com.example.simplelibrarysystem.model.Users;
import com.example.simplelibrarysystem.service.BookBorrowingService;
import com.example.simplelibrarysystem.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BorrowingController.class)
public class BorrowingControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookBorrowingService service;

    @MockBean
    private BookService bookService;

    @Test
    public void givenBooks_whenBorrowABook_thenReturnJsonArray() throws Exception {
        Book book = new Book(1L,"ISBN-117-8881-uy78","She","Rider Hagard", BookStatus.AVAILABLE);
        Users users = new Users(1L,"nur hazwani","nur.h@gmail.com");
        BookBorrowing bookBorrowing = new BookBorrowing(1L,1L,1L,false);
      //  Book book = new Book(1L,"ISBN-117-8881-uy78","She","Rider Hagard", BookStatus.AVAILABLE);

        List<Book> allBooks = List.of(book);

        given(bookService.getAllBooks()).willReturn(allBooks);
        given(service.findByBookId(1L)).willReturn(bookBorrowing);

        mvc.perform(post("/api/borrowing")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookBorrowing)))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void givenBooks_whenReturnABook_thenReturnJsonArray() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("HP1");
        book.setIsbn("1123-113-44");
        book.setAuthor("JK Rowling");
        book.setStatus(BookStatus.AVAILABLE);

        BookBorrowing bookBorrowing = new BookBorrowing(1L,1L,1L,false);

        List<Book> allBooks = List.of(book);

        given(bookService.getAllBooks()).willReturn(allBooks);
        given(service.findByBookId(1L)).willReturn(bookBorrowing);

        mvc.perform(post("/api/borrowing/return/1")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
                .andExpect(status().isOk());

    }

//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
