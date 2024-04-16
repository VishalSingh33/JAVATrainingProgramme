package com.skypro.broadband.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import com.skypro.broadband.dto.UserDto;
import com.skypro.broadband.entities.User;

public interface UserService {

	public Page<User> getUsers(int page, int size);

	public ResponseEntity<User> getUserById(String id);

	public ResponseEntity<User> createUser(UserDto userDto );

	public ResponseEntity<User> updateUserController(String id, UserDto userDto );

    public void deleteUser(String id);

	public InputStream loadPaymentExcel();

	public ResponseEntity<List<User>> getCsvFileData(List<String> id, int page, int size);


}
