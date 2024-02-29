package com.example.demo.commands;


import java.util.List;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.services.IPlayActiveService;
import com.example.demo.services.PlaylistService;

public class PlaySongCommand implements ICommand {

    private final IPlayActiveService playActiveService;

    public PlaySongCommand(IPlayActiveService playActiveService) {
        this.playActiveService = playActiveService;
    }

    @Override
    public void invoke(List<String> tokens) {

        String switchSong = tokens.get(0);
    
        try {
            Playlist playResponse = playActiveService.manageSong(switchSong);
        } catch (NoSuchCommandException e) {
            System.out.print(e.getMessage());
        }

    }
}
