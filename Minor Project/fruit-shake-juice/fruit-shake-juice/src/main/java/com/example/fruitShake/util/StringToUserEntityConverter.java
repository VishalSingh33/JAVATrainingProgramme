// package com.zyapaar.userservice.util;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.zyapaar.exceptionhandler.custom.BadRequestException;
// import com.zyapaar.userservice.dto.UserCompanyRegistrationDto;
// import com.zyapaar.userservice.entities.UserEntity;
// import org.springframework.core.convert.converter.Converter;
// import org.springframework.stereotype.Component;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// /**
//  * Json String to UserCompanyRegistrationDto converter
//  * 
//  * @author Uday Halpara
//  */
// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class StringToUserEntityConverter 
//     implements Converter<String, UserEntity> {

//   private final ObjectMapper objectMapper;

//   @Override
//   public UserEntity convert(String source) throws BadRequestException {
//     log.info("[convert] string to UserCompanyRegistrationDto");
//     try {
//       return objectMapper.readValue(source, UserEntity.class);
//     } catch (JsonProcessingException e) {
//       log.info("[convert] Malformed JSON exception");
//       throw new BadRequestException("Malformed JSON", e);
//     }
//   }
// }
