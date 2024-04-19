package com.airlines.british.exception;

import org.springframework.stereotype.Component;

// @Component
public class BookingControllerException extends RuntimeException{

	/**
	 * 
	 */
	// private static final long serialVersionUID = 779679767128057150L;

    public BookingControllerException()
		{
		 super();
		}
		public BookingControllerException(String msg)
		{
		 super(msg);
		}
    }
