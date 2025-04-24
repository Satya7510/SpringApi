package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void loadUsersFromExternalApi();
    List<User> searchUsers(String searchTerm);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
} 