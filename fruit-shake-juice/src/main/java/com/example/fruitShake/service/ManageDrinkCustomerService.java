// package com.example.fruitShake.service;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
//
// import com.example.fruitShake.dto.DrinkCusRequestDto;
// import com.example.fruitShake.entities.DrinkCustomer;
// import com.example.fruitShake.repository.DrinkCustomerRepository;
//
// import java.time.OffsetDateTime;
// import java.time.ZoneOffset;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;
//
// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class ManageDrinkCustomerService implements DrinkCustomerService {
//
// 	private final DrinkCustomerRepository drinkCustomerRepository;
//
// 	@Override
// 	public ResponseEntity<DrinkCustomer> getAllDrinkCus(String cName, String cPhoneNo) {
//
// 		if(cName != null) {
//			cName = cName.isEmpty() ? null : "%" + cName + "%";
//		}
// 		if(cPhoneNo != null) {
//			cPhoneNo = cPhoneNo.isEmpty() ? null : "%" + cPhoneNo + "%";
//		}
//
// 		DrinkCustomer resCus = drinkCustomerRepository.findAll( cName, cPhoneNo);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resCus);
// 	}
//
// 	public ResponseEntity<DrinkCustomer> createDrinkCustomer(DrinkCusRequestDto userDetails) {
//
// 		ArrayList<DrinkCustomer> list = new ArrayList<>();
// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		DrinkCustomer resDrinkCustomer = new DrinkCustomer();
// 		resDrinkCustomer.setCName(userDetails.getCName());
// 		resDrinkCustomer.setCPhoneNo(userDetails.getCPhoneNo());
// 		resDrinkCustomer.setCAddress(userDetails.getCAddress());
// 		resDrinkCustomer.setCreatedOn(offset);
// 		resDrinkCustomer.setUpdatedOn(offset);
// 		list.add(resDrinkCustomer);
// 		drinkCustomerRepository.save(resDrinkCustomer);
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resDrinkCustomer);
// 	}
//
// 	@Override
// 	public ResponseEntity<Optional<DrinkCustomer>> updateDrinkCustomer(String cId, DrinkCusRequestDto updateDrinkCus) {
//
// 		OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
// 		Optional<DrinkCustomer> resDrinkCustomer = drinkCustomerRepository.findById(cId);
// 		resDrinkCustomer.get().setCName(updateDrinkCus.getCName());
// 		resDrinkCustomer.get().setCAddress(updateDrinkCus.getCAddress());
// 		resDrinkCustomer.get().setCPhoneNo(updateDrinkCus.getCPhoneNo());
// 		resDrinkCustomer.get().setUpdatedOn(offset);
// 		drinkCustomerRepository.save(resDrinkCustomer.get());
// 		return ResponseEntity.status(HttpStatus.CREATED).body(resDrinkCustomer);
// 	}
//
// 	@Override
// 	public ResponseEntity<Optional<DrinkCustomer>> deleteDrinkCus(String cId) {
//
// 		drinkCustomerRepository.deleteById(cId);
// 		return  ResponseEntity.status(HttpStatus.CREATED).body(null);
// 	}
//
//
// }