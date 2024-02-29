package com.example.demo;

import com.example.demo.commands.AddSongCommand;
import com.example.demo.commands.AddSongToPlaylistCommand;
import com.example.demo.commands.CommandKeyword;
import com.example.demo.commands.CommandRegistry;
import com.example.demo.commands.CreateGreetingCommand;
import com.example.demo.commands.CreatePlaylistCommand;
import com.example.demo.commands.CreateUserCommand;
import com.example.demo.commands.DeletePlaylistCommand;
import com.example.demo.commands.DeleteSongFromPlaylistCommand;
import com.example.demo.commands.GetGreetingCommand;
import com.example.demo.commands.ListGreetingCommand;
import com.example.demo.commands.ListSongCommand;
import com.example.demo.commands.LoadPlaylistCommand;
import com.example.demo.commands.PlaySongCommand;
import com.example.demo.entities.ActivePlaylist;
import com.example.demo.repositories.GreetingRepository;
import com.example.demo.repositories.IGreetingRepository;
import com.example.demo.repositories.IPlaylistRepository;
import com.example.demo.repositories.ISongRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.repositories.PlaylistRepository;
import com.example.demo.repositories.SongRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PlayActiveService;
import com.example.demo.services.GreetingService;
import com.example.demo.services.IPlayActiveService;
import com.example.demo.services.IPlaylistService;
import com.example.demo.services.ISongsService;
import com.example.demo.services.IUserService;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongsService;
import com.example.demo.services.UsersService;

public class Configuration {
    // Singleton Pattern
    // create an object of Single Configuration Object
    private static Configuration instance = new Configuration();

    // make the constructor private so that this class cannot be
    // instantiated
    private Configuration() {}

    // Get the only object available
    public static Configuration getInstance() {
        return instance;
    }

    // Initialize repositories
    private final IGreetingRepository greetingRepository = new GreetingRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final ISongRepository songRepository = new SongRepository();
    private final IUserRepository userRepository = new UserRepository();
    private final ActivePlaylist activePlaylist = new ActivePlaylist();

    // Initialize services
    private final GreetingService greetingService = new GreetingService(greetingRepository);

    private final ISongsService songService = new SongsService(songRepository);
    private final IUserService userService = new UsersService(userRepository);
    private final IPlaylistService playlistService =
            new PlaylistService(songRepository, userRepository, playlistRepository);
    private final IPlayActiveService playActiveService =
        new PlayActiveService(songRepository, userRepository, playlistRepository, activePlaylist);


    // Initialize commands
    private final CreateGreetingCommand createGreetingCommand =
            new CreateGreetingCommand(greetingService);
    private final ListGreetingCommand listGreetingCommand =
            new ListGreetingCommand(greetingService);
    private final GetGreetingCommand getGreetingCommand = new GetGreetingCommand(greetingService);

    private final AddSongCommand addSongCommand = new AddSongCommand(songService);
    private final AddSongToPlaylistCommand addSongToPlaylistCommand = new AddSongToPlaylistCommand(playlistService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final DeleteSongFromPlaylistCommand deleteSongFromPlaylistCommand = new DeleteSongFromPlaylistCommand(playActiveService);
    private final ListSongCommand listSongCommand = new ListSongCommand(songService);
    private final LoadPlaylistCommand loadPlaylistCommand = new LoadPlaylistCommand(playActiveService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(playActiveService);

    // Initialize commandRegistery
    private final CommandRegistry commandRegistry = new CommandRegistry();

    // Register commands
    private void registerCommands() {
        commandRegistry.registerCommand(CommandKeyword.CREATE_GREETING_COMMAND.getName(),
                createGreetingCommand);
        commandRegistry.registerCommand(CommandKeyword.LIST_GREETING_COMMAND.getName(),
                listGreetingCommand);
        commandRegistry.registerCommand(CommandKeyword.GET_GREETING_COMMAND.getName(),
                getGreetingCommand);
        commandRegistry.registerCommand(CommandKeyword.ADD_SONG_COMMAND.getName(), addSongCommand);
        commandRegistry.registerCommand(CommandKeyword.LIST_SONGS_COMMAND.getName(), listSongCommand);
        commandRegistry.registerCommand(CommandKeyword.CREATE_PLAYLIST_COMMAND.getName(), createPlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.LOAD_PLAYLIST_COMMAND.getName(), loadPlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.DELETE_PLAYLIST_COMMAND.getName(), deletePlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.ADD_SONG_TO_PLAYLIST_COMMAND.getName(), addSongToPlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.PLAY_SONG_COMMAND.getName(), playSongCommand);
        commandRegistry.registerCommand(CommandKeyword.NEXT_SONG_COMMAND.getName(), playSongCommand);
        commandRegistry.registerCommand(CommandKeyword.PREVIOUS_SONG_COMMAND.getName(), playSongCommand);
        commandRegistry.registerCommand(CommandKeyword.STOP_SONG_COMMAND.getName(), playSongCommand);
        commandRegistry.registerCommand(CommandKeyword.DELETE_SONG_FROM_PLAYLIST_COMMAND.getName(), deleteSongFromPlaylistCommand);

    }

    public CommandRegistry getCommandRegistry() {
        registerCommands();
        return commandRegistry;
    }
}
