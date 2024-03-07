package com.geektrust.backend.controller;

public enum IndexCommand {

    ONE_INDEX(1),
    TWO_INDEX(2),
    COURSE_NAME_INDEX(1),
    INSTRUCTOR_INDEX(2),
    DATE_INDEX(3),
    MIN_CAPACITY_INDEX(4),
    MAX_CAPACITY_INDEX(5);

    private final int index;

    IndexCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
