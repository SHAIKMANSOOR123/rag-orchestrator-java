package com.mansoor.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController: Essential annotation to mark this class as a RESTful web service component.
@RestController 
// @RequestMapping: Sets the base URL for all endpoints in this controller to /api/users.
@RequestMapping("/api/users") 
public class UserController {

    // @Autowired: Spring automatically injects an instance of the UserRepository (Dependency Injection).
    @Autowired
    private UserRepository userRepository;

    // --- C: Create (POST /api/users) ---
    // Handles creating a new user from the JSON body of the request.
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user); // Calls the inherited save method.
    }

    // --- R: Read All (GET /api/users) ---
    // Handles fetching all users from the database.
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Calls the inherited findAll method.
    }

    // --- R: Read By ID (GET /api/users/{id}) ---
    // Handles fetching a specific user by their ID.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id); // Returns Optional to handle nulls.

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // Returns 200 OK with the user data.
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 Not Found.
        }
    }
    
    // --- U: Update (PUT /api/users/{id}) ---
    // Handles updating an existing user's details.
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 1. Update the existing entity's fields
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            
            // 2. Save the updated entity
            final User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- D: Delete (DELETE /api/users/{id}) ---
    // Handles deleting a user by their ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.noContent().build(); // Returns 204 No Content for a successful deletion.
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}