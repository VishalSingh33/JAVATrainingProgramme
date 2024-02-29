package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Songs;

public interface ISongsService {

    Songs loadSongFromFile(String songName, String artist, String album, String genre);

    List<Songs> allSongs();

}
