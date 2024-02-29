package com.example.demo.entities.PlayerStates;

import com.example.demo.entities.Songs;
import com.example.demo.services.Player;

public class PauseState extends PlayerState {
//check
    public PauseState(Player player) {
        super(player);
    }

    @Override
    public void play(Songs song) {
        player.setplayerState(new PlayState(player));
        System.out.println("Song [ id="+song.getId()+"] is playing!");
    }

    @Override
    public void next(Songs song) {
        System.out.println("Song [ id="+song.getId()+"] is playing!");
    }

    @Override
    public void back(Songs song) {
        System.out.println("Song [ id="+song.getId()+"] is playing!");
    }

    @Override
    public void stop(Songs song) {
        player.setplayerState(new PlayState(player));
        System.out.println("Song [ id="+song.getId()+"] is playing!");
        
    }

}
