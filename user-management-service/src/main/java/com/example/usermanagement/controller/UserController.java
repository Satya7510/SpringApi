package com.example.usermanagement.controller;

import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management API", description = "APIs for managing and searching users")
public class UserController {

    private final UserService userService;

    @PostMapping("/load")
    @Operation(
        summary = "Load users from external API",
        description = "Loads user data from the external API into the local H2 database"
    )
    @ApiResponse(responseCode = "200", description = "Users loaded successfully")
    @ApiResponse(responseCode = "500", description = "Error loading users from external API")
    public ResponseEntity<Void> loadUsers() {
        log.info("Received request to load users from external API");
        userService.loadUsersFromExternalApi();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(
        summary = "Search users",
        description = "Search users based on firstName, lastName, or SSN using full-text search"
    )
    public ResponseEntity<List<User>> searchUsers(
        @Parameter(description = "Search term to match against firstName, lastName, or SSN")
        @RequestParam String searchTerm
    ) {
        log.info("Searching users with term: {}", searchTerm);
        return ResponseEntity.ok(userService.searchUsers(searchTerm));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Find user by ID",
        description = "Retrieve a user by their ID"
    )
    public ResponseEntity<User> getUserById(
        @Parameter(description = "ID of the user to retrieve")
        @PathVariable Long id
    ) {
        log.info("Fetching user with id: {}", id);
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(
        summary = "Find user by email",
        description = "Retrieve a user by their email address"
    )
    public ResponseEntity<User> getUserByEmail(
        @Parameter(description = "Email of the user to retrieve")
        @PathVariable String email
    ) {
        log.info("Fetching user with email: {}", email);
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(
        summary = "Get all users",
        description = "Retrieve all users from the database"
    )
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users");
        return ResponseEntity.ok(userService.findAll());
    }
} 