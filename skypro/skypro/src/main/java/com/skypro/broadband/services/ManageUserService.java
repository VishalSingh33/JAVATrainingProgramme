package com.skypro.broadband.services;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.skypro.broadband.dto.UserDto;
import com.skypro.broadband.entities.User;
import com.skypro.broadband.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ManageUserService implements UserService {

private final UserRepository userRepository;

@Override
public Page<User> getUsers(int page, int size) {

Pageable pageable = PageRequest.of(page, size);
return userRepository.findAll(pageable);

}

@SuppressWarnings("unused")
@Override
public ResponseEntity<User> createUser(UserDto userDto) {

User user = convertToUserEntity(userDto);
String uniqueId = UUID.randomUUID().toString();
user.setId(uniqueId);
user.setCreatedDate(LocalDateTime.now());
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
user.setUserId(userDto.getUserId());
user.setUserType(userDto.getUserType());
user.setMessage(userDto.getMessage());
user.setTopic(userDto.getTopic());
user.setLink(userDto.getLink());
user.setReadFlag(userDto.getReadFlag());
user.setTriggeredBy(userDto.getTriggeredBy());
return user;
}

@Override
public ResponseEntity<User> updateUserController(String id, UserDto userDto)
{

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
user.setUserId(userDto.getUserId());
user.setUserType(userDto.getUserType());
user.setMessage(userDto.getMessage());
user.setTopic(userDto.getTopic());
user.setLink(userDto.getLink());
user.setReadFlag(userDto.getReadFlag());
user.setTriggeredBy(userDto.getTriggeredBy());
}

@Override
public void deleteUser(String id) {

userRepository.deleteById(id);

}

}