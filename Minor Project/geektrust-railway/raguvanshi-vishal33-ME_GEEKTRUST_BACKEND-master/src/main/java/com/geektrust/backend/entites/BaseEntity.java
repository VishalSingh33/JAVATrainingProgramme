package com.geektrust.backend.entites;

import java.util.UUID;

public abstract class BaseEntity {
    protected UUID id;

    public UUID getId() {
        return id;
    }
    
}

