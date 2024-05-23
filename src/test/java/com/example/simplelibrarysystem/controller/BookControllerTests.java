package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.model.Book;
import com.example.simplelibrarysystem.model.BookStatus;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    @Test
    public void givenBooks_whenGetAllBooks_thenReturnJsonArray() throws Exception {
        Book book = new Book(1L,"ISBN-117-8881-uy78","She","Rider Hagard", BookStatus.AVAILABLE);

        List<Book> allBooks = List.of(book);

        given(service.getAllBooks()).willReturn(allBooks);

        mvc.perform(get("/api/books/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void givenBooks_whenPostABook_thenReturnJsonArray() throws Exception {
        Book book = new Book();
        book.setTitle("HP1");
        book.setIsbn("1123-113-44");
        book.setAuthor("JK Rowling");
        book.setStatus(BookStatus.AVAILABLE);

        given(service.registerBook(book)).willReturn(book);

        mvc.perform(post("/api/books/add")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
