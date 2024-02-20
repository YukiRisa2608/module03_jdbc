package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.dto.AddUserDto;
import ra.dto.EditUserDto;
import ra.model.User;
import ra.service.UserService;
import ra.utils.Enum.EmailExistsException;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Hiển thị danh sách tất cả người dùng
    @GetMapping
    public String getAllUser(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list-users";
    }

    //Search
    @GetMapping("/search")
    public String searchUsers(@RequestParam("keyword") String keyword, Model model) {
        List<User> users = userService.searchByKeyword(keyword);
        model.addAttribute("users", users);
        return "user/list-users";
    }

    //block / unblock
    @GetMapping("/status/{id}")
    public String toggleStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.userStatus(id);
        redirectAttributes.addFlashAttribute("successMessage", "User status updated successfully");
        return "redirect:/users";
    }

    //add
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("userDto", new AddUserDto());
        return "user/add-user";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute AddUserDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.addUser(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "User added successfully!");
            return "redirect:/users";
        } catch (EmailExistsException e) {
            // Thêm thông báo lỗi vào model để hiển thị trên giao diện
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/users";
        }
    }


    //edit
    // Hiển thị form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        if (user != null) {
            EditUserDto userDto = new EditUserDto(user.getId(), user.getUsername(), user.getEmail(), "", user.getStatus());
            model.addAttribute("editUserDto", userDto);
            return "user/edit-user";
        } else {
            return "redirect:/users";
        }
    }

    //xử lí edit
    @PostMapping("/edit")
    public String editUser(EditUserDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.editUser(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating user: " + e.getMessage());
        }
        return "redirect:/users";
    }

    //Delete
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserById(userId);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully.");
        } catch (Exception e) {
            // Xử lý lỗi nếu sản phẩm không tìm thấy hoặc không thể xóa
            redirectAttributes.addFlashAttribute("errorMessage", "Error cannot delete or Not found user.");
        }
        return "redirect:/users";
    }
}
