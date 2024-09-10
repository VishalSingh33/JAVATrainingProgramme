package com.order.invoice.exception;

public class InvoiceException extends RuntimeException{


    public InvoiceException()
		{
		 super();
		}
		public InvoiceException(String msg)
		{
		 super(msg);
		}
    }
