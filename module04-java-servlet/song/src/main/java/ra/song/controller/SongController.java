package ra.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.song.model.Song;
import ra.song.service.ISongService;

import java.util.List;
//@Controller là một annotation của Spring, đánh dấu lớp này là một Spring MVC Controller.
@Controller
//Lớp SongController sẽ xử lý các yêu cầu HTTP liên quan đến bài hát
public class SongController {
    //@Autowired tự động tiêm các phụ thuộc vào Spring beans.
    //ISongService interface được tiêm vào controller.
    //songService sử dụng để thao tác CRUD với dữ liệu bài hát.
    @Autowired
    private ISongService songService;
    //@GetMapping("/home") xử lý yêu cầu GET đến đường dẫn "/home".
    @GetMapping("/home")
    public String home(Model model) {
       //String "home" là tên của phương thức trong SongController.Tên này có thể thay đổi mà không ảnh hưởng đến các chỗ khác, được spring tự động đọc;
        //Model trong Spring MVC là một Interface, khuôn để đặt dữ liệu để chuyển từ controller đến view.
        //thêm dữ liệu vào Model bằng cách sử dụng phương thức addAttribute. Ví dụ: model.addAttribute("key", value);.
        //"key" là tên sử dụng để truy cập dữ liệu này trong trang web.
        List<Song> songList = songService.getAllSongs();
        model.addAttribute("songs", songList);
        //trong views, ${songs} sẽ truy cập vào danh sách các bài hát đã thêm vào model trong controller
        //ex: <c:forEach items="${songs}
        model.addAttribute("message", "Song Management");
        //sang trang views sẽ dùng là ex: <h1>${message}</h1> để hiển thị "Song Management"
        return "home";// "home" là tên của view
    }

    //{id} là một biến động, được trích xuất từ URL và truyền vào phương thức deleteSong dưới dạng tham số.
    @GetMapping("/deleteSong/{id}")
    //@PathVariable String id: sử dụng để trích xuất giá trị của biến động {id} từ URL và gán vào biến id trong phương thức.
    public String deleteSong(@PathVariable String id) {
        songService.deleteSongById(id);
        return "redirect:/home";
    }

    //trả về view addSong khi người dùng truy cập đường dẫn /addSongForm.
    @GetMapping("/addSongForm")
    public String showAddSongForm() {
        return "addSong";
    }

    //add song method
    @PostMapping("/addSong")
    public String addSong(@ModelAttribute Song song) {
        songService.addSong(song);
        return "redirect:/home";
    }

    //edit form
    @GetMapping("/editSongForm/{id}")
    public String showEditSongForm(@PathVariable String id, Model model) {
        Song song = songService.getSongById(id);
        model.addAttribute("song", song);
        return "editSong";
    }

    //edit method
    @PostMapping("/editSong")
    public String editSong(@ModelAttribute Song song) {
        songService.updateSong(song);
        return "redirect:/home";
    }

    @GetMapping("/musicPlay")
    public String music(Model model) {
        List<Song> musicList = songService.getMusicList(); // Lấy danh sách bài hát có trạng thái true
        model.addAttribute("musicList", musicList);
        return "musicPlay";
    }


//@GetMapping được sử dụng để ánh xạ phương thức xử lý yêu cầu GET đến một URL cụ thể,
//@PathVariable được sử dụng để trích xuất giá trị từ biến đường dẫn trong URL và sử dụng chúng trong phương thức xử lý yêu cầu.

//return home # "redirect:/home "
//redirect:/home, trình duyệt của người dùng sẽ thay đổi URL và gửi một yêu cầu GET mới đến /home. Thích hợp cho việc cập nhật trang sau khi thực hiện một thao tác như thêm mới hoặc xóa bài hát.
//return "home", người dùng sẽ không thấy sự thay đổi trong URL và trang home sẽ được hiển thị trực tiếp. Thích hợp cho việc hiển thị trang kết quả sau khi người dùng tìm kiếm hoặc chỉnh sửa thông tin bài hát mà không cần thay đổi URL.
}


