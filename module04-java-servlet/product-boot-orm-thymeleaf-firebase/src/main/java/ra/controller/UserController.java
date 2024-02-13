package ra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.Category;
import ra.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String categoryManagement(Model model) {

        return "user/list-users";
    }
}