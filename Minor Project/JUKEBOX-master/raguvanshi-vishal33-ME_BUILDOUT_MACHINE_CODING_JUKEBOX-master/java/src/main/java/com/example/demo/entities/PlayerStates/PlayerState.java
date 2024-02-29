package com.example.demo.entities.PlayerStates;

import com.example.demo.entities.Songs;
import com.example.demo.services.Player;

public abstract class PlayerState {
// done
    protected Player player;

    PlayerState(Player player){
        this.player = player;
    }

    public abstract void play(Songs id);
    public abstract void next(Songs id);
    public abstract void back(Songs id);
    public abstract void stop(Songs id);

    


    
}
