package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.ActivePlaylist;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.PlaylistStatus;
import com.example.demo.entities.Songs;
import com.example.demo.entities.SwitchSongs;
import com.example.demo.repositories.IPlaylistRepository;
import com.example.demo.repositories.ISongRepository;
import com.example.demo.repositories.IUserRepository;

public class PlayActiveService implements IPlayActiveService {

    private ISongRepository songRepository;
    private IUserRepository userRepository;
    private IPlaylistRepository playlistRepository;
    private Playlist currentPlayList;
    private ActivePlaylist currentActivePlaylist;
    ActivePlaylist activePlaylist;

    public PlayActiveService() {}

    public PlayActiveService(ISongRepository songRepository, IUserRepository userRepository,
            IPlaylistRepository playlistRepository, ActivePlaylist activePlaylist) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.activePlaylist = activePlaylist;
    }

    @Override
    public Playlist getPlaylistByName(String playlistName) {

        Playlist playlist = playlistRepository.getPlaylistByName(playlistName)
        .orElseThrow(() -> new RuntimeException("Playlist with name: \" + playlistName + \" not found!\""));

        if (playlistRepository.getLoadedPlaylist() != null) playlist.setState(PlaylistStatus.NOT_LOADED);
        playlist.setState(PlaylistStatus.LOADED);
        playlistRepository.load(playlist);

        activePlaylist.setPlaylists(playlist);
        activePlaylist.setCurrentSong(0);
        return playlist;
    }

    @Override
    public List<Songs> getSongsByPlaylistId(String playlistId) {
        return activePlaylist.getPlaylists().getSongs();
    }

    @Override
    public Songs getSongBySongId(String songId) {
        return currentActivePlaylist.getSongById(currentPlayList, songId);
    }

    @Override
    public String playPlaylist(String playlistId) {
        // DONE Auto-generated method stub
        Playlist playlist = playlistRepository.getPlaylistById(playlistId);

        if (playlist == null) {
            return "Playlist not found or not owned by the user.";
        }
        List<Songs> songs = playlist.getSongs();

        if (songs.isEmpty()) {
            return "Playlist is empty.";
        }
        // Assuming you have a method to get song details by songId
        String firstSongId = songs.get(0).getId();
        Songs firstSong = songRepository.getSongById(firstSongId).orElseThrow();

        if (firstSong == null) {
            return "Error retrieving song details.";
        }
        return "Current Song Playing\n" + "Song - " + firstSong.getSongName() + "\n" + "Album - "
                + firstSong.getAlbum() + "\n" + "Artists - "
                + String.join(",", firstSong.getGenre());
    }

    @Override
    public Playlist manageSong(String action) {

        Playlist playlist = activePlaylist.getPlaylists();
        int currentPlayingsong = activePlaylist.getCurrentSong();
        List<Songs> songList = playlist.getSongs();
        // Ensure the playlist is not empty
        if (songList.isEmpty()) {
            System.out.println("Playlist is empty!");
            return playlist;
        }
        // Ensure songNavigationIndex is within bounds
        if (currentPlayingsong < 0 || currentPlayingsong >= songList.size()) {
            currentPlayingsong = 0; // reset to 0 if out of bounds
        }
        Songs currentSong = songList.get(currentPlayingsong);

        switch (action) {
            case "PLAY_SONG":
                if (currentSong.getSongStatus() == SwitchSongs.PLAYING) {
                    currentSong.setSongStatus(SwitchSongs.PAUSED);
                    // System.out.println(currentSong + " is paused!");
                    System.out.println("Song [id="+currentSong.getId()+"] is paused!");
                } else {
                    currentSong.setSongStatus(SwitchSongs.PLAYING);
                    // System.out.println(currentSong + " is playing!");
                    System.out.println("Song [id="+currentSong.getId()+"] is playing!");
                }
                break;
            case "NEXT_SONG":
            currentSong.setSongStatus(SwitchSongs.STOP_SONG); // stop the current playing
            currentPlayingsong++;
            activePlaylist.setCurrentSong(currentPlayingsong);
               
                // songNavigationIndex = (songNavigationIndex + 1) % songList.size(); // move to next
                currentSong = songList.get(currentPlayingsong);
                currentSong.setSongStatus(SwitchSongs.PLAYING); // start the next song
                // System.out.println(currentSong + " is playing!");
                System.out.println("Song [id="+currentSong.getId()+"] is playing!");
                break;
            case "PREVIOUS_SONG":
                currentSong.setSongStatus(SwitchSongs.STOP_SONG); // stop the current playing
                // songNavigationIndex = (songNavigationIndex - 1 + songList.size()) % songList.size(); 
                currentPlayingsong--;
                // move to previous song
                activePlaylist.setCurrentSong(currentPlayingsong);
                currentSong = songList.get(currentPlayingsong);
                currentSong.setSongStatus(SwitchSongs.PLAYING); // start the previous song
                System.out.println("Song [id="+currentSong.getId()+"] is playing!");
                break;
            case "STOP_SONG":
                currentSong.setSongStatus(SwitchSongs.STOP_SONG);
                System.out.println("Song [id="+currentSong.getId()+"] is stopped!");
                break;
            default:
                System.out.println("Invalid action!");
                break;
        }
        return playlist;
    }

    @Override
    public Playlist deleteSongsByPlaylistId(String playlistName, String songId) {
        
        Songs song = songRepository.getSongById(songId)
                .orElseThrow(() -> new RuntimeException("Song with id: " + songId + " not found!"));
        Playlist playlist = playlistRepository.getPlaylistByName(playlistName).orElseThrow(
                () -> new RuntimeException("Playlist with name: " + playlistName + " not found!"));
        playlist.removeSong(song);
        
        return playlist;
    }

}
