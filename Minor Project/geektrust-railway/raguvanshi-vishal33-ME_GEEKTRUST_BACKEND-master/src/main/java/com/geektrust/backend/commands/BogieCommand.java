package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.IBogieService;

public class BogieCommand implements IBogieCommand{


    private final IBogieService bogieService;

    public BogieCommand(IBogieService bogieService) {
        this.bogieService = bogieService;

    }

    @Override
    public List<String> printTrainA(String[] input) {
       
        return printTrainA(input);
    }

    @Override
    public List<String> printTrainB(String[] input) {

        return printTrainA(input);
    }

}
