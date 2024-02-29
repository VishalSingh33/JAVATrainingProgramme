package com.example.demo.commands;


import java.util.List;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand {
    private final IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @Override
    public void invoke(List<String> tokens) {

        String playlistName = tokens.get(1);
        try {
            if (playlistService.deletePlaylist(playlistName));
            System.out.println("Playlist "+playlistName+ " is deleted!");
        } catch (NoSuchCommandException e) {
            System.out.print(e.getMessage());
        }

    }
}
