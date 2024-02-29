package com.example.demo.repositories;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.ExceptionHandling.InvalidOperationException;
import com.example.demo.ExceptionHandling.NoSuchCommandException;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.PlaylistStatus;
import com.example.demo.entities.Songs;

public class PlaylistRepository implements IPlaylistRepository {

    private SongRepository songRepository;
    private final Map<String, Playlist> playlistMap;
    private Integer autoIncrement = 0;
    private Playlist loadedPlaylist;

    public Playlist getLoadedPlaylist() {
        return loadedPlaylist;
    }

    public PlaylistRepository() {
        playlistMap = new HashMap<String, Playlist>();
    }

    public PlaylistRepository(Map<String, Playlist> playlistMap, SongRepository songRepository) {
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
        this.songRepository = songRepository;
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        // DONE Auto-generated method stub
        autoIncrement++;
        String playlistId = Integer.toString(autoIncrement);
        Playlist pList = new Playlist(playlistId, playlist.getPlaylistName(), playlist.getSongs());
        pList.setState(PlaylistStatus.NOT_LOADED);
        playlistMap.put(playlistId, pList);
    }

    @Override
    public Optional<Playlist> getPlaylistByName(String playlistName) {
        // DONE Auto-generated method stub
        for (Map.Entry<String, Playlist> entry : playlistMap.entrySet()) {
            if (entry.getValue().getPlaylistName().equalsIgnoreCase(playlistName))
                return Optional.ofNullable(entry.getValue());
        }
        return Optional.empty();
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        // DONE Auto-generated method stub
        String playlistId = playlist.getId();

        List<Songs> songList = songRepository.getAllSongs();

        if (playlistMap.containsKey(playlistId)) {
            songList.forEach(song -> {
                getPlaylistById(playlistId).addSong(song);
            });
            playlistMap.put(playlistId, playlist);
        }
    }

    @Override
    public List<String> deleteSong(String playlistId, List<Songs> songList) {

        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            songList.forEach(song -> {
                playlist.removeSong(song);
            });
            // Return a list of song IDs or names that were successfully deleted
            return songList.stream().map(Songs::getId).collect(Collectors.toList());
        } else {
            // Case where the playlist is not found
            return Collections.emptyList();
        }
    }

    @Override
    public Playlist getPlaylistById(String playlistId) {

        for (Playlist playlist : playlistMap.values()) {
            if (playlist.getPlaylistName().equals(playlistId)) {
                return playlist;
            }
        }
        throw new NoSuchCommandException("Playlist Not Found");
    }

    private Playlist addPlaylistAndGet(Playlist playlist) {
        autoIncrement++;
        String playlistId = Integer.toString(autoIncrement);
        Playlist pList = new Playlist(playlistId, playlist.getPlaylistName(), playlist.getSongs());
        pList.setState(PlaylistStatus.NOT_LOADED);
        playlistMap.put(playlistId, pList);
        return pList;
    }
    
    @Override
    public boolean savePlaylist(Playlist playlist) {
        Playlist playList = addPlaylistAndGet(playlist);
        if (playlistMap.containsKey(playlist.getId())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deletePlaylist(String playlistName) throws InvalidOperationException {

        Playlist playlist = getPlaylistById(playlistName);
        return playlistMap.remove(playlist.getId(), playlist);
    }

    @Override
    public void load(Playlist playlist) {
        this.loadedPlaylist = playlist;
    }

}
