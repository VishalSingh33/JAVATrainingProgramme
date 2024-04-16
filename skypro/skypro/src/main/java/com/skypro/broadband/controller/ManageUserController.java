package com.skypro.broadband.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.skypro.broadband.dto.UserDto;
import com.skypro.broadband.entities.User;
import com.skypro.broadband.services.ManageUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManageUserController implements UserController {

	@Autowired
	private final ManageUserService userService;

	@Override
	public Page<User> getUsers(int page, int size) {

		return userService.getUsers(page, size);
	}

	@Override
	public ResponseEntity<User> getUserById(String id) {
		
		return userService.getUserById(id);
	}

	@Override
	public ResponseEntity<User> createUser(UserDto userDto) {

		return userService.createUser(userDto);
	}

	@Override
	public ResponseEntity<User> updateUserController(String id, UserDto userDto) {

		return userService.updateUserController(id, userDto);
	}

	@Override
	public void deleteUser(String id) {

		userService.deleteUser(id);
	}

	@Override
	public ResponseEntity<InputStreamResource> downlaodUserPaymentExcel() {

		LocalDateTime localDateTime =  LocalDateTime.now();
		String filename = "user_payment_record_" + localDateTime + ".xlsx";

		InputStreamResource file = new InputStreamResource(userService.loadPaymentExcel());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(file);

	}

	@Override
	public ResponseEntity<List<User>> getCsvFileData(List<String> id, int page, int size) 
	{
		return userService.getCsvFileData(id, page, size);
	}


}