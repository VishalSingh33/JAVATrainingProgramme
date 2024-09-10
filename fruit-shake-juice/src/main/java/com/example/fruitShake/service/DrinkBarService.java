 package com.example.fruitShake.service;

 import java.util.ArrayList;
 import java.util.List;

 import com.example.fruitShake.dto.DrinkBarResponseDto;
 import org.springframework.http.ResponseEntity;

 import com.example.fruitShake.dto.DrinkBarRequestDto;
 import com.example.fruitShake.entities.DrinkBar;

 public interface DrinkBarService {

     ResponseEntity<List<DrinkBar>> getAllDrinks();
//     String dName, String status
     ResponseEntity<List<DrinkBar>> addDrinks(DrinkBarRequestDto addDrinks);

//     ResponseEntity<Optional<DrinkBar>> updateDrinks(String dID, DrinkBarRequestDto updateDrinks);
//
//     ResponseEntity<Optional<DrinkBar>> deleteDrinks(String dID);

 }
