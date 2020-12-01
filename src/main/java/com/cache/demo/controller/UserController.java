package com.cache.demo.controller;

import com.cache.demo.model.User;
import com.cache.demo.model.UserModifier;
import com.cache.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Log
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAllUsers();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody UserModifier user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteUser(@PathVariable UUID id) {
        return userService.removeUserById(id);
    }
}
