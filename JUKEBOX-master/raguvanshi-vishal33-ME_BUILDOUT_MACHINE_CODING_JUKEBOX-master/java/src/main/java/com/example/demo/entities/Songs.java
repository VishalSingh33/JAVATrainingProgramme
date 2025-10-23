package com.example.demo.entities;

import java.util.List;

public class Songs extends BaseEntity{
    private String songName;
    private String album;
    private String albumArtist ;
    private String genre ;
    private List<String> artists;
    private SwitchSongs songStatus;

    public Songs(String id, String songName, String albumArtist, String album, String genre) {
        this.id = id;
        this.songName = songName;
        this.album = album;
        this.albumArtist = albumArtist;
        this.genre = genre;
        this.songStatus = SwitchSongs.NOT_PLAYING;
    }
    public String getId() {
        return id;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbum() {
        return album;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getGenre() {
        return genre;
    }
    public SwitchSongs getSongStatus() {
        return songStatus;
    }
    public void setSongStatus(SwitchSongs songStatus) {
        this.songStatus = songStatus;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((album == null) ? 0 : album.hashCode());
        result = prime * result + ((albumArtist == null) ? 0 : albumArtist.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + ((songName == null) ? 0 : songName.hashCode());
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
        Songs other = (Songs) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (album == null) {
            if (other.album != null)
                return false;
        } else if (!album.equals(other.album))
            return false;
        if (albumArtist == null) {
            if (other.albumArtist != null)
                return false;
        } else if (!albumArtist.equals(other.albumArtist))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (songName == null) {
            if (other.songName != null)
                return false;
        } else if (!songName.equals(other.songName))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Songs [album=" + album + ", albumArtist=" + albumArtist + ", artists=" + artists
                + ", genre=" + genre + ", songName=" + songName + ", id='" + id + "]";
    }

   

}

