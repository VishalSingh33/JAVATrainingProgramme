package com.paypal.payment.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException()
    {
     super();
    }
    public ResourceNotFoundException(String msg)
    {
     super(msg);
    }

}
