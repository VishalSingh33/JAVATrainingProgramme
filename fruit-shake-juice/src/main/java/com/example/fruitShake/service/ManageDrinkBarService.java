 package com.example.fruitShake.service;

 import com.example.fruitShake.dto.DrinkBarResponseDto;
 import com.example.fruitShake.mapper.DrinkBarMapper;
 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;

 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
 import com.example.fruitShake.dto.DrinkBarRequestDto;
 import com.example.fruitShake.entities.DrinkBar;
 import com.example.fruitShake.repository.DrinkBarRepository;

 import java.time.OffsetDateTime;
 import java.time.ZoneOffset;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;

 @Slf4j
 @Service
 @RequiredArgsConstructor
 public class ManageDrinkBarService implements DrinkBarService {

 	private final DrinkBarRepository drinkBarRepository;
    private final DrinkBarMapper drinkBarMapper;


     @Override
 	public ResponseEntity<List<DrinkBar>> getAllDrinks() {

// 		if (dName != null)
//            dName = dName.isEmpty() ? null : "%" + dName + "%";

        List<DrinkBar> resDrink =  drinkBarRepository.findAll();
 		return ResponseEntity.status(HttpStatus.CREATED).body(resDrink);
 	}

     @Override
 	public ResponseEntity<List<DrinkBar>> addDrinks(DrinkBarRequestDto addDrinks) {

 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);

         ArrayList<DrinkBar> responseList = new ArrayList<>();
//         for(DrinkBarRequestDto listdrink : addDrinks) {
             DrinkBar resDrink = DrinkBarMapper.INSTANCE.mapToDrinkBar(addDrinks, offset);
             responseList.add(resDrink);
//         }
         drinkBarRepository.saveAll(responseList);
 		 return ResponseEntity.status(HttpStatus.CREATED).body(responseList);
 	}
//      drinkBarRepository.save(resDrink);
//      DrinkBarResponseDto responseDto = DrinkBarMapper.INSTANCE.toDrinkBarResponseDto(resDrink);
//      responseList.add(responseDto);

//      DrinkBar resDrink = new DrinkBar();
// 		resDrink.setDType(DrinkType.JUICE.status());
// 		resDrink.setDName(addDrinks.getDName());
// 		resDrink.setDStatus(DrinkStatus.AVAILABLE.status());
// 		resDrink.setCreatedOn(offset);
// 		resDrink.setUpdatedOn(offset);

// 	@Override
// 	public ResponseEntity<Optional<DrinkBar>> updateDrinks(String dName, DrinkBarRequestDto updateDrinkDto) {
//
// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		Optional<DrinkBar> resDrink = drinkBarRepository.findById(dName);
// 		resDrink.get().setDType(DrinkType.JUICE.status());
// 		resDrink.get().setDName(updateDrinkDto.getDName());
// 		resDrink.get().setDStatus(DrinkStatus.AVAILABLE.status());
// 		resDrink.get().setUpdatedOn(offset);
// 		drinkBarRepository.save(resDrink.get());
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resDrink);
// 	}
//
// 	@Override
// 	public ResponseEntity<Optional<DrinkBar>> deleteDrinks(String bookId) {
//
// 		// return drinkBarRepository.inactiveById(bookId);
// 		drinkBarRepository.deleteById(bookId);
// 		return  ResponseEntity.status(HttpStatus.CREATED).body(null);
// 	}

 }
