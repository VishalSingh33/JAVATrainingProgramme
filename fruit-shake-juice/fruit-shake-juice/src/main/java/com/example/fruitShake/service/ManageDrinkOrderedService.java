// package com.example.fruitShake.service;

// import java.time.OffsetDateTime;
// import java.time.ZoneOffset;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.http.HttpStatus;
// import com.example.fruitShake.dto.BookStatus;
// import com.example.fruitShake.entities.DrinkOrdered;
// import com.example.fruitShake.entities.Books;
// import com.example.fruitShake.entities.DrinkBar;
// import com.example.fruitShake.entities.DrinkCustomer;
// import com.example.fruitShake.entities.Users;
// import com.example.fruitShake.repository.BookRepository;
// import com.example.fruitShake.repository.DrinkOrderedRepository;
// import com.example.fruitShake.repository.DrinkBarRepository;
// import com.example.fruitShake.repository.DrinkCustomerRepository;
// import com.example.fruitShake.repository.DrinkOrderedRepository;
// import com.example.fruitShake.repository.UsersRepository;

// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class ManageDrinkOrderedService implements DrinkOrderedService {

//     private final DrinkBarRepository drinkBarRepository;
//     private final DrinkCustomerRepository drinkCustomerRepository;
//     private final DrinkOrderedRepository drinkOrderedRepository;

//     @Override
//     public ResponseEntity<List<DrinkBar>> getAllDrinks() {

//         List<DrinkBar> listUsers = drinkBarRepository.findAll();
//         return ResponseEntity.status(HttpStatus.CREATED).body(listUsers);
//     }

//     @Override
//     public ResponseEntity<List<DrinkCustomer>> getAllDrinkCUstomer() {

//         List<DrinkCustomer> listBooks = drinkCustomerRepository.findAll();
//         return ResponseEntity.status(HttpStatus.CREATED).body(listBooks);
//     }

//     @Override
//     public ResponseEntity<DrinkOrdered> createDrinkOrder(String cId, String dId) {

//         DrinkBar drinkBar = drinkBarRepository.findById(dId)
//                 .orElseThrow(() -> new RuntimeException("Book not found with id: " + dId));

//         DrinkCustomer drinkCustomer = drinkCustomerRepository.findById(dId)
//                 .orElseThrow(() -> new RuntimeException("Book not found with id: " + dId));

//         OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
//         DrinkOrdered drinkOrdered = new DrinkOrdered();
//         // drinkOrdered.setBookId(books);
//         // drinkOrdered.setUserId(users);
//         // drinkOrdered.setIssuedDate(offset);
//         // drinkOrdered.setReturnDate(offset.plusDays(14));
//         // drinkOrdered.setStatus(BookStatus.BOOKED.status());
//         // books.setStatus(BookStatus.BOOKED.status());
//         drinkOrderedRepository.save(drinkOrdered);

//         return ResponseEntity.status(HttpStatus.CREATED).body(drinkOrdered);
//     }

//     @Override
//     public ResponseEntity<DrinkOrdered> updateDrinkOrder(String cId, String dId) {

//         DrinkBar drinkBar = drinkBarRepository.findById(dId)
//                 .orElseThrow(() -> new RuntimeException("Book not found with id: " + dId));

//         DrinkCustomer drinkCustomer = drinkCustomerRepository.findById(dId)
//                 .orElseThrow(() -> new RuntimeException("Book not found with id: " + dId));

//         OffsetDateTime offset = new Date().toInstant().atOffset(ZoneOffset.UTC);
//         DrinkOrdered drinkOrdered = new DrinkOrdered();
//         // drinkOrdered.setReturnDate(offset);
//         // drinkOrdered.setStatus(BookStatus.AVAILABLE.status());
//         // books.setStatus(BookStatus.AVAILABLE.status());
//         drinkOrderedRepository.save(drinkOrdered);
//         return ResponseEntity.status(HttpStatus.CREATED).body(drinkOrdered);
//     }

// }
