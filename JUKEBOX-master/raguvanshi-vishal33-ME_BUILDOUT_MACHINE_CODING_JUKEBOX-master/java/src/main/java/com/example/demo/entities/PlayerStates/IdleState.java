package com.example.demo.entities.PlayerStates;

import com.example.demo.entities.Songs;
import com.example.demo.services.Player;

public class IdleState extends PlayerState {
// done
    public IdleState(Player player){
        super(player);
    }

    @Override
    public void play(Songs song) {

        player.setplayerState(new PlayState(player));
        System.out.println("Song [ id="+song.getId()+"] is playing!");
    }

    @Override
    public void next(Songs song) {
     return;
    }

    @Override
    public void back(Songs song) {
        return;
        
    }

    @Override
    public void stop(Songs song) {
        return;
    }

}
