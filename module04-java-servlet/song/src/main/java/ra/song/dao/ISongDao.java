package ra.song.dao;

import ra.song.model.Song;

import java.util.List;

public interface ISongDao {
    List<Song> getAllSongs();
    List<Song> searchSongsByKeyWord(String query);
    Song getSongById(String id);
    void addSong(Song song);
    void updateSong(Song song);
    void deleteSongById(String id);
}

