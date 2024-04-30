package com.airlines.british.exception;

public class BookingException extends RuntimeException{


    public BookingException()
		{
		 super();
		}
		public BookingException(String msg)
		{
		 super(msg);
		}
    }
