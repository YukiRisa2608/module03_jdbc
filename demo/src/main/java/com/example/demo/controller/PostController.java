package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Post;
import com.example.demo.service.IPostService;

@Controller
//@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;
    @RequestMapping(value = "/home",method = RequestMethod.GET) // get
    public String home(Model model){
        System.out.println("Controller is called");
        // lấy ra danh sách bài đăng và gửi nó đến view
        model.addAttribute("list",postService.findAllPost());
        return "home";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(@RequestParam String search, Model model){
        model.addAttribute("list",postService.findAllPostByTitleOrContent(search));
        model.addAttribute("search",search);
        return "home";
    }

    //@PostMapping để xử lý dữ liệu được gửi từ form khi người dùng nhấn nút "Post".
    @PostMapping("/addPost")
    public String addPost(@ModelAttribute Post post, Model model) {
        // Thêm post vào cơ sở dữ liệu
        postService.addPost(post);

        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String deleteItem(@ModelAttribute Post post, Model model113) {
        // xoa bai post theo id
        postService.deletePost(post.getId());

        // Chuyển hướng đến trang home
        return "redirect:/home";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post, Model model113) {
        // edit bai post theo id
        postService.editPost(post);

        // Chuyển hướng đến trang home
        return "redirect:/home";
    }
}