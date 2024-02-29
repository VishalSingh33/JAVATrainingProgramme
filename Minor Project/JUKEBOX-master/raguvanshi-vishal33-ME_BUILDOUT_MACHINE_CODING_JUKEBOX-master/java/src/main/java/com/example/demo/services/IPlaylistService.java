package com.example.demo.services;

import java.util.List;
import com.example.demo.ExceptionHandling.InvalidOperationException;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;

public interface IPlaylistService {
    
    Playlist createPlaylist(String playlistName, List<String> songIdList);

    boolean deletePlaylist(String playlistName) throws InvalidOperationException;

    List<Songs> getSongsByListOfSongids(List<String> songIds) throws NoSuchCommandException;

    Playlist modifyPlaylist(String action, String playlistName, List<String> songIds); 
   
}
