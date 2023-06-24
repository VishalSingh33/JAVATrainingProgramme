// package com.zyapaar.userservice.component;

// import com.zyapaar.userservice.properties.B2bProperties;
// import com.zyapaar.userservice.request.IdentityValidationDto;

// import org.springframework.stereotype.Component;
// import org.springframework.web.reactive.function.client.WebClient;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class IdentityComponent {

//   private final B2bProperties b2bProperties;
//   private final WebClient.Builder webClientBuilder;

//   public Boolean isIdentityValid(IdentityValidationDto identityValidationDto) {

//     log.info("[isIdentityValid] check is identity number is valid or not");

//     webClientBuilder.build()
//         .post()
//         .uri(b2bProperties.getApi().getIdentityValidation())
//         .bodyValue(identityValidationDto)
//         .retrieve()
//         .toBodilessEntity()
//         .block();
//     return true;
//   }

// }
