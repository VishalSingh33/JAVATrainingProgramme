package com.notification.service.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.notification.service.dto.UserDto;
import com.notification.service.entity.User;
import com.notification.service.repository.NotificationRepository;
import com.notification.service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManageUserService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public Page<User> getUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public ResponseEntity<User> getUserById(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

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
        user.setId(uniqueId);
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
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setSubscriptionPlan(userDto.getSubscriptionPlan());
        user.setNotificationLimit(userDto.getNotificationLimit());
        user.setDeviceType(userDto.getDeviceType());
        return user;
    }

    public ResponseEntity<User> updateUserController(String id, UserDto userDto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Check if the user exists
        if (existingUser == null) {
            // Return 404 Not Found if user not found
            return ResponseEntity.notFound().build();
        }

        // Update the retrieved user entity with information from UserDto
        updateUserFromDto(existingUser, userDto);

        // Save the updated user entity
        User updatedUser = userRepository.save(existingUser);

        // Return ResponseEntity with the updated user
        return ResponseEntity.ok(updatedUser);

    }
    private void updateUserFromDto(User user, UserDto userDto) {
        // Update user properties from UserDto
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setDeviceType(userDto.getDeviceType());
        user.setSubscriptionPlan(userDto.getSubscriptionPlan());
        user.setNotificationLimit(userDto.getNotificationLimit());
    }

    public void deleteUser(String id) {

        userRepository.deleteById(id);

    }

}
