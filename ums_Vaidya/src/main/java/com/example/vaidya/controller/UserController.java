package com.example.vaidya.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vaidya.entity.User;
import com.example.vaidya.service.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/pagination")
    public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Page<User> userPage = userService.getUsers(page, size);
        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("totalPages", userPage.getTotalPages());
        response.put("currentPage", userPage.getNumber());
        return ResponseEntity.ok(response);
    }

    

    @GetMapping("/filterUsers")
    public List<User> filterUsers(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer roleId) {
        return userService.filterUsers(userId, fullName, userEmail, address, roleId);
    }
}
