package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entites.TrainObject;

public interface IBogieService {

    public List<TrainObject> printTrainA(String[] input);

    public List<TrainObject> printTrainB(String[] input);
    
   
}
