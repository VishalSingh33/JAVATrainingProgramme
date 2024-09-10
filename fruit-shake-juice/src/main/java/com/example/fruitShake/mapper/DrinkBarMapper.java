package com.example.fruitShake.mapper;

import java.time.OffsetDateTime;

import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.web.bind.annotation.Mapping;

import com.example.fruitShake.dto.AddUserRequestDto;
import com.example.fruitShake.entities.Books;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Products mapper
 * 
 */
 @Mapper
public interface DrinkBarMapper  {

    // @Mapping(target = "bookId", source = "bookId")
    // @Mapping(target = "createdOn", source = "createdOn")
    // @Mapping(target = "bookName", source = "addBook.bookName")
    // Books setData(Class<UuidGenerator> bookId,  OffsetDateTime createdOn AddBookRequestDto addBook);


//   default String map(CharSequence value) {
//     return value == null ? null : String.valueOf(value);
//   }
}