package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;

public interface IPlayActiveService  {

    Playlist getPlaylistByName(String playlistName);

    List<Songs> getSongsByPlaylistId(String playlistId);
    
    Songs getSongBySongId(String songId);

    String playPlaylist(String playlistId);

    Playlist manageSong(String switchSong);

    Playlist deleteSongsByPlaylistId(String playlistName, String songId);

}
