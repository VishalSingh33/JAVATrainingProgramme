package com.example.demo.commands;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.ExceptionHandling.InvalidOperationException;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.services.IPlayActiveService;
import com.example.demo.services.IPlaylistService;

public class DeleteSongFromPlaylistCommand implements ICommand {

    private final IPlayActiveService playActiveService;

    public DeleteSongFromPlaylistCommand(IPlayActiveService playActiveService) {
        this.playActiveService = playActiveService;
    }
    @Override
    public void invoke(List<String> tokens) {

        String playlistName = tokens.get(1);
        String songId = tokens.get(2);
        try {
            Playlist playResponse = playActiveService.deleteSongsByPlaylistId(playlistName, songId);
            System.out.print("Playlist " + playResponse.getPlaylistName() + " is revised with [");
            List<Songs> songs = playResponse.getSongs(); // Assuming you have a method to get the
            for (int i = 0; i < songs.size(); i++) {
                Songs song = songs.get(i);
                System.out.print("Song [id=" + song.getId() + "]");
                if (i < songs.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        } catch (NoSuchCommandException e) {
            System.out.print(e.getMessage());
        } catch (InvalidOperationException e) {
            System.out.print(e.getMessage());
        }

    }
}
