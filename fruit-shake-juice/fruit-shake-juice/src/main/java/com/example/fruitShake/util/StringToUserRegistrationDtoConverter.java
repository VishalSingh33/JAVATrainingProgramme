// package com.zyapaar.userservice.util;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.zyapaar.exceptionhandler.custom.BadRequestException;
// import com.zyapaar.userservice.dto.UserRegistrationDto;
// import org.springframework.core.convert.converter.Converter;
// import org.springframework.stereotype.Component;

// import lombok.RequiredArgsConstructor;
// import lombok.SneakyThrows;
// import lombok.extern.slf4j.Slf4j;

// /**
//  * Json String to UserCompanyRegistrationDto Converter
//  * 
//  * @author Uday Halpara
//  */
// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class StringToUserRegistrationDtoConverter implements Converter<String, UserRegistrationDto>{

//   private final ObjectMapper objectMapper;

//   @Override
//   @SneakyThrows
//   public UserRegistrationDto convert(String source) {
//     log.info("[convert] string to UserRegistrationDto");
//     try{
//       return objectMapper.readValue(source, UserRegistrationDto.class);
//     } catch (JsonProcessingException e) {
//       log.info("[convert] Malformed JSON exception");
//       throw new BadRequestException("Malformed JSON", e);
//     }
//   }

// }
