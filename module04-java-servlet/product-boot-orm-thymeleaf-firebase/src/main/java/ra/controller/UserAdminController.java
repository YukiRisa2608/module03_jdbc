package ra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.service.CategoryService;

@Controller
@RequestMapping("users-admin")
public class UserAdminController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String categoryManagement(Model model) {

        return "user-admin/list-users";
    }
}