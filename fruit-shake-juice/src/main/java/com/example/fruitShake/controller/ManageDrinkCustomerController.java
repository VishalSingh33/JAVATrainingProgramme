// package com.example.fruitShake.controller;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// import java.util.List;
// import java.util.Optional;
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.example.fruitShake.dto.AddBookRequestDto;
// import com.example.fruitShake.dto.DrinkCusRequestDto;
// import com.example.fruitShake.entities.BookUsers;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.DrinkCustomer;
// import com.example.fruitShake.entities.Users;
// // import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.service.DrinkCustomerService;
//
// @RestController
// @Slf4j
// @RequiredArgsConstructor
// public class ManageDrinkCustomerController implements DrinkCustomerController {
//
// 	private final DrinkCustomerService drinkCustomerService;
//
// 	@Override
// 	public ResponseEntity<DrinkCustomer> getAllDrinkCus(String cName, String cPhoneNo) {
//
// 		return drinkCustomerService.getAllDrinkCus(cName, cPhoneNo);
// 	}
//
// 	@Override
// 	public ResponseEntity<DrinkCustomer> createDrinkCustomer(DrinkCusRequestDto addDrinkCus) {
//
// 		return drinkCustomerService.createDrinkCustomer(addDrinkCus);
// 	}
//
// 	@Override
// 	public ResponseEntity<Optional<DrinkCustomer>> updateDrinkCustomer(String cId, DrinkCusRequestDto updateDrinkCus) {
//
// 		return drinkCustomerService.updateDrinkCustomer(cId, updateDrinkCus);
// 	}
//
// 	@Override
// 	public ResponseEntity<Optional<DrinkCustomer>> deleteDrinkCus(String cId) {
//
// 		return drinkCustomerService.deleteDrinkCus(cId);
// 	}
//
// }
