//package com.example.fruitShake.config;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.support.WebExchangeBindException;
//
//@ControllerAdvice
//public class ValidationHandler {
//
//    @ExceptionHandler(WebExchangeBindException.class)
//    public ResponseEntity<List<String>> handleException(WebExchangeBindException e) {
//        var errors = e.getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.toList());
//        return ResponseEntity.badRequest().body(errors);
//    }
//
//}