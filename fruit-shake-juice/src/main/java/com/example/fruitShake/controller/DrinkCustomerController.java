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
// import com.example.fruitShake.dto.DrinkCusRequestDto;
// import com.example.fruitShake.entities.BookUsers;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.DrinkBar;
// import com.example.fruitShake.entities.DrinkCustomer;
// // import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.Users;
//
// @Validated
// @CrossOrigin("*")
// @RequestMapping("/api/v1")
// public interface DrinkCustomerController {
//
// 	@GetMapping(value = "/getAllDrinkCus")
// 	public ResponseEntity<DrinkCustomer> getAllDrinkCus(@RequestParam(required = false) String cName,
// 	@RequestParam(required = false) String cPhoneNo);
//
// 	@PostMapping(value = "/create")
// 	public ResponseEntity<DrinkCustomer> createDrinkCustomer(@RequestBody DrinkCusRequestDto addDrinkCus);
//
// 	@PutMapping(value = "/update/{dId}/{cId}")
// 	public ResponseEntity<Optional<DrinkCustomer>> updateDrinkCustomer(@RequestHeader("Z-DCUSID") String cId,
// 	@RequestBody DrinkCusRequestDto updateDrinkCus);
//
// 	@DeleteMapping(value = "/deleteDrinkCus")
// 	public ResponseEntity<Optional<DrinkCustomer>> deleteDrinkCus(@RequestHeader("Z-DCUSID") String cId);
//
// }
