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
// import com.example.fruitShake.entities.BookUsers;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.DrinkBar;
// import com.example.fruitShake.entities.DrinkCustomer;
// import com.example.fruitShake.entities.DrinkOrdered;
// import com.example.fruitShake.entities.Users;
// // import com.example.fruitShake.entities.Books;
//// import com.example.fruitShake.service.DrinkBarService;
// import com.example.fruitShake.service.DrinkCustomerService;
// import com.example.fruitShake.service.DrinkOrderedService;
//
// @RestController
// @Slf4j
// @RequiredArgsConstructor
// public class ManageDrinkOrderedController implements DrinkOrderedController {
//
//// 	private final DrinkBarService drinkBarService;
// 	private final DrinkCustomerService drinkCustomerService;
// 	private final DrinkOrderedService drinkOrderedService;
//
// 	@Override
// 	public ResponseEntity<List<DrinkBar>> getAllDrinks() {
//
// 		return drinkOrderedService.getAllDrinks();
// 	}
//
// 	@Override
// 	public ResponseEntity<List<DrinkCustomer>> getAllDrinkCUstomer() {
//
// 		return drinkOrderedService.getAllDrinkCUstomer();
// 	}
//
// 	@Override
// 	public ResponseEntity<DrinkOrdered> createDrinkOrder(String cId, String dIdList) {
//
// 		return drinkOrderedService.createDrinkOrder(cId, dIdList);
// 	}
//
// 	@Override
// 	public ResponseEntity<DrinkOrdered> updateDrinkOrder(String cId, String dIdList) {
//
// 		return drinkOrderedService.updateDrinkOrder(cId,  dIdList);
// 	}
//
// }
