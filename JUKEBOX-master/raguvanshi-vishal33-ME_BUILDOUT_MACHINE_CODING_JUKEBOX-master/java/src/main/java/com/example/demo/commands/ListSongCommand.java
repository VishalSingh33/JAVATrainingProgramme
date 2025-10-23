package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Songs;
import com.example.demo.services.ISongsService;

public class ListSongCommand implements ICommand {
    private final ISongsService songService;

    public ListSongCommand(ISongsService songService) {
        this.songService = songService;
    }
    @Override
    public void invoke(List<String> tokens) {

        List<Songs> out = songService.allSongs();
        System.out.print("[");
        for (Songs song : out) {
            System.out.print("Song [id=" + song.getId() + "]");
            if (out.indexOf(song) != out.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
    }
}
