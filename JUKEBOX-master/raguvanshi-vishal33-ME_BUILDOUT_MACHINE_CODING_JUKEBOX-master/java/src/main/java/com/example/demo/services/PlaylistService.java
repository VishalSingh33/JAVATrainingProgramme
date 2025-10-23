package com.example.demo.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.example.demo.ExceptionHandling.InvalidOperationException;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.repositories.IPlaylistRepository;
import com.example.demo.repositories.ISongRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.repositories.PlaylistRepository;
import com.example.demo.repositories.SongRepository;
import com.example.demo.repositories.UserRepository;

public class PlaylistService implements IPlaylistService{

    private ISongRepository songRepository;
    private IUserRepository userRepository;
    private IPlaylistRepository playlistRepository;
    private Integer autoIncrement = 0;
    private static int counter = 0;

    public PlaylistService() {
        userRepository = new UserRepository();
        songRepository = new SongRepository();
        playlistRepository = new PlaylistRepository();
    }
    public PlaylistService(ISongRepository songRepository, IUserRepository userRepository, 
              IPlaylistRepository playlistRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }


   
    @Override
    public Playlist createPlaylist(String playlistName, List<String> songIdList) {
       
        String playlistId = String.valueOf(++counter);
        List<Songs> songList = getListOfSongsfromIdList(songIdList);
        Playlist playlist = new Playlist(playlistId, playlistName, songList);
        playlistRepository.savePlaylist(playlist);
        return playlist;
    }
    private List<Songs> getListOfSongsfromIdList(List<String> songIds) {
        List<Songs> list = new ArrayList<>();
        for (String songId : songIds) {
            Songs song = songRepository.getSongById(songId)
                    .orElseThrow(() -> new RuntimeException("Song with id: " + songId + " not found!"));
            list.add(song);
        }
        return list;
    }

    @Override
    public boolean deletePlaylist(String playlistName) throws InvalidOperationException  {
        // DONE Auto-generated method stub
        return playlistRepository.deletePlaylist(playlistName);
    }

    @Override
    public List<Songs> getSongsByListOfSongids(List<String> songIds) throws NoSuchCommandException {
        List<Songs> songs = new LinkedList<>();
        for (String s : songIds) {
            try {
                Songs newSong = songRepository.getSongById(s).orElseThrow();
                if (!songs.contains(newSong))
                    songs.add(newSong);
            } catch (NoSuchCommandException e) {
                throw new NoSuchCommandException("Some Requested Songs Not Available. Please try again.");
            }
        }
        return songs;
    }

    @Override
    public Playlist modifyPlaylist(String action,String playlistId, List<String> songIds) {
        // DONE Auto-generated method stub
        Playlist playlist = playlistRepository.getPlaylistById(playlistId);
        if (playlist != null ) {
            List<Songs> songsInPlaylist = playlist.getSongs();
            if (action.equals("ADD_SONG_TO_PLAYLIST")) {
                // Add songs to the playlist
                for (String songId : songIds) {
                    Songs songToAdd = songRepository.getSongById(songId).orElseThrow();
                    if (songToAdd != null && !songsInPlaylist.contains(songToAdd)) {
                        songsInPlaylist.add(songToAdd);
                    }
                }
                return playlist;
            } else if (action.equals("DELETE_SONG_FROM_PLAYLIST")) {
                // Delete songs from the playlist
                List<Songs> songsToRemove = new ArrayList<>();
                for (String songId : songIds) {
                    Songs songToRemove = songRepository.getSongById(songId).orElseThrow();
                    if (songToRemove != null && songsInPlaylist.contains(songToRemove)) {
                        songsToRemove.add(songToRemove);
                    }
                }
                songsInPlaylist.removeAll(songsToRemove);
                return playlist;
            }
        }
        return playlist;
    }
    
}
