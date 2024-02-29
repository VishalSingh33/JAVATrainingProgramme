package com.geektrust.backend.commands;

import java.util.List;

public interface IBogieCommand {

    public List<String> printTrainA(String[] input);

    public List<String> printTrainB(String[] input);
}
