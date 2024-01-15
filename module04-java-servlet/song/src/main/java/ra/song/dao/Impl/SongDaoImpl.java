package ra.song.dao.Impl;

import org.springframework.stereotype.Repository;
import ra.song.dao.ISongDao;
import ra.song.model.Song;
import ra.song.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongDaoImpl implements ISongDao {
//get all songs
    @Override
    public List<Song> getAllSongs() {
        List<Song> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("SELECT * FROM songs");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Song song = new Song();
                song.setId(rs.getString("id"));
                song.setSongName(rs.getString("song_name"));
                song.setAuthor(rs.getString("author"));
                song.setDescription(rs.getString("description"));
                song.setImageUrl(rs.getString("image_url"));
                song.setVideoUrl(rs.getString("video_url"));
                song.setDuration(rs.getInt("duration"));
                song.setStatus(rs.getBoolean("status"));
                list.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

//Search song by keywords
    @Override
    public List<Song> searchSongsByKeyWord(String searchKey) {
        List<Song> list = new ArrayList<>();
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("SELECT * FROM songs WHERE song_name LIKE ? OR description LIKE ? OR author LIKE ?");
            callSt.setString(1, "%" + searchKey + "%");
            callSt.setString(2, "%" + searchKey + "%");
            callSt.setString(3, "%" + searchKey + "%");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Song song = new Song();
                song.setId(rs.getString("id"));
                song.setSongName(rs.getString("song_name"));
                song.setAuthor(rs.getString("author"));
                song.setDescription(rs.getString("description"));
                song.setImageUrl(rs.getString("image_url"));
                song.setVideoUrl(rs.getString("video_url"));
                song.setDuration(rs.getInt("duration"));
                song.setStatus(rs.getBoolean("status"));
                list.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

//Get song by ID
    @Override
    public Song getSongById(String id) {
        Song song = null;
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("SELECT * FROM songs WHERE id = ?");
            callSt.setString(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                song = new Song();
                song.setId(rs.getString("id"));
                song.setSongName(rs.getString("song_name"));
                song.setAuthor(rs.getString("author"));
                song.setDescription(rs.getString("description"));
                song.setImageUrl(rs.getString("image_url"));
                song.setVideoUrl(rs.getString("video_url"));
                song.setDuration(rs.getInt("duration"));
                song.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return song;
    }

//add song
    @Override
    public void addSong(Song song) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("INSERT INTO songs (song_name, author, description, image_url, video_url, duration) VALUES (?, ?, ?, ?, ?, ?)");
            callSt.setString(1, song.getSongName());
            callSt.setString(2, song.getAuthor());
            callSt.setString(3, song.getDescription());
            callSt.setString(4, song.getImageUrl());
            callSt.setString(5, song.getVideoUrl());
            callSt.setInt(6, song.getDuration());
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

//update song
    @Override
    public void updateSong(Song song) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("UPDATE songs SET song_name = ?, author = ?, description = ?, image_url = ?, video_url = ?, duration = ?, status = ? WHERE id = ?");
            callSt.setString(1, song.getSongName());
            callSt.setString(2, song.getAuthor());
            callSt.setString(3, song.getDescription());
            callSt.setString(4, song.getImageUrl());
            callSt.setString(5, song.getVideoUrl());
            callSt.setInt(6, song.getDuration());
            callSt.setBoolean(7, song.isStatus());
            callSt.setString(8, song.getId());
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

//delete song
    @Override
    public void deleteSongById(String id) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("DELETE FROM songs WHERE id = ?");
            callSt.setString(1, id);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

}
