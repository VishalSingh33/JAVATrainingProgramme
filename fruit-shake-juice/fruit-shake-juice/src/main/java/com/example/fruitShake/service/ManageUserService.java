// package com.example.fruitShake.service;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import com.example.fruitShake.dto.AddUserRequestDto;
// import com.example.fruitShake.entities.Users;
// import com.example.fruitShake.repository.UsersRepository;

// import java.time.OffsetDateTime;
// import java.time.ZoneOffset;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.Optional;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class ManageUserService implements UserService {

// 	private final UsersRepository usersRepository;

// 	@Override
// 	public ResponseEntity<Users> getAllUsers(String userName, String mobileNo) {
		
// 		if (userName != null)
// 			userName = userName.isEmpty() ? null : "%" + userName + "%";
// 		if (mobileNo != null)
// 		mobileNo = mobileNo.isEmpty() ? null : "%" + mobileNo + "%";

// 		Users resBook = usersRepository.findAll(userName, mobileNo);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resBook);
// 	}

// 	public ResponseEntity<Users> addUser(AddUserRequestDto userDetails) {

// 		ArrayList<Users> list = new ArrayList<>();
// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		Users resUsers = new Users();
// 		resUsers.setUserName(userDetails.getUserName());
// 		resUsers.setMobileNo(userDetails.getMobileNo());
// 		resUsers.setAddress(userDetails.getAddress());
// 		resUsers.setCreatedOn(offset);
// 		resUsers.setUpdatedOn(offset);
// 		list.add(resUsers);
// 		usersRepository.save(resUsers);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resUsers);
// 	}

// 	@Override
// 	public ResponseEntity<Optional<Users>> addUser(String userId, AddUserRequestDto updateUser) {

// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		Optional<Users> resUsers = usersRepository.findById(userId);
// 		resUsers.get().setUserName(updateUser.getUserName());
// 		resUsers.get().setAddress(updateUser.getAddress());
// 		resUsers.get().setMobileNo(updateUser.getMobileNo());
// 		resUsers.get().setUpdatedOn(offset);
// 		usersRepository.save(resUsers.get());
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resUsers);
// 	}

// 	@Override
// 	public ResponseEntity<Optional<Users>> deleteBook(String userId) {

// 		usersRepository.deleteById(userId);
// 		return  ResponseEntity.status(HttpStatus.CREATED).body(null);
// 	}

// }
