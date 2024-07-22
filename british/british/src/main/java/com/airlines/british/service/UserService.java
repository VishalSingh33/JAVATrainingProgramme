package com.airlines.british.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.airlines.british.dto.UserDto;
import com.airlines.british.entites.User;
import com.airlines.british.repository.UserRepository;
import com.airlines.british.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

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
@EnableCaching
public class UserService {

    private final UserRepository userRepository;
    // private final RestClient restClient;

    // @Cacheable(value = "users")
    public Page<User> getUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    // @Cacheable(value = "?", key = "#userId")
    public ResponseEntity<?> getUserById(String userId) {

        doLongRunningTask();

        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        objectMapper.registerModule(javaTimeModule);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        try {
            String userJson = objectMapper.writeValueAsString(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }

        // if (user != null) {
        // // return ResponseEntity.status(HttpStatus.CREATED).body(user);
        // String userJson = objectMapper.writeValueAsString(user);
        // return ResponseEntity.status(HttpStatus.CREATED).body(userJson);
        // } else {
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        // }

    }

    @SuppressWarnings("unused")
    public ResponseEntity<User> createUser(UserDto userDto) {

        User user = new User();
        user.setFullName(userDto.getFullName());
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

    // @CacheEvict(value = "users", key = "#userId")
    public ResponseEntity<User> updateUserController(String userId, UserDto userDto) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        existingUser.setFullName(userDto.getFullName());
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

    @CacheEvict(value = "users", key = "#userId")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // public List<Todo> getSingleTodo() {
    // List<Todo> body = restClient.get()
    // .uri("/todos")
    // .retrieve()
    // .body(new ParameterizedTypeReference<List<Todo>>() {
    // });

    // return body;
    // }

}
