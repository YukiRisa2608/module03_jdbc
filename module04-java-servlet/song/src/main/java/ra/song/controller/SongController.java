package ra.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.song.model.Song;
import ra.song.service.ISongService;

import java.util.List;

@Controller
public class SongController {
    @Autowired
    private ISongService songService;

    @RequestMapping(value = "/songs", method = RequestMethod.GET) // Get danh sách bài hát
    public String listSongs(Model model) {
        List<Song> list = songService.getAllSongs();
        model.addAttribute("songs", list);
        return "songs"; // Tên file view
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET) // Tìm kiếm bài hát
    public String search(@RequestParam String search, Model model) {
        model.addAttribute("songs", songService.searchSongsByKeyWord(search));
        model.addAttribute("search", search);
        return "songs";
    }

    @PostMapping("/addSong") // Thêm bài hát mới
    public String addSong(@ModelAttribute Song song, Model model) {
        songService.addSong(song);
        return "redirect:/songs"; // Chuyển hướng sau khi thêm bài hát
    }

    @PostMapping("/deleteSong") // Xóa bài hát
    public String deleteSong(@RequestParam String id, Model model) {
        songService.deleteSongById(id);
        return "redirect:/songs"; // Chuyển hướng sau khi xóa
    }

    @PostMapping("/updateSong") // Cập nhật bài hát
    public String updateSong(@ModelAttribute Song song, Model model) {
        songService.updateSong(song);
        return "redirect:/songs"; // Chuyển hướng sau khi cập nhật
    }
}

