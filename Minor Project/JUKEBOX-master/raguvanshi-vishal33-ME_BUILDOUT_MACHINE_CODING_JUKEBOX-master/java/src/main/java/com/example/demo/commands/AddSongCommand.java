package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Songs;
import com.example.demo.services.ISongsService;

public class AddSongCommand implements ICommand {
    private final ISongsService songService;

    public AddSongCommand(ISongsService songService) {
        this.songService = songService;
    }
    @Override
    public void invoke(List<String> tokens) {

        String songName = tokens.get(1);
        String artist = tokens.get(2);
        String album = tokens.get(3);
        String genre = tokens.get(4);

        if(songName.isEmpty() || artist.isEmpty() || album.isEmpty() || genre.isEmpty() ){
            throw new RuntimeException("Fields are Empty");

        } else {
            Songs song = songService.loadSongFromFile(songName, artist, album, genre);
            System.out.println("Song [id=" + song.getId() + "]");

        }

    }
}
