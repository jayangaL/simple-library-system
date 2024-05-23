package com.example.simplelibrarysystem.service.Impl;

import com.example.simplelibrarysystem.model.Users;
import com.example.simplelibrarysystem.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    void shouldSaveUsersSuccessfully(){
        Users usr= new Users(1L,"Chamari Athapaththu","chamari.a@gmail.com");

        Users saved = userRepository.save(usr);
        verify(userRepository).save(any(Users.class));

        Users usr2 = userService.register(usr);
        Assertions.assertEquals(usr2,saved);

    }
}
