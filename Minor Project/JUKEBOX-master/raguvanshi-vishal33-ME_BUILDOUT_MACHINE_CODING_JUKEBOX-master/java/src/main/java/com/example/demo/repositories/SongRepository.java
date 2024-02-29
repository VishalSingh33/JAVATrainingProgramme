package com.example.demo.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entities.Songs;

public class SongRepository implements ISongRepository {

    private final Map<String, Songs> songsMap;
    private Integer autoIncrement = 0;

    public SongRepository() {
        songsMap = new HashMap<String, Songs>();
    }
    public SongRepository(Map<String, Songs> songsMap) {
        this.songsMap = songsMap;
        this.autoIncrement = songsMap.size();
    }

    @Override
    public Optional<Songs> getSong(String songName) {

        Collection<Songs> userCollection = songsMap.values();
        for (Songs song : userCollection) {
            if (song.getSongName().equals(songName)) {
                return Optional.of(song);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Songs> getSongById(String songId) {

        Collection<Songs> userCollection = songsMap.values();
        for (Songs song : userCollection) {
            if (song.getId().equals(songId)) {
                return Optional.of(song);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Songs> getAllSongs() {
        return songsMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean saveSong(Songs song) {

            if (getSongById(song.getId()).isPresent()) {
            songsMap.put(song.getId(), song);
            return true;
        } else
            return false;
    }

    @Override
    public Songs loadSongs(String songName, String artist, String album, String genre) {
      
        String id = String.valueOf(++autoIncrement);
        Songs song = new Songs(id, songName, artist, album, genre);
        saveSong(song);
        songsMap.put(id, song);
        return song;
    }

}
