package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.ExceptionHandling.InvalidOperationException;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.ActivePlaylist;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;

public interface IPlaylistRepository {

    
    void addPlaylist(Playlist playlist);

    Optional<Playlist> getPlaylistByName(String playlistName);

    void updatePlaylist(Playlist playlist);

    Playlist getPlaylistById(String playlistId);

    List<String> deleteSong(String playlistId, List<Songs> songList);

    boolean savePlaylist(Playlist playlist);

    boolean deletePlaylist(String playlistName) throws InvalidOperationException;

    void load(Playlist playlist);
    
    public Playlist getLoadedPlaylist();

}

