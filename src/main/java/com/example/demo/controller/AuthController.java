package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Users existingUser = userService.getUserByEmail(email); // Исправлено на вызов существующего метода
        if (existingUser != null) {
            return "Пользователь с таким email уже существует";
        }
        userService.registerUser(name, email, password);
        return "Пользователь зарегистрирован";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        Users user = userService.getUserByEmail(email); // Исправлено на вызов существующего метода
        if (user != null && userService.authenticateUser(email, password) != null) {
            return "Авторизация успешна";
        }
        return "Неверный email или пароль";
    }
}
