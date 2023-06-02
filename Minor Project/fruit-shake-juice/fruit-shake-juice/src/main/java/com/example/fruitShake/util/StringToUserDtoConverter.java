// package com.example.fruitShake.util;

// import com.example.fruitShake.dto.AllRequestDto;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.core.convert.converter.Converter;
// import org.springframework.stereotype.Component;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// /**
//  * Json String to UserCompanyRegistrationDto Converter
//  * 
//  */
// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class StringToUserDtoConverter implements Converter<String, AllRequestDto> {

//   private final ObjectMapper objectMapper;

//   @Override
//   public AllRequestDto convert(String source) {
//     log.info("[convert] string to UserRegistrationDto");
//     try {
//       return objectMapper.readValue(source, AllRequestDto.class);
//     } catch (JsonProcessingException e) {
//       log.info("[convert] Malformed JSON exception");
//       throw new BadRequestException("Malformed JSON", e);
//     }
//   }

// }
