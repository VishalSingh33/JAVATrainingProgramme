package com.example.demo.repositories;

import java.io.File;
import java.util.List;
import java.util.Optional;
import javax.management.openmbean.InvalidOpenTypeException;
import com.example.demo.ExceptionHandling.InvalidOperationException;
import com.example.demo.entities.Songs;

public interface ISongRepository {
    
    Optional<Songs> getSong(String songName);

    Optional<Songs> getSongById(String songId);

    List<Songs> getAllSongs();

    Songs loadSongs(String songName, String artist, String album, String genre);

    boolean saveSong(Songs song);
}

