package com.geektrust.backend.controller;
import java.util.List;

public interface ICommand {
    void execute(List<String> tokens);
}