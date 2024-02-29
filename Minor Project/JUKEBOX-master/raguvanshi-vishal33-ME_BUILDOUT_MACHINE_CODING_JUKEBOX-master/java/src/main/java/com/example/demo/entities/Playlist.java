package com.example.demo.entities;

import java.util.List;

public class Playlist extends BaseEntity {

    private String playlistName;
    private List<Songs> songs;
    private PlaylistStatus state;

    public Playlist(String id, String playlistName, List<Songs> songs) {
        this.id = id;
        // this.users = users;
        this.playlistName = playlistName;
        this.songs = songs;
    }
    public Playlist(String id, String playlistName, List<Songs> songs, PlaylistStatus state) {
        this.id = id;
        // this.users = users;
        this.playlistName = playlistName;
        this.songs = songs;
        this.state = state;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public List<Songs> getSongs() {
        return songs;
    }
    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }
    public void addSong(Songs song){
        this.songs.add(song);
    }
    public boolean removeSong(Songs song){
        return  this.songs.remove(song);
    }
    public void setState(PlaylistStatus state) {
        this.state = state;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playlistName == null) ? 0 : playlistName.hashCode());
        result = prime * result + ((songs == null) ? 0 : songs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (playlistName == null) {
            if (other.playlistName != null)
                return false;
        } else if (!playlistName.equals(other.playlistName))
            return false;
        if (songs == null) {
            if (other.songs != null)
                return false;
        } else if (!songs.equals(other.songs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + ", songs=" + songs + "]";
    }

}

