package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String email,
                               @RequestParam String password, @RequestParam String phone,
                               @RequestParam String role) {
        Users existingUser = userService.getUserByEmail(email);
        if (existingUser != null) {
            return "Пользователь с таким email уже существует";
        }

        Role userRole;
        try {
            userRole = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Недопустимая роль. Используйте 'admin' или 'user'";
        }

        userService.registerUser(name, email, password, phone, userRole);
        return "Пользователь зарегистрирован";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam String email, @RequestParam String password) {
        Users user = userService.getUserByEmail(email);
        if (user != null && userService.authenticateUser(email, password) != null) {
            // Вернуть данные пользователя в ответе
            return ResponseEntity.ok(user); // Возвращаем объект пользователя
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный email или пароль");
    }
}
