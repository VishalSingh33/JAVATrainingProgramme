package com.airlines.british.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    // private static final long serialVersionUID = 779679767128057150L;
	
    public ResourceNotFoundException()
    {
     super();
    }
    public ResourceNotFoundException(String msg)
    {
     super(msg);
    }

}
