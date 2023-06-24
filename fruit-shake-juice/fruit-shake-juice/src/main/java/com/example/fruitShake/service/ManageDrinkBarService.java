// package com.example.fruitShake.service;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
// import org.springframework.stereotype.Service;
// import com.example.fruitShake.dto.DrinkBarRequestDto;
// import com.example.fruitShake.dto.DrinkStatus;
// import com.example.fruitShake.dto.DrinkType;
// import com.example.fruitShake.entities.DrinkBar;
// import com.example.fruitShake.entities.DrinkBar;
// import com.example.fruitShake.mapper.BookMapper;
// import com.example.fruitShake.repository.DrinkBarRepository;

// import java.time.OffsetDateTime;
// import java.time.ZoneOffset;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.Optional;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class ManageDrinkBarService implements DrinkBarService {

// 	private final DrinkBarRepository drinkBarRepository;
// 	// private final DrinkBarMapper drinkBarMapper;

// 	@Override
// 	public ResponseEntity<DrinkBar> getAllDrinks(String dName, String status) {

// 		if (dName != null)
// 			dName = dName.isEmpty() ? null : "%" + dName + "%";

// 			DrinkBar resDrink = drinkBarRepository.findAll(dName, status);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resDrink);
// 	}

// 	@Override
// 	public ResponseEntity<DrinkBar> addDrinks(DrinkBarRequestDto addDrinks) {

// 		// Class<UuidGenerator> dName = UuidGenerator.class;
// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		// DrinkBar resDrinks = bookMapper.setData(dName, offset, addBook);
// 		ArrayList<DrinkBar> list = new ArrayList<>();
// 		DrinkBar resDrink = new DrinkBar();
// 		resDrink.setDType(DrinkType.JUICE.status());
// 		resDrink.setDName(addDrinks.getDName());
// 		resDrink.setDStatus(DrinkStatus.AVAILABLE.status());
// 		resDrink.setCreatedOn(offset);
// 		resDrink.setUpdatedOn(offset);
// 		// resDrinks.setdName(addBook.getdName());
// 		list.add(resDrink);
// 		drinkBarRepository.save(resDrink);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resDrink);
// 	}

// 	@Override
// 	public ResponseEntity<Optional<DrinkBar>> updateDrinks(String dName, DrinkBarRequestDto updateDrinkDto) {

// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		Optional<DrinkBar> resDrink = drinkBarRepository.findById(dName);
// 		resDrink.get().setDType(DrinkType.JUICE.status());
// 		resDrink.get().setDName(updateDrinkDto.getDName());
// 		resDrink.get().setDStatus(DrinkStatus.AVAILABLE.status());
// 		resDrink.get().setUpdatedOn(offset);
// 		drinkBarRepository.save(resDrink.get());
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resDrink);
// 	}

// 	@Override
// 	public ResponseEntity<Optional<DrinkBar>> deleteDrinks(String bookId) {

// 		// return drinkBarRepository.inactiveById(bookId);
// 		drinkBarRepository.deleteById(bookId);
// 		return  ResponseEntity.status(HttpStatus.CREATED).body(null);
// 	}

// }
