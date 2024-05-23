package com.example.simplelibrarysystem.service.Impl;

import com.example.simplelibrarysystem.model.Users;
import com.example.simplelibrarysystem.repo.UserRepository;
import com.example.simplelibrarysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Users register(Users user) {
        return userRepository.save(user);
    }
}
