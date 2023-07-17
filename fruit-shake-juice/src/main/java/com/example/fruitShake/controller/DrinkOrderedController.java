// package com.example.fruitShake.controller;
//
// import java.util.List;
// import java.util.Optional;
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
//
// import com.example.fruitShake.dto.AddBookRequestDto;
// import com.example.fruitShake.entities.BookUsers;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.DrinkBar;
// import com.example.fruitShake.entities.DrinkCustomer;
// import com.example.fruitShake.entities.DrinkOrdered;
// // import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.Users;
//
// @Validated
// @CrossOrigin("*")
// @RequestMapping("/api/v1")
// public interface DrinkOrderedController {
//
// 	@GetMapping(value = "/getAllDrinks")
// 	public ResponseEntity<List<DrinkBar>> getAllDrinks();
//
// 	@GetMapping(value = "/getAllDrinkCUstomer")
// 	public ResponseEntity<List<DrinkCustomer>> getAllDrinkCUstomer();
//
// 	@PostMapping(value = "/order/{cId}/{dId}")
// 	public ResponseEntity<DrinkOrdered> createDrinkOrder(String cId, String dIdList);
//
// 	@PutMapping(value = "/updateOrder/{cId}/{dId}")
// 	public ResponseEntity<DrinkOrdered> updateDrinkOrder(String cId, String dIdList);
//
// }
