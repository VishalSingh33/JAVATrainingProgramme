package com.example.demo.ExceptionHandling;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException()
 {
  super();
 }
 public UserNotFoundException(String msg)
 {
  super(msg);
 }
}
