package ra.song.service;

import ra.song.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> getAllSongs();
    List<Song> searchSongsByKeyWord(String query);
    Song getSongById(String id);
    void addSong(Song song);
    void updateSong(Song song);
    void deleteSongById(String id);
}
