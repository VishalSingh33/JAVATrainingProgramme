 package com.example.fruitShake.controller;

 import com.example.fruitShake.dto.DrinkBarResponseDto;
 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;

 import java.util.List;
 import java.util.Optional;

 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.RestController;

 import com.example.fruitShake.dto.AddBookRequestDto;
 import com.example.fruitShake.dto.DrinkBarRequestDto;
 import com.example.fruitShake.entities.DrinkBar;
 import com.example.fruitShake.service.DrinkBarService;

 @RestController
 @Slf4j
 @RequiredArgsConstructor
 public class ManageDrinkBarController implements DrinkBarController {

 	private final DrinkBarService drinkBarService;

 	@Override
 	public ResponseEntity<List<DrinkBar>> getAllDrinks() {

 		return drinkBarService.getAllDrinks();
 	}


 	@Override
 	public ResponseEntity<List<DrinkBar>> addDrinks(DrinkBarRequestDto addDrinks) {

 		return drinkBarService.addDrinks(addDrinks);
 	}

// 	@Override
// 	public ResponseEntity<Optional<DrinkBar>> updateDrinks(String dID, DrinkBarRequestDto updateDrinks) {
//
// 		return drinkBarService.updateDrinks(dID, updateDrinks);
// 	}
//
//
// 	@Override
// 	public ResponseEntity<Optional<DrinkBar>> deleteDrinks(String dID) {
//
// 		return drinkBarService.deleteDrinks(dID);
// 	}


 }
