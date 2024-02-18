package ra.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.dto.AddProductDto;
import ra.dto.AddUserDto;
import ra.model.Category;
import ra.model.CategoryProductCount;
import ra.model.Product;
import ra.model.User;
import ra.service.CategoryService;
import ra.service.UploadService;
import ra.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UploadService uploadService;

    @GetMapping
    public String usersList(Model model) {
        List<User> users = userService.FindAll();
        model.addAttribute("users", users);
        return "user/list-users";
    }

    // Hiển thị form thêm danh mục
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("userDto", new AddUserDto());
        return "user/add";
    }

    // Xử lý thêm danh mục

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") AddUserDto addUserDto) {
        User user = new User();
        user.setEmail(addUserDto.getEmail());
        user.setBirthday(addUserDto.getBirthday());
        user.setPassword(addUserDto.getPassword());
        user.setUsername(addUserDto.getUsername());
        user.setImgUrl(uploadService.upload(addUserDto.getImgUrl()));
        userService.addUser(user);
        System.out.println(user.getBirthday() + " " + user.getUsername());
        return "redirect:/users"; // Điều hướng đến trang danh sách user sau khi thêm
    }

    //Delete category


    //Display Form Edit category


    //Edit



    //Display or not


    //search


}

