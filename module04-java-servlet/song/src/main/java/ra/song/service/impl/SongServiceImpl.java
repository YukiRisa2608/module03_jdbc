package ra.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.song.dao.ISongDao;
import ra.song.model.Song;
import ra.song.service.ISongService;

import java.util.List;

@Service
public class SongServiceImpl implements ISongService {

    @Autowired
    private ISongDao songDao;

    @Override
    public List<Song> getAllSongs() {
        return songDao.getAllSongs();
    }

    @Override
    public Song getSongById(String id) {
        return songDao.getSongById(id);
    }

    @Override
    public void addSong(Song song) {
        songDao.addSong(song);
    }

    @Override
    public void updateSong(Song song) {
        songDao.updateSong(song);
    }

    @Override
    public void deleteSongById(String id) {
        songDao.deleteSongById(id);
    }

    @Override
    public List<Song> searchSongsByKeyWord(String keyword) {
        return songDao.searchSongsByKeyWord(keyword);
    }
}
