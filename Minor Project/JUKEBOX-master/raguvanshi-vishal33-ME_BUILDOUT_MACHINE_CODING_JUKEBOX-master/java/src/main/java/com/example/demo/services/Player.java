package com.example.demo.services;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.entities.PlayerStates.IdleState;
import com.example.demo.entities.PlayerStates.PlayerState;

public class Player {
    private final PlaylistService playlistService;
    private final PlayActiveService playActiveService;
    private final Deque<Songs> songId;
    private PlayerState currentState;


    public Player(PlaylistService playlistService, PlayActiveService playActiveService) {
        this.playlistService = playlistService;
        this.playActiveService = playActiveService;
        this.songId = new ArrayDeque<>();
        this.currentState = new IdleState(this);
    }

    public void setplayerState(PlayerState playerState){
        currentState = playerState;
    }

    public void LoadPlaylist(String playlistName){
        songId.clear();
        Playlist find = playActiveService.getPlaylistByName(playlistName);
        List<Songs> list = find.getSongs();
        for(int i=0; i < list.size(); i++){
            songId.add(list.get(i));
        }
    }

    public void playSong(){
        // Long id = songId.peekFirst();
        Songs id = songId.peekFirst();
        currentState.play(id);
    }
    public void nextSong(){
        Songs id = songId.pollFirst();
        songId.addLast(id);
        currentState.next(songId.peek());
    }
    public void prevSong(){
        Songs id = songId.pollLast();
        songId.addFirst(id);
        currentState.back(songId.peek());
    }
    public void stopSong(){
        Songs id = songId.peekFirst();
        currentState.stop(id);
    }


    

    
    
}
