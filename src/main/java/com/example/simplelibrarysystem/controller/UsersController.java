package com.example.simplelibrarysystem.controller;

import com.example.simplelibrarysystem.model.Users;
import com.example.simplelibrarysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/reg")
    public ResponseEntity<Users> register(
            @RequestBody Users user){
        Users u = userService.register(user);
        return ResponseEntity.ok(u);
    }
}
