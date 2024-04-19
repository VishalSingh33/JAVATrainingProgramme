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
        
        User user = convertToUserEntity(userDto);
        String uniqueId = UUID.randomUUID().toString();
        user.setUserId(uniqueId);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        // log.info("user.getEntityLogo() entitiesList : {}", entitiesList);
        // Save the User entity
        User savedUser = userRepository.save(user);

        if (savedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private User convertToUserEntity(UserDto userDto) {
        
        User user = new User();
        // Map fields from UserDto to User entity
        user.setUserId(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setGender(userDto.getGender());
        return user;
    }

    public ResponseEntity<User> updateUserController(String userId, UserDto userDto) {
        
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Check if the user exists
        if (existingUser == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }
        // Update the retrieved user entity with information from UserDto
        updateUserFromDto(existingUser, userDto);
        existingUser.setUpdatedAt(LocalDateTime.now());
        // Save the updated user entity
        User updatedUser = userRepository.save(existingUser);
        // Return ResponseEntity with the updated user
        return ResponseEntity.ok(updatedUser);

    }
    private void updateUserFromDto(User user, UserDto userDto) {
        // Update user properties from UserDto
        user.setUserId(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setGender(userDto.getGender());
    }

    public void deleteUser(String userId) {

        userRepository.deleteById(userId);
    }

}
