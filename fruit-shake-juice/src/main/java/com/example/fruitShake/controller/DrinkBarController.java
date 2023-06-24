// package com.example.fruitShake.controller;

// import java.util.Optional;

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

// import com.example.fruitShake.dto.AddBookRequestDto;
// import com.example.fruitShake.dto.DrinkBarRequestDto;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.DrinkBar;

// @Validated
// @CrossOrigin("*")
// @RequestMapping("/api/v1")
// public interface DrinkBarController {

// 	@GetMapping(value = "/drinkBar")
// 	public ResponseEntity<DrinkBar> getAllDrinks(@RequestParam(required = false) String dName,
// 			@RequestParam(required = false) String status);

// 	@PostMapping(value = "/createDrinks")
// 	public ResponseEntity<DrinkBar> addDrinks(@RequestBody DrinkBarRequestDto addDrinks);

// 	@PutMapping(value = "/updateDrinks")
// 	public ResponseEntity<Optional<DrinkBar>> updateDrinks(@RequestHeader("Z-DRINKID") String dId,
// 			@RequestBody DrinkBarRequestDto updateDrinks);

// 	@DeleteMapping(value = "/deleteDrinks")
// 	public ResponseEntity<Optional<DrinkBar>> deleteDrinks(@RequestHeader("Z-DRINKID") String dId);

// }
