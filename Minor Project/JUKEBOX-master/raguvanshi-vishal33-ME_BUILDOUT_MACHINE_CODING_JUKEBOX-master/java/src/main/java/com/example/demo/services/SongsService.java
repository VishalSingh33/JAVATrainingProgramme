package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.entities.Songs;
import com.example.demo.repositories.ISongRepository;

public class SongsService implements ISongsService {

    private final ISongRepository songRepository;

    public SongsService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    } 
    public static List<Songs> songs = new ArrayList<>();

    @Override
    public Songs loadSongFromFile(String songName, String artist, String album, String genre) {
        
        return songRepository.loadSongs(songName, artist, album, genre);
    }

    @Override
    public List<Songs> allSongs() {

        return songRepository.getAllSongs();
    }
}
