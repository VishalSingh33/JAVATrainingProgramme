package com.example.demo.commands;

import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Playlist;
import com.example.demo.services.IPlaylistService;
import java.util.ArrayList;
import java.util.List;

public class CreatePlaylistCommand implements ICommand {
    String userId;
    String playlistName;
    List<String> songsId;

    private final IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {

        String playlistName = tokens.get(1);
        List<String> songIdList = new ArrayList<>();
        for (int i = 2; i < tokens.size(); i++) {
            String id = String.valueOf(tokens.get(i));
            songIdList.add(id);
        }
        try {
            Playlist playlist = playlistService.createPlaylist(playlistName, songIdList);
            System.out.println("Playlist [id=" + playlist.getId() + "]");
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage());
        }

    }

}
