package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import ra.model.Category;
import ra.model.Product;
import ra.model.User;
import ra.service.CategoryService;
import ra.service.ProductService;
import ra.service.UserService;
import ra.utils.Enum.Role;
import ra.dto.*;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    //display admin page
    @GetMapping("/admin")
    public String adminPage(Model model, @ModelAttribute("email") String email) {
        List<Category> categories = categoryService.getAllCategories();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        if(email != null && !email.isEmpty()) {
            User user = userService.findByEmail(email).get();
            UserResponse userResponse = new UserResponse(user);
            model.addAttribute("user", userResponse);
        }
        return "admin/admin-page";
    }

    //login admin page
    @GetMapping("/admin/login")
    public String showLoginAdmin(Model model){
        return "admin/login-admin";
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@ModelAttribute UserLoginDto userLoginDto, Model model, RedirectAttributes rattrs){
        userLoginDto.setRole(Role.ADMIN);
        if(userService.checkLogin(userLoginDto)) {
            rattrs.addAttribute("email", userLoginDto.getEmail());
            return "redirect:/admin";
        }
        model.addAttribute("errorMessage", "Email or password not mactch");
        return "admin/login-admin";
    }

}
