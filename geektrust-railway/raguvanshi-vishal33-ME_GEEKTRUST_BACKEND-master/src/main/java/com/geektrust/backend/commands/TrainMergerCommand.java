package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.TrainMergerService;

public class TrainMergerCommand implements ITrainMergerCommand {

    private final TrainMergerService mergerService;

    public TrainMergerCommand(TrainMergerService mergerService) {
        this.mergerService = mergerService;

    }

    @Override
    public void trainMerge() {
        
    }
    
}
