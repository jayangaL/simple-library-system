package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.model.Users;
import com.example.simplelibrarysystem.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void givenUser_whenPostAUser_thenReturnJsonArray() throws Exception {

        Users users = new Users(1L,"Nayomi Scott","nayomi.sc@gmail.com");

        given(service.register(users)).willReturn(users);

        mvc.perform(post("/api/users/reg")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(users)))
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
