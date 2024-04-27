package com.airlines.british.service;

import java.time.LocalDateTime;
import com.airlines.british.dto.UserDto;
import com.airlines.british.entites.User;
import com.airlines.british.repository.UserRepository;
import com.airlines.british.service.UserService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Page<User> getUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public ResponseEntity<User> getUserById(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @SuppressWarnings("unused")
    public ResponseEntity<User> createUser(UserDto userDto) {
        
        User user = new User();
        user.setUserId(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setGender(userDto.getGender());
        String uniqueId = UUID.randomUUID().toString();
        user.setUserId(uniqueId);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        // log.info("user.getEntityLogo() entitiesList : {}", entitiesList);
        // Save the User entity
        userRepository.save(user);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<User> updateUserController(String userId, UserDto userDto) {
        
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        existingUser.setUserId(userDto.getName());
        existingUser.setUserName(userDto.getUserName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setMobileNumber(userDto.getMobileNumber());
        existingUser.setGender(userDto.getGender());
        existingUser.setUpdatedAt(LocalDateTime.now());
        // Save the updated user entity
        User updatedUser = userRepository.save(existingUser);
        // Return ResponseEntity with the updated user
        return ResponseEntity.ok(updatedUser);
    }

    public void deleteUser(String userId) {

        userRepository.deleteById(userId);
    }
    
}
