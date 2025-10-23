package com.geektrust.backend.controller;

public enum CommandKeyword {

    // Register Command Keywords
    // CREATE_GREETING_COMMAND("CREATE_GREETING"),
    ADD_COURSE_OFFERING("ADD-COURSE-OFFERING"),
    REGISTER("REGISTER"),
    ALLOT("ALLOT"),
    CANCEL("CANCEL");

    private final String name;
    private CommandKeyword(String name){
        this.name = name;
    }

    public String getName() {
        return name;
     } 
}
