package com.paypal.payment.exception;

public class PaymentException extends RuntimeException{


    public PaymentException()
		{
		 super();
		}
		public PaymentException(String msg)
		{
		 super(msg);
		}
    }
