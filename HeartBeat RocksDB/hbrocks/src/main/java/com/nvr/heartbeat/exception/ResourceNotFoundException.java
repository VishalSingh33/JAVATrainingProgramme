package com.nvr.heartbeat.exception;

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
