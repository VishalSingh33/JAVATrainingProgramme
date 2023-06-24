package com.example.fruitShake.mapper;

import java.time.OffsetDateTime;

import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.web.bind.annotation.Mapping;

import com.example.fruitShake.dto.AddUserRequestDto;
import com.example.fruitShake.entities.Books;

import jakarta.persistence.MappedSuperclass;

// import org.mapstruct.Mapper;

/**
 * Products mapper
 * 
 */
// @Mapping
public interface BookMapper {

    // @Mapping(target = "bookId", source = "bookId")
    // @Mapping(target = "createdOn", source = "createdOn")
    // @Mapping(target = "bookName", source = "addBook.bookName")
    // Books setData(Class<UuidGenerator> bookId,  OffsetDateTime createdOn AddBookRequestDto addBook);


//   default String map(CharSequence value) {
//     return value == null ? null : String.valueOf(value);
//   }
}