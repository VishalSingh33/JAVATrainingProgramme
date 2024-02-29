package com.example.demo.entities;

import java.util.List;
import java.util.Optional;

public class ActivePlaylist {
    private Playlist playlists;
    private int currentSong;

    public ActivePlaylist() { }

    public ActivePlaylist(Playlist playlists, int currentSong) {
        this.playlists = playlists;
        this.currentSong = currentSong;
    }
    public Playlist getPlaylists() {
        return playlists;
    }
    public void setPlaylists(Playlist playlists) {
        this.playlists = playlists;
    }
    public int getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(int currentSong) {
        this.currentSong = currentSong;
    }
    public Songs getCurrentSongs() {
        return this.playlists.getSongs().get(currentSong);
    }
    public boolean isSongsExist(Playlist playlist) {
        return ((playlist.getSongs().size() != 0) ? true : false);
    }
    public Songs getSongById(Playlist playlist, String songId) {
        Optional<Songs> temp = playlist.getSongs().stream()
                .filter(song -> songId.equals(song.getId())).findFirst();
        return temp.get();
    }
    public List<Songs> getSongList(Playlist playlist) {
        return playlist.getSongs();
    }
}
