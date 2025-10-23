package com.example.demo.commands;

import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Playlist;
import com.example.demo.services.IPlayActiveService;
import java.util.List;

public class LoadPlaylistCommand implements ICommand {
    String userId;
    String playlistName;
    List<String> songsId;

    private final IPlayActiveService playActiveService;

    public LoadPlaylistCommand(IPlayActiveService playActiveService) {
        this.playActiveService = playActiveService;
    }
    @Override
    public void invoke(List<String> tokens) {

        String playlistName = tokens.get(1);
        try {
            Playlist playlist = playActiveService.getPlaylistByName(playlistName);
            System.out.println("Playlist "+ playlist.getPlaylistName() + " is loaded!");
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        }
    }

}
